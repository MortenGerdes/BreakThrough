package breakthrough.server;

import breakthrough.domain.Breakthrough;
import breakthrough.domain.BreakthroughSurrogate;
import breakthrough.domain.Move;
import breakthrough.domain.MoveState;
import breakthrough.main.ResponseObject;
import breakthrough.main.StatisticObject;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ServerRestAdapter
{
    private int gameID = 42;
    private HashMap<Integer, Breakthrough> gameDB = new HashMap<>();
    private Gson gson = new Gson();

    // Statistics
    private int lastStatusCode = -1;
    private int lastCalledGame = -1;
    private int lastCreatedGameIns = -1;
    private String lastHeader = null;
    private String lastHeaderData = null;
    private String lastValidMove = null;

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

        lastStatusCode = ro.getStatus();
        lastCalledGame = nextGameID;
        lastCreatedGameIns = nextGameID;
        lastHeader = "Location";
        lastHeaderData = ro.getHeader("Location");
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

            lastValidMove = move.toString();
        }
        ro.setBody(gson.toJson(move));
        ro.setStatus(HttpServletResponse.SC_ACCEPTED);

        lastStatusCode = ro.getStatus();
        lastCreatedGameIns = Integer.parseInt(gameID);
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

        lastStatusCode = ro.getStatus();
        lastCreatedGameIns = Integer.parseInt(gameID);
        return ro;
    }

    public StatisticObject getStatsOfServer()
    {
        StatisticObject statObj = new StatisticObject();

        statObj.setGamesRunning(gameDB.size());
        statObj.setLastCalledGame(lastCalledGame);
        statObj.setLastCreatedGameIns(lastCreatedGameIns);
        statObj.setLastHeader(lastHeader);
        statObj.setLastHeaderData(lastHeaderData);
        statObj.setLastValidMove(lastValidMove);
        statObj.setNextGameToBeCreated(findFirstAvailableGameID());
        statObj.setLastStatusCode(lastStatusCode);

        return statObj;
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
