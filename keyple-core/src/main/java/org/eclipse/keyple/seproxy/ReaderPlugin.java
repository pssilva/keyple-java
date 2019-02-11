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
package org.eclipse.keyple.seproxy;

import java.util.SortedSet;
import org.eclipse.keyple.seproxy.exception.KeypleReaderException;
import org.eclipse.keyple.seproxy.exception.KeypleReaderNotFoundException;
import org.eclipse.keyple.util.NameableConfigurable;


/**
 * Card readers plugin interface.
 */
public interface ReaderPlugin extends NameableConfigurable, Comparable<ReaderPlugin> {

    /**
     * Gets the readers.
     *
     * @return the ‘unique’ name of the readers’ plugin.
     * @throws KeypleReaderException if the list of readers has not been initialized
     */
    SortedSet<? extends ProxyReader> getReaders() throws KeypleReaderException;

    /**
     * Gets the reader whose name is provided as an argument
     * 
     * @param name of the reader
     * @return the ProxyReader object.
     * @throws KeypleReaderNotFoundException if the wanted reader is not found
     */
    ProxyReader getReader(String name) throws KeypleReaderNotFoundException;
}