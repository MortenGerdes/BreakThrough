package breakthrough.client;

import java.lang.reflect.Type;

/**
 * Created by csdev on 11/20/17.
 */
public class SpyRequester implements frs.broker.Requestor {

    String lastObjectId;
    String lastOperationName;
    Object[] lastArgument;
    Type lastType;

    @Override
    public <T> T sendRequestAndAwaitReply(String objectId, String operationName,
                                          Type typeOfReturnValue, Object... argument) {
        lastObjectId = objectId;
        lastOperationName = operationName;
        lastArgument = argument;
        lastType = typeOfReturnValue;
        return null;
    }
}
