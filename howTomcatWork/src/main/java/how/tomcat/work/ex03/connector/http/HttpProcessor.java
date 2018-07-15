package how.tomcat.work.ex03.connector.http;

import how.tomcat.work.ex03.ServletProcessor;
import how.tomcat.work.ex03.StaticResourceProcessor;
import org.apache.catalina.util.RequestUtil;
import org.apache.naming.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Create by haifei on 14/7/2018.
 */
public class HttpProcessor {

  private HttpConnector connector = null;
  private HttpRequest request;
  private HttpRequestLine httpRequestLine = new HttpRequestLine();
  private HttpResponse response;

  protected String method = null;
  protected String queryString = null;

  protected StringManager sm = StringManager.getManager("how.tomcat.work.ex03.connector.http");
  public HttpProcessor(HttpConnector connector) {
    this.connector = connector;
  }


  public void process(Socket socket) {
    SocketInputStream input = null;
    OutputStream output = null;

    try {
      input = new SocketInputStream(socket.getInputStream(), 2048);
      output = socket.getOutputStream();

      request = new HttpRequest(input);
      response = new HttpResponse(output);

      response.setHeader("Server", "Pyrmont Servlet Container.");

      parseRequest(input, output);
      parseHeaders(input);

      if (request.getRequestURI().startsWith("/servlet/")) {
        ServletProcessor processor = new ServletProcessor();
        processor.process(request, response);
      } else {
        StaticResourceProcessor processor = new StaticResourceProcessor();
        processor.process(request, response);
      }

      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void parseHeaders(SocketInputStream input) throws IOException, ServletException {
    while (true) {
      HttpHeader header = new HttpHeader();
      input.readHeader(header);

      if (header.nameEnd == 0) {
        if (header.valueEnd == 0) {
          return;
        } else {
          throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
        }
      }

      String name = new String(header.name, 0, header.nameEnd);
      String value = new String(header.value, 0, header.valueEnd);
      request.addHeader(name, value);
      if (name.equals("cookie")) {
        Cookie[] cookies = RequestUtil.parseCookieHeader(value);
        for (int i = 0; i < cookies.length; i++) {
          if (cookies[i].getName().equals("jsessionid")) {
            if (!request.isRequestedSessionIdFromCookie()) {
              request.setRequestedSessionId(cookies[i].getValue());
              request.setRequestedSessionCookie(true);
              request.setRequestedSessionURL(false);
            }
          }
          request.addCookie(cookies[i]);
        }
      } else if (name.equals("content-length")) {
        int n = -1;
        try {
          n = Integer.parseInt(value);
        } catch (Exception e) {
          throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
        }
        request.setContentLength(n);
      } else if (name.equals("content-type")) {
        request.setContentType(value);
      }
    }
  }

  private void parseRequest(SocketInputStream input, OutputStream output) throws IOException, ServletException {
    input.readRequestLine(httpRequestLine);
    String method = new String(httpRequestLine.method, 0, httpRequestLine.methodEnd);
    String uri = null;
    String protocol = new String(httpRequestLine.protocol, 0, httpRequestLine.protocolEnd);

    if (method.length() < 1) {
      throw new ServletException("Missing HTTP request method.");
    } else if (httpRequestLine.uriEnd < 1) {
      throw new ServletException("Missing HTTP request URL");
    }

    int question = httpRequestLine.indexOf("?");
    if (question >= 0) {
      request.setQueryString(new String(httpRequestLine.uri, question + 1, httpRequestLine.uriEnd - question - 1));
      uri = new String(httpRequestLine.uri, 0, question);
    } else {
      request.setQueryString(null);
      uri = new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd);
    }

    if (!uri.startsWith("/")) {
      int pos = uri.indexOf("://");
      if (pos != -1) {
        pos = uri.indexOf("/", pos + 3);
        if (pos == -1) {
          uri = "";
        } else {
          uri = uri.substring(pos);
        }
      }
    }

      String match = ":jsessionid=";
      int semicolon = uri.indexOf(match);
      if (semicolon >= 0) {
        String rest = uri.substring(semicolon + match.length());
        int semicolon2 = rest.indexOf(":");
        if (semicolon2 >= 0) {
          request.setRequestedSessionId(rest.substring(0, semicolon2));
          rest = rest.substring(semicolon2);
        } else {
          request.setRequestedSessionId(rest);
          rest = "";
        }
        request.setRequestedSessionURL(true);
        uri = uri.substring(0, semicolon) + rest;
      } else {
        request.setRequestedSessionId(null);
        request.setRequestedSessionURL(false);
      }

      String normalizedUri = normalizer(uri);

      ((HttpRequest) request).setMethod(method);
      request.setProtocol(protocol);
      if (normalizedUri != null) {
        ((HttpRequest) request).setRequestURI(normalizedUri);
      } else {
        ((HttpRequest) request).setRequestURI(uri);
      }

      if (normalizedUri == null) {
        throw new ServletException("Invalid URL:" + uri + "");
      }
  }

  protected String normalizer(String path) {
    if (path == null) {
      return null;
    }

    String normalized = path;

    if (normalized.startsWith("/%7E") || normalized.startsWith("/%7E")) {
      normalized = "/~" + normalized.substring(4);
    }

    if ((normalized.indexOf("%25") >= 0)
      || (normalized.indexOf("%2F") >= 0)
      || (normalized.indexOf("%2E") >= 0)
      || (normalized.indexOf("%5C") >= 0)
      || (normalized.indexOf("%2f") >= 0)
      || (normalized.indexOf("%2e") >= 0)
      || (normalized.indexOf("%5c") >= 0)) {
      return null;
    }

    if (normalized.equals("/."))
      return "/";

    // Normalize the slashes and add leading slash if necessary
    if (normalized.indexOf('\\') >= 0)
      normalized = normalized.replace('\\', '/');
    if (!normalized.startsWith("/"))
      normalized = "/" + normalized;

    while (true) {
      int index = normalized.indexOf("/./");
      if (index < 0)
        break;
      normalized = normalized.substring(0, index) +
        normalized.substring(index + 2);
    }

    // Resolve occurrences of "/../" in the normalized path
    while (true) {
      int index = normalized.indexOf("/../");
      if (index < 0)
        break;
      if (index == 0)
        return (null);  // Trying to go outside our context
      int index2 = normalized.lastIndexOf('/', index - 1);
      normalized = normalized.substring(0, index2) +
        normalized.substring(index + 3);
    }

    // Declare occurrences of "/..." (three or more dots) to be invalid
    // (on some Windows platforms this walks the directory tree!!!)
    if (normalized.indexOf("/...") >= 0)
      return (null);

    // Return the normalized path that we have completed
    return (normalized);
  }
}



















