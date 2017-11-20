package breakthrough.main;

import breakthrough.client.ClientBTProxy;
import breakthrough.domain.Breakthrough;
import breakthrough.domain.BreakthroughSurrogate;
import breakthrough.ui.ClientInterpreter;
import frs.broker.ClientRequestHandler;
import frs.broker.Requestor;
import frs.broker.ipc.http.UriTunnelClientRequestHandler;
import frs.broker.ipc.socket.SocketClientRequestHandler;
import frs.broker.marshall.json.StandardJSONRequestor;

/** client for breakthrough, using socket communication.
 * Hardcoded for port 37321, host given as argument.
 */
public class ClientMainSocket {
  public static void main(String args[]) {
    new ClientMainSocket(args[0], 37321);
  }

  public ClientMainSocket(String host, int port) {
    // Create the game
    ClientRequestHandler crh = new SocketClientRequestHandler(host, port);

    StandardJSONRequestor requester = new StandardJSONRequestor(crh);
    ClientBTProxy proxy = new ClientBTProxy(requester);
    Breakthrough game = new BTProxyGame(proxy);

    // Welcome
    System.out.println("=== client Socket. Host = "
            + host + " ===");
    // And start the interpreter...
    ClientInterpreter interpreter =
            new ClientInterpreter(game,
                    System.in, System.out);
    interpreter.readEvalLoop();
  }
}
