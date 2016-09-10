package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.helpers.BeanAssertion;
import com.e3.houzzerator.datascrapper.download.datamodel.Range;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanParameter;
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
    public void assertBean() {
        BeanAssertion.assertBeanProperties(ScanParameter.class);
        BeanAssertion.assertEqualsAndHashCode(ScanParameter.class);
        BeanAssertion.assertToString(ScanParameter.class);
    }
    @Test
    public void allowUsingRangeSetter() {
        ScanParameter param = new ScanParameter("param", new Range(1,2));
        param.setRange(new Range(3,4));
        Assert.assertNotNull(param.getRange());
        Assert.assertEquals(3,param.getRange().getFrom());
        Assert.assertEquals(4,param.getRange().getTo());
    }
    @Test
    public void provideEqualsMethod() {
        ScanParameter p1 = new ScanParameter("p", new Range(1, 2));
        ScanParameter p2 = new ScanParameter("p", new Range(1, 2));
        ScanParameter p3 = new ScanParameter("p", new Range(1, 3));
        ScanParameter p4 = new ScanParameter("p1", new Range(1, 2));

        Assert.assertEquals(p1, p2);
        Assert.assertNotEquals(p1, p3);
        Assert.assertNotEquals(p1, p4);
    }
}

