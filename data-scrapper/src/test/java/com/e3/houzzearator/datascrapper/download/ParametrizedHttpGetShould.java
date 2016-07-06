package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzerator.installcert.download.ParametrizedHttpGet;
import com.e3.houzzerator.installcert.download.ScanContext;
import org.apache.http.message.BasicHeader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class ParametrizedHttpGetShould {
    @Test
    public void returnNonParametrizedUriAsIs() {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        request.setUriTemplate("http://example.com/api/getSome");
        request.addHeader("Header1", "value1");
        request.addHeader("Header2", "value2");

        Assert.assertEquals("http://example.com/api/getSome",request.getURI().toString());
    }


    @Test
    public void returnSubstitutedUri() {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        ScanContext context = new ScanContext();
        context.put("id","someId");
        request.setContext(context);
        request.setUriTemplate("http://example.com/api/getSome/${id}");

        Assert.assertEquals("http://example.com/api/getSome/someId",request.getURI().toString());
    }

    @Test
    public void returnSubstituteHeaders() {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        ScanContext context = new ScanContext();
        context.put("value1","someValue");
        request.setContext(context);
        request.setUriTemplate("http://example.com/api/getSome");
        request.addHeader(new BasicHeader("Header1", "${value1}"));

        Assert.assertEquals("someValue",request.getHeaders("Header1")[0].getValue());
        Assert.assertEquals("someValue",request.getFirstHeader("Header1").getValue());
        Assert.assertEquals("someValue",request.getLastHeader("Header1").getValue());
        Assert.assertEquals("someValue",request.getAllHeaders()[0].getValue());
    }

    @Test
    public void returnSubstituteMultipleHeaders() {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        ScanContext context = new ScanContext();
        context.put("value1","someValue1");
        context.put("value2","someValue2");
        request.setContext(context);
        request.setUriTemplate("http://example.com/api/getSome");
        request.addHeader(new BasicHeader("Header1", "${value1}"));
        request.addHeader(new BasicHeader("Header1", "${value2}"));

        Assert.assertEquals(2,request.getHeaders("Header1").length);
        Assert.assertEquals("someValue1",request.getFirstHeader("Header1").getValue());
        Assert.assertEquals("someValue2",request.getLastHeader("Header1").getValue());
    }

    @Test
    public void returnSubstitutedParams() {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        ScanContext context = new ScanContext();
        context.put("param1","someParam");
        request.setContext(context);
        request.setUriTemplate("http://example.com/api/getSome?paramName=${param1}");

        Assert.assertEquals("paramName=someParam",request.getURI().getQuery());
    }
}

