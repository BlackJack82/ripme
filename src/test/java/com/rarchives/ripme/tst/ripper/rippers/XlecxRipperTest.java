package com.rarchives.ripme.tst.ripper.rippers;

import java.io.IOException;
import java.net.URL;

import com.rarchives.ripme.ripper.rippers.XlecxRipper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class XlecxRipperTest extends RippersTest {
    @Test
    public void testAlbum() throws IOException {
        XlecxRipper ripper = new XlecxRipper(new URL("http://xlecx.org/4274-black-canary-ravished-prey.html"));
        testRipper(ripper);
    }
}
