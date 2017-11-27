package breakthrough.server;

import breakthrough.domain.Breakthrough;
import breakthrough.domain.BreakthroughSurrogate;
import breakthrough.domain.Move;
import breakthrough.domain.MoveState;
import breakthrough.main.ResponseObject;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ServerRestAdapter
{
    private int gameID = 42;
    private HashMap<Integer, Breakthrough> gameDB = new HashMap<>();
    private Gson gson = new Gson();

    public ResponseObject postOnBreakthrough()
    {
        int nextGameID = findFirstAvailableGameID();
        ResponseObject ro = new ResponseObject();
        Breakthrough game = new BreakthroughSurrogate();
        System.out.println("Created new game with id = " + nextGameID);

        gameDB.put(nextGameID, game);
        ro.setBody(nextGameID+"");
        ro.setStatus(HttpServletResponse.SC_CREATED);
        ro.addHeader("Location", "/breakthrough/create");
        return ro;
    }

    public ResponseObject putOnBreakthrough(String gameID, Move move)
    {
        ResponseObject ro = new ResponseObject();
        Breakthrough game = gameDB.get(Integer.parseInt(gameID));
        move.changeState(MoveState.REJECTED);
        ro.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if(game.move(move))
        {
            move.changeState(MoveState.ACCEPTED);
        }
        ro.setBody(gson.toJson(move));
        ro.setStatus(HttpServletResponse.SC_ACCEPTED);
        return ro;
    }

    public String deleteOnBreakthrough(String gameID)
    {
        return null;
    }

    public ResponseObject getOnBreakthrough(String gameID)
    {
        ResponseObject ro = new ResponseObject();
        if(gameDB.containsKey(Integer.parseInt(gameID)))
        {
            ro.setBody(gson.toJson(gameDB.get(Integer.parseInt(gameID))));
            ro.setStatus(HttpServletResponse.SC_OK);
            return ro;
        }
        ro.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return ro;
    }

    private int findFirstAvailableGameID()
    {
        int loopable = gameID;
        if(gameDB.isEmpty()) return gameID;

        for(Integer i: gameDB.keySet())
        {
            if(i != loopable)
            {
                return loopable;
            }
            loopable++;
        }
        return loopable;
    }
}
