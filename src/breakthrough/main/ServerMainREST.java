package breakthrough.main;
import breakthrough.domain.Breakthrough;
import breakthrough.domain.Move;
import breakthrough.domain.MoveState;
import breakthrough.server.BreakThroughSRHAndInvoker;
import breakthrough.server.ServerRestAdapter;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;

import static breakthrough.main.JsonUtil.json;
import static spark.Spark.*;

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
