package frs.broker;

import java.lang.reflect.Type;

/**
 * The Requestor role in the Broker pattern.
 * <p>
 * Responsibility: To encapsulate marshalling and sending
 * request objects on behalf of the client proxies.
 * The Requestor sends messages using its associated
 * ClientRequestHandler.
 */
public interface Requestor {
  
  /**
   * breakthrough.marshall the given operation and its parameters into a request object, send
   * it to the remote component, and interpret the answer and convert it back
   * into the return type of generic type T
   * 
   * @param <T>
   *          generic type of the return value
   * @param objectId
   *          the object that this request relates to; not that this may not
   *          necessarily just be the object that the method is called upon
   * @param operationName
   *          the operation (=method) to invoke
   * @param typeOfReturnValue
   *          the java reflection type of the returned type
   * @param argument
   *          the arguments to the method call
   * @return the return value of the type given by typeOfReturnValue
   */
  <T> T sendRequestAndAwaitReply(String objectId, String operationName, 
      Type typeOfReturnValue, Object... argument);

}
