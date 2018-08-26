package Thread;

/**
 * Create by haifei on 11/8/2018.
 */
public class DaemonThreadTest implements Runnable {

  Thread thread;

  @Override
  public void run() {
    System.out.println("Thead start");
  }

  public void threadStart() {
    thread = new Thread(this, "Daemod Thead ONE");
    thread.setDaemon(true);
    thread.start();
  }



}
