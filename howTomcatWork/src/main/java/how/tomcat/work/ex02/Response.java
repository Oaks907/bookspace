//package how.tomcat.work.ex02;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.Locale;
//
///**
// * Create by haifei on 12/7/2018.
// */
//public class Response implements ServletResponse {
//
//  private static final int BUFFER_SIZE = 1024;
//  Request request;
//  OutputStream output;
//
//  public Response(OutputStream output) {
//    this.output = output;
//  }
//
//  public void setRequest(Request request) {
//    this.request = request;
//  }
//
//  public void sendStaticResource() throws IOException {
//    byte[] bytes = new byte[BUFFER_SIZE];
//    FileInputStream fis = null;
//
//    // webroot=/Users/haifei/studyspace/bookstudy/howTomcatWork/webroot uri=/index.html
//    System.out.println("webroot=" + HttpServer1.WEB_ROOT + " uri=" + request.getUri());
//    File file = new File(HttpServer1.WEB_ROOT, request.getUri());
//
//    try {
//      if (file.exists()) {
//        fis = new FileInputStream(file);
//        int ch = fis.read(bytes, 0, BUFFER_SIZE);
//        while (ch != -1) {
//          output.write(bytes, 0, ch);
//          ch = fis.read(bytes, 0, BUFFER_SIZE);
//        }
//      } else {
//        String errorMesssage = "HTTP/1.1 404 File Not Fount\r\n"
//          + "Content-Type:text/html\r\n"
//          + "Content-Length:23\r\n"
//          + "\r\n"
//          + "<h1>File Not Found</h1>";
//        output.write(errorMesssage.getBytes());
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } finally {
//      if (fis != null) {
//        fis.close();
//      }
//    }
//
//  }
//
//
//  public String getCharacterEncoding() {
//    return null;
//  }
//
//  public String getContentType() {
//    return null;
//  }
//
//  public ServletOutputStream getOutputStream() throws IOException {
//    return null;
//  }
//
//  public PrintWriter getWriter() throws IOException {
//    PrintWriter writer = new PrintWriter(output, true);
//    return writer;
//  }
//
//  public void setCharacterEncoding(String s) {
//
//  }
//
//  public void setContentLength(int i) {
//
//  }
//
//  public void setContentType(String s) {
//
//  }
//
//  public void setBufferSize(int i) {
//
//  }
//
//  public int getBufferSize() {
//    return 0;
//  }
//
//  public void flushBuffer() throws IOException {
//
//  }
//
//  public void resetBuffer() {
//
//  }
//
//  public boolean isCommitted() {
//    return false;
//  }
//
//  public void reset() {
//
//  }
//
//  public void setLocale(Locale locale) {
//
//  }
//
//  public Locale getLocale() {
//    return null;
//  }
//}
