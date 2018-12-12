package org.eclipse.keyple.plugin.remotese.nativese.method;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.keyple.plugin.remotese.nativese.NativeReaderServiceImpl;
import org.eclipse.keyple.plugin.remotese.transport.*;
import org.eclipse.keyple.plugin.remotese.transport.json.JsonParser;
import org.eclipse.keyple.seproxy.event.ObservableReader;
import org.eclipse.keyple.seproxy.exception.KeypleReaderException;
import org.eclipse.keyple.seproxy.message.ProxyReader;
import org.eclipse.keyple.seproxy.message.SeRequestSet;
import org.eclipse.keyple.seproxy.message.SeResponseSet;
import org.eclipse.keyple.transaction.SelectionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmSetDefaultSelectionRequestExecutor implements RemoteMethodExecutor {

    private static final Logger logger = LoggerFactory.getLogger(RmSetDefaultSelectionRequestExecutor.class);

    private final NativeReaderServiceImpl nativeReaderService;

    public RmSetDefaultSelectionRequestExecutor(NativeReaderServiceImpl nativeReaderService) {
        this.nativeReaderService = nativeReaderService;
    }


    @Override
    public TransportDto execute(TransportDto transportDto) {
        KeypleDto keypleDto = transportDto.getKeypleDTO();

        // Extract info from keypleDto
        String body = keypleDto.getBody();
        JsonObject jsonObject = JsonParser.getGson().fromJson(body, JsonObject.class);

        JsonPrimitive selectionRequestJson = jsonObject.getAsJsonPrimitive("selectionRequest");
        JsonPrimitive notificationModeJson = jsonObject.getAsJsonPrimitive("notificationMode");

        logger.debug(selectionRequestJson.getAsString());
        logger.debug(notificationModeJson.getAsString());

        SelectionRequest selectionRequest = JsonParser.getGson().fromJson(selectionRequestJson.getAsString(), SelectionRequest.class);
        ObservableReader.NotificationMode notificationMode = ObservableReader.NotificationMode.get(notificationModeJson.getAsString());

        String nativeReaderName = keypleDto.getNativeReaderName();
        logger.debug("Execute locally SetDefaultSelectionRequest : {} - {}", notificationMode, selectionRequest );

        try {
            // find native reader by name
            ProxyReader reader = nativeReaderService.findLocalReader(nativeReaderName);

            if(reader instanceof ObservableReader){
                logger.debug(reader.getName() + " is an ObservableReader, invoke setDefaultSelectionRequest on it");
                ((ObservableReader) reader).setDefaultSelectionRequest(selectionRequest,notificationMode);

                // prepare response
                String parseBody = "{}";
                return transportDto
                        .nextTransportDTO(new KeypleDto(RemoteMethod.DEFAULT_SELECTION_REQUEST.getName(),
                                parseBody, false, keypleDto.getSessionId(), nativeReaderName,
                                keypleDto.getVirtualReaderName(), keypleDto.getNodeId()));
            }else{
                throw new KeypleReaderException("Reader is not observable, can not invoke SetDefaultSelectionRequest on " + nativeReaderName);
            }


        } catch (KeypleReaderException e) {
            // if an exception occurs, send it into a keypleDto to the Master
            return transportDto.nextTransportDTO(KeypleDtoHelper.ExceptionDTO(
                    RemoteMethod.DEFAULT_SELECTION_REQUEST.getName(), e, keypleDto.getSessionId(),
                    nativeReaderName, keypleDto.getVirtualReaderName(), keypleDto.getNodeId()));
        }
    }
}
