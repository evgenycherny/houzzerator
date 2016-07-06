package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.ScanContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class ScanContextShould {
    @Test
    public void inheritHashMapBehavior() {
        ScanContext context = new ScanContext();
        context.put("key","value");
        Assert.assertEquals("value", context.get("key"));
    }
}

