package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzerator.installcert.download.Range;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class RangeShould {
    @Test
    public void instanciate() {
        Range range = new Range(1,2);
        Assert.assertEquals(1,range.getFrom());
        Assert.assertEquals(2,range.getTo());
    }

    @Test
    public void instanciateWithStep() {
        Range range = new Range(1, 4, 2);
        Assert.assertEquals(1,range.getFrom());
        Assert.assertEquals(4,range.getTo());
        Assert.assertEquals(2,range.getStep());
    }
    @Test
    public void allowUsingSetters() {
        Range range = new Range(1,2);
        range.setFrom(3);
        range.setTo(10);
        range.setStep(2);
        Assert.assertEquals(3,range.getFrom());
        Assert.assertEquals(10,range.getTo());
        Assert.assertEquals(2,range.getStep());
    }
}

