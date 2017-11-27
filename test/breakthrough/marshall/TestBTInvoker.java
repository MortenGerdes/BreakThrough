package breakthrough.marshall;

import breakthrough.client.ClientBTProxy;
import breakthrough.domain.BreakthroughSurrogate;
import breakthrough.domain.Move;
import breakthrough.domain.Position;
import breakthrough.doubles.LocalMethodCallClientRequestHandler;
import frs.broker.ClientRequestHandler;
import frs.broker.Invoker;
import frs.broker.marshall.json.StandardJSONRequestor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Created by csdev on 11/20/17.
 */
public class TestBTInvoker {

    private ClientBTProxy btProxy;
    private BreakthroughSurrogate btServant;
    private Position position1;
    private Position position2;
    private Move move;

    @Before
    public void setup(){
        btServant = new BreakthroughSurrogate();
        Invoker invoker = new BTInvoker(btServant);

        ClientRequestHandler crh = new LocalMethodCallClientRequestHandler(invoker);
        StandardJSONRequestor requester = new StandardJSONRequestor(crh);

        btProxy = new ClientBTProxy(requester);

        position1 = new Position(1,0);
        position2 = new Position(2, 0);
        move = new Move(position1, position2);
    }

    @Test
    public void shouldInvokeGetWinner(){
        assertThat(btProxy.getWinner(), is(btServant.getWinner()));
    }

    @Test
    public void shouldInvokeGetPlayerInTurn(){
        assertThat(btProxy.getPlayerInTurn(), is(btServant.getPlayerInTurn()));
    }

    @Test
    public void shouldInvokeGetPieceAt(){
        assertThat(btProxy.getPieceAt(position1), is(btServant.getPieceAt(position1)));
    }

    @Test
    public void shouldInvokeMove(){
        assertThat(btProxy.move(move), is(btServant.move(move)));
    }

}
