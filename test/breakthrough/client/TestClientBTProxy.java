package breakthrough.client;


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

    @Before
    public void setUp() {
        requester = new SpyRequester();
        btProxy = new ClientBTProxy(requester);
    }

    @Test
    public void shouldValidateRequestObjectCreated(){
        btProxy.getPlayerInTurn();
        assertThat(requester.lastObjectId, is("2"));
        assertThat(requester.lastOperationName, is("getplayerinturn"));


    }
}
