package breakthrough.client;

import breakthrough.domain.Breakthrough;
import breakthrough.domain.Color;
import breakthrough.domain.Move;
import breakthrough.domain.Position;
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
        return null;
    }

    @Override
    public Color getWinner() {
        return null;
    }

    @Override
    public boolean move(Move move) {
        return false;
    }
}
