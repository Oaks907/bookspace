package how.tomcat.work.ex10.core;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Create by haifei on 29/7/2018.
 */
public class SimpleContextConfig implements LifecycleListener {
  public void lifecycleEvent(LifecycleEvent event) {
    if (Lifecycle.START_EVENT.equals(event.getType())) {
      Context context = (Context) event.getLifecycle();
      context.setConfigured(true);
    }
  }
}
