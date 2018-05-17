/*
 * Copyright (c) 2018 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License version 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 */

package org.keyple.calypso.commands.csm.parser;


import org.keyple.commands.AbstractApduResponseParser;
import org.keyple.seproxy.ApduResponse;

/**
 * Digest authentication response parser. See specs: Calypso / page 54 / 7.4.2 - Session MAC
 * computation
 */
public class DigestAuthenticateRespPars extends AbstractApduResponseParser {
    /**
     * Instantiates a new DigestAuthenticateRespPars.
     *
     * @param response from the CSM DigestAuthenticateCmdBuild
     */
    public DigestAuthenticateRespPars(ApduResponse response) {
        super(response);
    }
}