/*
 * Copyright (c) 2018 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License version 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 */

package org.keyple.plugin.pcsc;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keyple.seproxy.ProxyReader;
import org.keyple.seproxy.exceptions.IOReaderException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SmartCardIOPluginTest {
    Logger logger = LogManager.getLogger(SmartCardIOPluginTest.class);

    @InjectMocks
    @Spy
    private PcscPlugin plugin;

    PcscPlugin smartCardPluginSpyied;

    @Mock
    List<ProxyReader> cardTerminals;

    @Mock
    CardTerminal cardTerminal;

    // fclairamb (2018-03-01): This test made no sense, we were using a CardTerminals which is
    // PCSC-specific when we
    // should be returning ProxyReaders
    @Before
    public void setUp() throws IOReaderException, CardException {
        // smartCardPluginSpyied = spy(plugin);
        /*
         * when(plugin.getReaders()).thenReturn(cardTerminals); List<CardTerminal> terms = new
         * ArrayList<CardTerminal>(); terms.add(cardTerminal);
         * when(cardTerminals.list()).thenReturn(terms);
         * when(cardTerminal.getName()).thenReturn("PcscPlugin");
         */
    }

    @Test
    public void testGetReaders() throws CardException, IOReaderException {
        assertEquals(plugin.getReaders().size(), 1);
        assertEquals("PcscPlugin", plugin.getName());
    }

}