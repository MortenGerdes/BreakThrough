package breakthrough.client;


import breakthrough.domain.Color;
import breakthrough.domain.Move;
import breakthrough.domain.Position;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by csdev on 11/20/17.
 */
public class TestClientBTProxy {

    private SpyRequester requester;
    private ClientBTProxy btProxy;
    private Position position1;
    private Position position2;
    private Move move;

    @Before
    public void setUp() {
        requester = new SpyRequester();
        btProxy = new ClientBTProxy(requester);
        position1 = new Position(1,0);
        position2 = new Position(2,0);
        move = new Move(position1, position2);
    }

    @Test
    public void shouldValidateRequestGetPlayerInTurn(){
        btProxy.getPlayerInTurn();
        assertThat(requester.lastObjectId, is("2"));
        assertThat(requester.lastOperationName, is("getplayerinturn"));
    }

    @Test
    public void shouldValidateRequestGetWinner(){
        btProxy.getWinner();
        assertThat(requester.lastObjectId, is("3"));
        assertThat(requester.lastOperationName, is("getwinner"));

    }

    @Test
    public void shouldValidateRequestGetPieceAt(){
        btProxy.getPieceAt(position1);
        assertThat(requester.lastObjectId, is("1"));
        assertThat(requester.lastOperationName, is("getpieceat"));
        assertThat(requester.lastArgument[0], is(position1));
        System.out.println(position1.toString());
    }

    @Test
    public void shouldValidateMove(){
        btProxy.move(move);
        assertThat(requester.lastObjectId, is("4"));
        assertThat(requester.lastOperationName, is("move"));
    }
}

