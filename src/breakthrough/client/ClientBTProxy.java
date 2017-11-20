package breakthrough.client;

import breakthrough.domain.*;
import breakthrough.main.BTProxyGame;
import frs.broker.ClientProxy;
import frs.broker.Constants;
import frs.broker.Requestor;

public class ClientBTProxy implements ClientProxy, Breakthrough {
    private final Requestor requestor;

    public ClientBTProxy(Requestor requestor)
    {
        this.requestor = requestor;
    }

    @Override
    public Color getPieceAt(Position p) {
        return requestor.sendRequestAndAwaitReply("1", Constants.GET_PIECE_AT_SERVER, Color.class, p);
    }

    @Override
    public Color getPlayerInTurn() {
        return requestor.sendRequestAndAwaitReply("2", Constants.GET_PLAYER_IN_TURN, Color.class);
    }

    @Override
    public Color getWinner() {
        return requestor.sendRequestAndAwaitReply("3", Constants.GET_WINNER, Color.class);
    }

    @Override
    public boolean move(Move move) {
        return requestor.sendRequestAndAwaitReply("4", Constants.MOVE, Boolean.class, move);
    }
}
