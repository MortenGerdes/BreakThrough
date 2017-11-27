package breakthrough.client;

import breakthrough.domain.*;
import breakthrough.main.ResponseObject;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import frs.broker.ClientProxy;

public class ClientBTProxyREST implements ClientProxy, Breakthrough {
    private String gameID;
    private int port;
    private String baseURL;
    private Gson gson = new Gson();

    public ClientBTProxyREST(String hostname, int port, String gameID) {
        this.gameID = gameID;
        this.port = port;
        this.baseURL = "http://" + hostname + ":" + port + "/";
    }

    public ClientBTProxyREST(String hostname, int port) {
        this.port = port;
        this.baseURL = "http://" + hostname + ":" + port + "/";

        HttpResponse<JsonNode> jsonRes = null;
        try {
            jsonRes = Unirest.post(baseURL + "breakthrough/create").asJson();
            ResponseObject ro = gson.fromJson(jsonRes.getBody().toString(), ResponseObject.class);
            gameID = ro.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Color getPieceAt(Position p)
    {
        HttpResponse<JsonNode> jsonRes = null;
        try {
            jsonRes = Unirest.get(baseURL + "breakthrough/" + gameID).asJson();
            ResponseObject ro = gson.fromJson(jsonRes.getBody().toString(), ResponseObject.class);
            Breakthrough game = gson.fromJson(ro.getBody(), BreakthroughSurrogate.class);
            return game.getPieceAt(p);

        } catch (UnirestException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Color getPlayerInTurn() {
        HttpResponse<JsonNode> jsonRes = null;
        try {
            jsonRes = Unirest.get(baseURL + "breakthrough/" + gameID).asJson();
            ResponseObject ro = gson.fromJson(jsonRes.getBody().toString(), ResponseObject.class);
            Breakthrough game = gson.fromJson(ro.getBody(), BreakthroughSurrogate.class);
            return game.getPlayerInTurn();

        } catch (UnirestException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Color getWinner() {
        HttpResponse<JsonNode> jsonRes = null;
        try {
            jsonRes = Unirest.get(baseURL + "breakthrough/" + gameID).asJson();
            ResponseObject ro = gson.fromJson(jsonRes.getBody().toString(), ResponseObject.class);
            Breakthrough game = gson.fromJson(ro.getBody(), BreakthroughSurrogate.class);
            return game.getWinner();

        } catch (UnirestException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean move(Move move) {
        HttpResponse<JsonNode> jsonRes = null;
        try {
            jsonRes = Unirest.put(baseURL + "breakthrough/" + gameID).body(gson.toJson(move)).asJson();
            Move moveFromServer = gson.fromJson(jsonRes.getBody().toString(), Move.class);
            if(moveFromServer.getStatus() == MoveState.ACCEPTED)
            {
                return true;
            }

        } catch (UnirestException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
