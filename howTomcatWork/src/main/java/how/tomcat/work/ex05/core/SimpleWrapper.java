package how.tomcat.work.ex05.core;

import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.InstanceListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Logger;
import org.apache.catalina.Manager;
import org.apache.catalina.Mapper;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Create by haifei on 17/7/2018.
 */
public class SimpleWrapper implements Pipeline, Wrapper {

  private Servlet instance = null;
  private String servletClass;
  private String name;
  private SimplePipeline pipeline = new SimplePipeline(this);  //调用的Pipeline
  private Loader loader;  //加载器，用于加载请求所访问的Servlet
  protected Container parent = null;

  public SimpleWrapper() {
    //设置 Basic pipeline，作为调用Servlet的第一个处理器
    pipeline.setBasic(new SimpleWrapperValve());
  }


  public Valve getBasic() {
    return pipeline.getBasic();
  }

  public void setBasic(Valve valve) {
    pipeline.setBasic(valve);
  }

  public void addValve(Valve valve) {
    pipeline.addValve(valve);
  }

  public Valve[] getValves() {
    return new Valve[0];
  }

  public String getInfo() {
    return null;
  }

  public Loader getLoader() {
    if (loader != null) {
      return (loader);
    }
    if (parent != null) {
      return (parent.getLoader());
    }
    return null;
  }

  public void setLoader(Loader loader) {
    this.loader = loader;
  }

  public Logger getLogger() {
    return null;
  }

  public void setLogger(Logger logger) {

  }

  public Manager getManager() {
    return null;
  }

  public void setManager(Manager manager) {

  }

  public Cluster getCluster() {
    return null;
  }

  public void setCluster(Cluster cluster) {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Container getParent() {
    return parent;
  }

  public void setParent(Container container) {
    this.parent = container;
  }

  public ClassLoader getParentClassLoader() {
    return null;
  }

  public void setParentClassLoader(ClassLoader parent) {

  }

  public Realm getRealm() {
    return null;
  }

  public void setRealm(Realm realm) {

  }

  public DirContext getResources() {
    return null;
  }

  public void setResources(DirContext resources) {

  }

  public void addChild(Container child) {

  }

  public void addContainerListener(ContainerListener listener) {

  }

  public void addMapper(Mapper mapper) {

  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {

  }

  public Container findChild(String name) {
    return null;
  }

  public Container[] findChildren() {
    return new Container[0];
  }

  public ContainerListener[] findContainerListeners() {
    return new ContainerListener[0];
  }

  public Mapper findMapper(String protocol) {
    return null;
  }

  public Mapper[] findMappers() {
    return new Mapper[0];
  }

  public void invoke(Request request, Response response) throws IOException, ServletException {
    pipeline.invoke(request, response);
  }

  public Container map(Request request, boolean update) {
    return null;
  }

  public void removeChild(Container child) {

  }

  public void removeContainerListener(ContainerListener listener) {

  }

  public void removeMapper(Mapper mapper) {

  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {

  }

  public void removeValve(Valve valve) {

  }

  public long getAvailable() {
    return 0;
  }

  public void setAvailable(long available) {

  }

  public String getJspFile() {
    return null;
  }

  public void setJspFile(String jspFile) {

  }

  public int getLoadOnStartup() {
    return 0;
  }

  public void setLoadOnStartup(int value) {

  }

  public String getRunAs() {
    return null;
  }

  public void setRunAs(String runAs) {

  }

  public String getServletClass() {
    return servletClass;
  }

  public void setServletClass(String servletClass) {
    this.servletClass = servletClass;
  }

  public boolean isUnavailable() {
    return false;
  }

  public void addInitParameter(String name, String value) {

  }

  public void addInstanceListener(InstanceListener listener) {

  }

  public void addSecurityReference(String name, String link) {

  }

  public Servlet allocate() throws ServletException {
    if (instance == null) {
      try {
        instance = loadServlet();
      } catch (ServletException e) {
        throw e;
      } catch (Throwable e) {
        throw new ServletException("Cannot allocate a servlet instance.", e);
      }
    }
    return instance;
  }

  private Servlet loadServlet() throws ServletException {
    if (instance != null) {
      return instance;
    }

    Servlet servlet = null;
    String actualClass = servletClass;
    if (actualClass == null) {
      throw new ServletException("servlet class has not been specified");
    }

    Loader loader = getLoader();
    if (loader == null) {
      throw new ServletException("no loader.");
    }

    ClassLoader classLoader = loader.getClassLoader();
    Class classClass = null;

    try {
      if (classLoader != null) {
        classClass = classLoader.loadClass(actualClass);
      }
    } catch (Throwable e) {
      throw new ServletException("Failed class not found.");
    }

    try {
      servlet = (Servlet) classClass.newInstance();
    } catch (Throwable e) {
      throw new ServletException("failed to instantiate servlet");
    }

    servlet.init(null);

    return servlet;
  }

  public void deallocate(Servlet servlet) throws ServletException {

  }

  public String findInitParameter(String name) {
    return null;
  }

  public String[] findInitParameters() {
    return new String[0];
  }

  public String findSecurityReference(String name) {
    return null;
  }

  public String[] findSecurityReferences() {
    return new String[0];
  }

  public void load() throws ServletException {
    instance = loadServlet();
  }

  public void removeInitParameter(String name) {

  }

  public void removeInstanceListener(InstanceListener listener) {

  }

  public void removeSecurityReference(String name) {

  }

  public void unavailable(UnavailableException unavailable) {

  }

  public void unload() throws ServletException {

  }
}
