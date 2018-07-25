package how.tomcat.work.ex08.core;

import org.apache.catalina.Container;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Create by haifei on 17/7/2018.
 * 构建类加载器，在Tomcat中指明了要由那个文件中加载Servlet
 */
public class SimpleLoader implements Loader, Lifecycle {

  public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

  ClassLoader classLoader;
  Container container;

  public SimpleLoader() {
    try {
      URL[] urls = new URL[1];
      URLStreamHandler handler = null;
      File classPath = new File(WEB_ROOT);
      String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
      urls[0] = new URL(null, repository, handler);
      classLoader = new URLClassLoader(urls);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }

  public ClassLoader getClassLoader() {
    return classLoader;
  }

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }

  public DefaultContext getDefaultContext() {
    return null;
  }

  public void setDefaultContext(DefaultContext defaultContext) {

  }

  public boolean getDelegate() {
    return false;
  }

  public void setDelegate(boolean delegate) {

  }

  public String getInfo() {
    return null;
  }

  public boolean getReloadable() {
    return false;
  }

  public void setReloadable(boolean reloadable) {

  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {

  }

  public void addRepository(String repository) {

  }

  public String[] findRepositories() {
    return new String[0];
  }

  public boolean modified() {
    return false;
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {

  }

  public void addLifecycleListener(LifecycleListener listener) {

  }

  public LifecycleListener[] findLifecycleListeners() {
    return new LifecycleListener[0];
  }

  public void removeLifecycleListener(LifecycleListener listener) {

  }

  public void start() throws LifecycleException {
    System.out.println("Starting SimpleLoader.");
  }

  public void stop() throws LifecycleException {

  }
}
