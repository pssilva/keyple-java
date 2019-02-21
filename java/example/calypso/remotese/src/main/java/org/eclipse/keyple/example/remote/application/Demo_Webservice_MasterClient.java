/********************************************************************************
 * Copyright (c) 2018 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/
package org.eclipse.keyple.example.remote.application;

import org.eclipse.keyple.example.remote.transport.wspolling.WsPollingFactory;
import org.eclipse.keyple.plugin.remotese.transport.factory.TransportFactory;

/**
 * Demo Web Service with jdk http client library The master device uses the webservice client
 * whereas the slave device uses the webservice server
 */
public class Demo_Webservice_MasterClient {

    public static void main(String[] args) throws Exception {

        TransportFactory factory = new WsPollingFactory(); // HTTP Web Polling

        // Launch the server thread
        Demo_Threads.startServer(false, factory);

        Thread.sleep(1000);

        // Launch the client thread
        Demo_Threads.startClient(true, factory);
    }
}
