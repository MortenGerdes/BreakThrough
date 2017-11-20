package breakthrough.client;


import org.junit.Before;
import org.junit.Test;

/**
 * Created by csdev on 11/20/17.
 */
public class TestClientBTProxy {

    private SpyRequester requester;
    private ClientBTProxy btProxy;

    @Before
    public ClientBTProxy getBtProxy() {
        requester = new SpyRequester();
        btProxy = new ClientBTProxy(requester);
    }

    @Test
    public void shouldValidateRequestObjectCreated(){
        btProxy.getPlayerInTurn();

    }
}
