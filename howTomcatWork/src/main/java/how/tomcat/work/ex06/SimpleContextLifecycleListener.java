package how.tomcat.work.ex06;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Create by haifei on 24/7/2018.
 */
public class SimpleContextLifecycleListener implements LifecycleListener{


  public void lifecycleEvent(LifecycleEvent event) {
    Lifecycle lifecycle = event.getLifecycle();
    System.out.println("SimpleContextLifecycleListener's event " + event.getType().toString());

    if (Lifecycle.START_EVENT.equals(event.getType())) {
      System.out.println("Starting context.");
    }

    if (Lifecycle.STOP_EVENT.equals(event.getType())) {
      System.out.println("Stopping context.");
    }
  }


}
