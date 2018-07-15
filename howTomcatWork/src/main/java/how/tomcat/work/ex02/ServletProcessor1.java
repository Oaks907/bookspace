//package how.tomcat.work.ex02;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.net.URLStreamHandler;
//
///**
// * Create by haifei on 12/7/2018.
// */
//public class ServletProcessor1 {
//
//  public void process(Request request, Response response) {
//    String uri = request.getUri();
//    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
//    System.out.println("servletName:" + servletName);
//    URLClassLoader loader = null;
//
//    try {
//      URL[] urls = new URL[1];
//      URLStreamHandler streamHandler = null;
//      File classPath = new File(Constants.WEB_ROOT);
//      /**
//       * @param      protocol   the name of the protocol to use.
//       * @param      host       the name of the host.
//       * @param      file       the file on the host.
//       */
//      System.out.println(classPath.getCanonicalPath() + File.separator);
//      String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
//      /**
//       * @param      context   the context in which to parse the specification.
//       * @param      spec      the {@code String} to parse as a URL.
//       * @param      handler   the stream handler for the URL.
//       */
//      urls[0] = new URL(null, repository, streamHandler);
//      loader = new URLClassLoader(urls);
//    } catch (IOException e) {
//      e.printStackTrace();
//      System.out.println(e.toString());
//    }
//
//    Class myClass = null;
//
//    try {
//      servletName = this.getClass().getPackage().getName() + "." + servletName;
//      myClass = loader.loadClass(servletName);
//    } catch (ClassNotFoundException e) {
//      System.out.println(e.toString());
//    }
//
//    Servlet servlet = null;
//
//    try {
//      servlet = (Servlet) myClass.newInstance();
//      servlet.service((ServletRequest) request, (ServletResponse) response);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//  }
//}
