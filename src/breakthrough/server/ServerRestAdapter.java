package breakthrough.server;

import breakthrough.domain.Breakthrough;
import breakthrough.domain.BreakthroughSurrogate;
import com.google.gson.Gson;

import java.util.HashMap;

public class ServerRestAdapter
{
    private int gameID = 42;
    private HashMap<Integer, Breakthrough> gameDB = new HashMap<>();
    private Gson gson = new Gson();

    public String postOnBreakthrough()
    {
        int nextGameID = findFirstAvailableGameID();
        Breakthrough game = new BreakthroughSurrogate();

        gameDB.put(nextGameID, game);
        return nextGameID+"";

    }

    public String putOnBreakthrough(String gameID, String body)
    {
        return null;
    }

    public String deleteOnBreakthrough(String gameID)
    {
        return null;
    }

    public String getOnBreakthrough(String gameID)
    {
        if(gameDB.containsKey(gameID))
        {
            return gson.toJson(gameDB.get(gameID));
        }
        return "notfound";
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
        return loopable+1;
    }
}
