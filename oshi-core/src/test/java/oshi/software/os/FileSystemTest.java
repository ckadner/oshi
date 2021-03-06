/**
 * Oshi (https://github.com/dblock/oshi)
 *
 * Copyright (c) 2010 - 2017 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.software.os;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import oshi.SystemInfo;

/**
 * Test File System
 */
public class FileSystemTest {

    /**
     * Test file system.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testFileSystem() throws IOException {
        SystemInfo si = new SystemInfo();
        FileSystem filesystem = si.getOperatingSystem().getFileSystem();
        assertTrue(filesystem.getOpenFileDescriptors() >= 0L);
        assertTrue(filesystem.getMaxFileDescriptors() >= 0L);
        OSFileStore[] fs = filesystem.getFileStores();
        for (OSFileStore store : fs) {
            assertNotNull(store.getName());
            assertNotNull(store.getVolume());
            assertNotNull(store.getDescription());
            assertNotNull(store.getType());
            assertNotNull(store.getMount());
            assertNotNull(store.getUUID());
            assertTrue(store.getTotalSpace() >= 0);
            assertTrue(store.getUsableSpace() <= store.getTotalSpace());

            store.setName("name");
            store.setVolume("volume");
            store.setDescription("desc");
            store.setType("type");
            store.setMount("mount");
            store.setUUID("uuid");
            store.setTotalSpace(12345L);
            store.setUsableSpace(1234L);

            assertEquals("name", store.getName());
            assertEquals("volume", store.getVolume());
            assertEquals("desc", store.getDescription());
            assertEquals("type", store.getType());
            assertEquals("mount", store.getMount());
            assertEquals("uuid", store.getUUID());
            assertEquals(12345L, store.getTotalSpace());
            assertEquals(1234L, store.getUsableSpace());
        }
    }
}
