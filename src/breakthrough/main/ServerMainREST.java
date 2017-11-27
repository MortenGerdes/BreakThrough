package breakthrough.main;

import breakthrough.server.BreakThroughSRHAndInvoker;

/** App server for Breakthrough, using REST.
 */
public class ServerMainREST {
  
  public static void main(String[] args) throws Exception {
    new ServerMainREST(); // No error handling!
  }

  public ServerMainREST() throws Exception {
      int port = 4567;
      BreakThroughSRHAndInvoker karl = new BreakThroughSRHAndInvoker(port);

    // Welcome
    System.out.println("=== Breakthrough REST (port:"+port+") ===");
    System.out.println(" Use ctrl-c to terminate!"); 

    // and start it by registrering the routes to listen to
    // ala 'serverRequestHandler.registerRoutes();'

  }
}
