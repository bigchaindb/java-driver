package com.authenteq.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ScannerUtilsTest {

    @Test
    public void testMonitorExit() {
        InputStream fakeIn = new ByteArrayInputStream("exit".getBytes());
        System.setIn(fakeIn);
        ScannerUtil.monitorExit();
    }
}
