package breakthrough.main;

import breakthrough.client.ClientBTProxy;
import breakthrough.domain.Breakthrough;
import breakthrough.domain.Color;
import breakthrough.domain.Move;
import breakthrough.domain.Position;
import frs.broker.Requestor;

public class BTProxyGame implements Breakthrough {
    private ClientBTProxy requestor;

    public BTProxyGame(ClientBTProxy requestor) {
        this.requestor = requestor;
    }

    @Override
    public Color getPieceAt(Position p) {
        return requestor.getPieceAt(p);
    }

    @Override
    public Color getPlayerInTurn() {
        return requestor.getPlayerInTurn();
    }

    @Override
    public Color getWinner() {
        return requestor.getWinner();
    }

    @Override
    public boolean move(Move move) {
        return requestor.move(move);
    }
}
