package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.helpers.BeanAssertion;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanTemplate;
import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import static org.mockito.Mockito.when;

/**
 * Author:  etshiorny
 * Date:    7/9/16.
 */
public class ScanTemplateShould {
    @Test
    public void unmarshallFromXml() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("scan.xml");
        ScanTemplate scanTemplate = unmarshall(in);

        Assert.assertEquals("GET",scanTemplate.getMethod());
        Assert.assertEquals("http://example.com/get/${id}",scanTemplate.getUrl());

        Assert.assertEquals("Content-Type",scanTemplate.getHeaders().get(0).getKey());
        Assert.assertEquals("application/json",scanTemplate.getHeaders().get(0).getValue());
        Assert.assertEquals("Content-Length",scanTemplate.getHeaders().get(1).getKey());
        Assert.assertEquals("1",scanTemplate.getHeaders().get(1).getValue());

        Assert.assertEquals("id",scanTemplate.getParameters().get(0).getName());
        Assert.assertEquals(1,scanTemplate.getParameters().get(0).getRange().getFrom());
        Assert.assertEquals(2,scanTemplate.getParameters().get(0).getRange().getTo());
        Assert.assertEquals(1,scanTemplate.getParameters().get(0).getRange().getStep());

        Assert.assertEquals("id1",scanTemplate.getParameters().get(1).getName());
        Assert.assertEquals(1,scanTemplate.getParameters().get(1).getRange().getFrom());
        Assert.assertEquals(2,scanTemplate.getParameters().get(1).getRange().getTo());
        Assert.assertEquals(1,scanTemplate.getParameters().get(1).getRange().getStep());
    }

    @Test
    public void assertBean() {
        BeanAssertion.assertBeanProperties(ScanTemplate.class);
        BeanAssertion.assertEqualsAndHashCode(ScanTemplate.class);
        BeanAssertion.assertToString(ScanTemplate.class);
    }

    private ScanTemplate unmarshall(InputStream in) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ScanTemplate.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (ScanTemplate) jaxbUnmarshaller.unmarshal(in);
    }

    @Test
    public void buildRequestWith() {
        ScanTemplate scanTemplate = new ScanTemplate();
        scanTemplate.setMethod("GET");
        SubstitutionHandler handler = Mockito.mock(SubstitutionHandler.class);
        when(handler.substitute(Mockito.anyString())).thenReturn("http://example.com/api/get");

        HttpUriRequest request = scanTemplate.buildRequest(handler);
        Assert.assertEquals("GET", request.getMethod());
        Assert.assertEquals("http://example.com/api/get", request.getURI().toString());

    }
}
