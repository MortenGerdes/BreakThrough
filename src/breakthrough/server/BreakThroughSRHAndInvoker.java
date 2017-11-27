package breakthrough.server;

import breakthrough.domain.Move;
import breakthrough.domain.MoveState;
import breakthrough.main.ResponseObject;
import com.google.gson.Gson;

import static breakthrough.main.JsonUtil.json;
import static spark.Spark.*;

import javax.servlet.http.HttpServletResponse;

public class BreakThroughSRHAndInvoker
{
    ServerRestAdapter adapter = new ServerRestAdapter();
    Gson gson = new Gson();

    public BreakThroughSRHAndInvoker(int port)
    {
        port(port);
        registerAllRoutes();
    }

    public void registerGetRoutes()
    {
        get("/breakthrough/:id", (req, res) ->
        {
            String id = req.params(":id");
            ResponseObject response = adapter.getOnBreakthrough(id);
            if(response.getStatus() == HttpServletResponse.SC_NOT_FOUND)
            {
                res.status(HttpServletResponse.SC_NOT_FOUND);
                return "{}";
            }
            res.status(HttpServletResponse.SC_OK);
            res.body(response.getBody());
            return gson.toJson(response);
        });
    }
    public void registerPostRoutes()
    {
        post("/breakthrough/create", (req, res) ->
        {
            ResponseObject response = adapter.postOnBreakthrough();
            res.body(response.getBody());
            res.status(response.getStatus());
            res.header("Location", req.host() + "/breakthrough/" + response.getBody());
            return gson.toJson(response);
        });
    }

    public void registerPutRoutes()
    {
        put("/breakthrough/:id", (req, res)->
        {
            String id = req.params(":id");
            Move move = gson.fromJson(req.body(), Move.class);
            ResponseObject response = adapter.putOnBreakthrough(id, move);
            Move checkedMove = gson.fromJson(response.getBody(), Move.class);
            if(MoveState.ACCEPTED.equals(checkedMove.getStatus()))
            {
                res.status(HttpServletResponse.SC_ACCEPTED);
                res.body(gson.toJson(checkedMove));
            }
            else if(MoveState.REJECTED.equals(checkedMove.getStatus()))
            {
                res.status(HttpServletResponse.SC_BAD_REQUEST);
            }
            return gson.toJson(checkedMove);
        });
    }

    public void registerAllRoutes()
    {
        registerGetRoutes();
        registerPostRoutes();
        registerPutRoutes();
    }
}