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

import org.eclipse.keyple.example.remote.transport.websocket.WskFactory;
import org.eclipse.keyple.plugin.remotese.transport.TransportFactory;

/**
 * Demo websocket The master device uses the websocket client whereas the slave device uses the
 * websocket server
 */
public class Demo_Websocket_MasterClient {

    public static void main(String[] args) throws Exception {

        // Create the procotol factory
        TransportFactory factory = new WskFactory(false); // Web socket

        // Launch the server thread
        Demo_Threads.startServer(false, factory);

        Thread.sleep(1000);

        // Launch the client thread
        Demo_Threads.startClient(true, factory);
    }
}