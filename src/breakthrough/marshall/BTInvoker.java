package breakthrough.marshall;

import frs.broker.Invoker;
import frs.broker.ReplyObject;

public class BTInvoker implements Invoker {
    @Override
    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        return null;
    }
}
