package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class ScanParameterShould {
    @Test
    public void instanciate() {
        ScanParameter param = new ScanParameter("param", new Range(1,2));
        Assert.assertEquals("param", param.getName());
        Assert.assertNotNull(param.getRange());
    }
    @Test
    public void allowUsingRangeSetter() {
        ScanParameter param = new ScanParameter("param", new Range(1,2));
        param.setRange(new Range(3,4));
        Assert.assertNotNull(param.getRange());
        Assert.assertEquals(3,param.getRange().getFrom());
        Assert.assertEquals(4,param.getRange().getTo());
    }
}

