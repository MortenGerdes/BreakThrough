package breakthrough.marshall;

import breakthrough.domain.Breakthrough;
import breakthrough.domain.Color;
import breakthrough.domain.Position;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frs.broker.Constants;
import frs.broker.Invoker;
import frs.broker.ReplyObject;

import javax.servlet.http.HttpServletResponse;

public class BTInvoker implements Invoker {
    private final Breakthrough breakthrough;
    private final Gson gson;

    public BTInvoker(Breakthrough breakthrough) {
        this.breakthrough = breakthrough;
        this.gson = new Gson();
    }

    @Override
    public ReplyObject handleRequest(String objectId, String operationName, String payload)
    {
        ReplyObject reply = null;
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();

        if(operationName.equals(Constants.GET_PIECE_AT_SERVER))
        {
            Position pos = gson.fromJson(array.get(0), Position.class);
            Color color = breakthrough.getPieceAt(pos);
            reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(color));
        }
        return null;
    }
}
