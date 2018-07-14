package how.tomcat.work.ex02;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Create by haifei on 14/7/2018.
 */
public class RequestFacade implements ServletRequest{

  private ServletRequest request = null;

  public RequestFacade(ServletRequest request) {
    this.request = request;
  }

  public Object getAttribute(String s) {
    return request.getAttribute(s);
  }

  public Enumeration<String> getAttributeNames() {
    return request.getAttributeNames();
  }

  public String getCharacterEncoding() {
    return null;
  }

  public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

  }

  public int getContentLength() {
    return 0;
  }

  public String getContentType() {
    return null;
  }

  public ServletInputStream getInputStream() throws IOException {
    return null;
  }

  public String getParameter(String s) {
    return null;
  }

  public Enumeration<String> getParameterNames() {
    return null;
  }

  public String[] getParameterValues(String s) {
    return new String[0];
  }

  public Map<String, String[]> getParameterMap() {
    return null;
  }

  public String getProtocol() {
    return null;
  }

  public String getScheme() {
    return null;
  }

  public String getServerName() {
    return null;
  }

  public int getServerPort() {
    return 0;
  }

  public BufferedReader getReader() throws IOException {
    return null;
  }

  public String getRemoteAddr() {
    return null;
  }

  public String getRemoteHost() {
    return null;
  }

  public void setAttribute(String s, Object o) {

  }

  public void removeAttribute(String s) {

  }

  public Locale getLocale() {
    return null;
  }

  public Enumeration<Locale> getLocales() {
    return null;
  }

  public boolean isSecure() {
    return false;
  }

  public RequestDispatcher getRequestDispatcher(String s) {
    return null;
  }

  public String getRealPath(String s) {
    return null;
  }

  public int getRemotePort() {
    return 0;
  }

  public String getLocalName() {
    return null;
  }

  public String getLocalAddr() {
    return null;
  }

  public int getLocalPort() {
    return 0;
  }

  public ServletContext getServletContext() {
    return null;
  }

  public AsyncContext startAsync() throws IllegalStateException {
    return null;
  }

  public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws
    IllegalStateException {
    return null;
  }

  public boolean isAsyncStarted() {
    return false;
  }

  public boolean isAsyncSupported() {
    return false;
  }

  public AsyncContext getAsyncContext() {
    return null;
  }

  public DispatcherType getDispatcherType() {
    return null;
  }
}
