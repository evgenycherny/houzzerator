package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzearator.datascrapper.download.matchers.RequestMatcher;
import com.e3.houzzerator.datascrapper.download.*;
import org.apache.http.client.HttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */

public class RestScannerShould {
    private RestScanner restScanner;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void executeSimpleRequest() throws Exception {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        request.setUriTemplate("http://example.com/api/getSome");

        restScanner = new RestScanner(request);
        restScanner.setHttpClient(httpClient);

        restScanner.scan();
        verifyHttpClientCall("http://example.com/api/getSome");
    }
    @Test
    public void executeWithScanParameter() throws Exception {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        request.setUriTemplate("http://example.com/api/getSome/${id}");

        restScanner = new RestScanner(request);
        restScanner.setHttpClient(httpClient);
        List<ScanParameter> params = new ArrayList<>();
        params.add(new ScanParameter("id", new Range(1,2)));
        restScanner.setScanParameters(params);

        restScanner.scan();

        verifyHttpClientCall("http://example.com/api/getSome/1");
        verifyHttpClientCall("http://example.com/api/getSome/2");
    }

    @Test
    public void executeWithMultipleScanParameters() throws Exception {
        ParametrizedHttpGet request = new ParametrizedHttpGet();
        request.setUriTemplate("http://example.com/api/getSome/${id1}/${id2}");

        restScanner = new RestScanner(request);
        restScanner.setHttpClient(httpClient);
        List<ScanParameter> params = new ArrayList<>();
        params.add(new ScanParameter("id1", new Range(1,2)));
        params.add(new ScanParameter("id2", new Range(3,4)));
        restScanner.setScanParameters(params);

        restScanner.scan();

        verifyHttpClientCall("http://example.com/api/getSome/1/3");
        verifyHttpClientCall("http://example.com/api/getSome/1/4");
        verifyHttpClientCall("http://example.com/api/getSome/2/3");
        verifyHttpClientCall("http://example.com/api/getSome/2/4");
    }
    @Test(expected=RestScannerException.class)
    public void handleInternalExceptionGracefully() throws Exception {
        RestScanner scanner = new RestScanner();
        scanner.scan();
    }

    @Test
    public void allowUsingDefaultContructorAndRequestSetter() throws Exception {
        restScanner = new RestScanner();
        Assert.assertNull(restScanner.getRequest());

        restScanner.setHttpClient(httpClient);
        Assert.assertNotNull(restScanner.getHttpClient());

        restScanner.setRequest(new ParametrizedHttpGet());
        Assert.assertNotNull(restScanner.getRequest());
    }

    @Test
    public void defaultScanParametersList() throws Exception {
        restScanner = new RestScanner();
        Assert.assertNotNull(restScanner.getScanParameters());
    }
    @Test
    public void allowSetGetScanParamatersList() throws Exception {
        restScanner = new RestScanner();
        List<ScanParameter> list = new ArrayList<>();
        list.add(new ScanParameter("param", new Range(1,2)));
        restScanner.setScanParameters(list);
        Assert.assertNotNull(restScanner.getScanParameters());
        Assert.assertEquals(1, restScanner.getScanParameters().size());
    }


    private void verifyHttpClientCall(String expected) throws IOException {
        verify(httpClient, times(1))
                .execute(argThat(new RequestMatcher()
                        .withPath(expected)));
    }
}
