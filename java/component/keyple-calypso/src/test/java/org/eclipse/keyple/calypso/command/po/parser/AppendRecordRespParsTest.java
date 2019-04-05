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
package org.eclipse.keyple.calypso.command.po.parser;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.keyple.command.AbstractApduResponseParser;
import org.eclipse.keyple.seproxy.message.ApduResponse;
import org.eclipse.keyple.seproxy.message.SeResponse;
import org.eclipse.keyple.seproxy.message.SeResponseSet;
import org.eclipse.keyple.seproxy.message.SelectionStatus;
import org.eclipse.keyple.util.ByteArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppendRecordRespParsTest {

    @Test
    public void appendRecordRespPars() {
        List<ApduResponse> responses = new ArrayList<ApduResponse>();
        ApduResponse apduResponse = new ApduResponse(new byte[] {90, 0}, null);
        responses.add(apduResponse);
        SeResponseSet seResponse =
                new SeResponseSet(new SeResponse(true, true,
                        new SelectionStatus(null,
                                new ApduResponse(ByteArrayUtils.fromHex("9000"), null), true),
                        responses));

        AbstractApduResponseParser apduResponseParser = new AppendRecordRespPars();
        apduResponseParser
                .setApduResponse(seResponse.getSingleResponse().getApduResponses().get(0));
        Assert.assertArrayEquals(new byte[] {90, 0},
                apduResponseParser.getApduResponse().getBytes());
    }
}
