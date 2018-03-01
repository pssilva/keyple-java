/*
 * Copyright (c) 2018 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License version 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 */

package org.keyple.seproxy;

import java.nio.ByteBuffer;

/**
 * Single APDU request wrapper
 */
public class ApduRequest extends AbstractApduBuffer {

    /**
     * a ‘case 4’ flag in order to explicitly specify, if it’s expected that the APDU command
     * returns data → this flag is required to manage revision 2.4 Calypso Portable Objects and
     * ‘S1Dx’ SAMs that presents a behaviour not compliant with ISO 7816-3 in contacts mode (not
     * returning the 61XYh status).
     */
    private boolean case4;

    /**
     * the constructor called by a ticketing application in order to build the APDU command requests
     * to push to the ProxyReader.
     *
     * @param case4 the case 4
     */
    public ApduRequest(ByteBuffer buffer, boolean case4) {
        super(buffer);
        this.case4 = case4;
    }

    public ApduRequest(byte[] data, boolean case4) {
        super(data);
        this.case4 = case4;
    }

    public ApduRequest(byte[] data, int offset, int length, boolean case4) {
        super(data, offset, length);
        this.case4 = case4;
    }

    public ApduRequest() {}

    /**
     * Checks if is case 4.
     *
     * @return the case4 flag.
     */
    public boolean isCase4() {
        return case4;
    }

    public void put(byte b) {
        buffer.put(b);
    }

    public void put(byte[] a) {
        buffer.put(a);
    }

    @Override
    public String toString() {
        ByteBuffer b;

        // TODO: Buffer will be read only, as such this code makes no sense anymore
        if (buffer.position() > 0) {
            b = buffer.duplicate();
            b.limit(buffer.position());
        } else {
            b = buffer;
        }

        return "APDU Request " + ByteBufferUtils.toHex(b);
    }
}