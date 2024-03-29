package breakthrough.doubles;

import frs.broker.ClientRequestHandler;
import frs.broker.Invoker;
import frs.broker.ReplyObject;
import frs.broker.RequestObject;

/**
 * A test double implementation of the ClientRequestHandler which simply
 * forwards any calls directly to an associated invoker, thus allowing the full
 * stack of remote calls implementations to be tested without the need of real
 * IPC.
 * <p>
 * Note that no ServerRequestHandler is involved as the server side IPC is
 * 'nothing' in case of normal method calls.
 * <p>
 *   Also acts as a spy to allow inspecting the request and reply objects
 *   being passed.

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published by CRC Press.
 Author:
 Henrik B Christensen
 Department of Computer Science
 Aarhus University

 Please visit http://www.baerbak.com/ for further information.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
public class LocalMethodCallClientRequestHandler implements ClientRequestHandler {

    private final Invoker invoker;
    private ReplyObject lastReply;
    private RequestObject lastRequest;

    public LocalMethodCallClientRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public ReplyObject sendToServer(RequestObject requestObject) {
        lastRequest = requestObject;
        // The send to the server can be mimicked by a direct method call
        lastReply = invoker.handleRequest(requestObject.getObjectId(),
                requestObject.getOperationName(),
                requestObject.getPayload());
        return lastReply;
    }

    public ReplyObject getLastReply() {
        return lastReply;
    }

    public RequestObject getLastRequest() {
        return lastRequest;
    }

}
