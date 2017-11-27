package breakthrough.main;
import breakthrough.server.ServerRestAdapter;
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
      ServerRestAdapter adapter = new ServerRestAdapter();

    // TODO: Create the REST based server instance

    get("/breakthrough/:id", (req, res) ->
    {
      String id = req.params(":id");
      String gameState = adapter.getOnBreakthrough(id);
      if(gameState.equals("notfound"))
      {
          res.status(HttpServletResponse.SC_NOT_FOUND);
          return "{}";
      }
      res.body(gameState);
      res.status(HttpServletResponse.SC_OK);
      return gameState;
    }, json());

    post("/breakthrough", (req, res) ->
    {
        String gameID = adapter.postOnBreakthrough();
        res.body(gameID);
        res.status(HttpServletResponse.SC_CREATED);
        res.header("Location", req.host() + "/breakthrough/" + gameID);
        return "{}";
    }, json());

    // Welcome
    System.out.println("=== Breakthrough REST (port:"+port+") ===");
    System.out.println(" Use ctrl-c to terminate!"); 

    // and start it by registrering the routes to listen to
    // ala 'serverRequestHandler.registerRoutes();'

  }
}
