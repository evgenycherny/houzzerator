package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.helpers.BeanAssertion;
import com.e3.houzzerator.datascrapper.download.helpers.RequestMatcher;
import com.e3.houzzerator.datascrapper.download.datamodel.Range;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanParameter;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanTemplate;
import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import lombok.SneakyThrows;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */

public class RestScannerShould {
    private RestScanner restScanner;

    @Mock private HttpClient httpClient;
    @Mock private SubstitutionHandler substitutionHandler;
    @Mock private ScanTemplate scanTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeSimpleRequest() throws Exception {
        when(scanTemplate.getParameters()).thenReturn(new ArrayList<ScanParameter>() {});
        when(scanTemplate.buildRequest(substitutionHandler)).thenReturn(prepareRequest("http://example.com/api/getSome"));
        restScanner = initializeRestScanner();
        restScanner.scan();

        verifyHttpClientCall("http://example.com/api/getSome");
    }

    @SneakyThrows
    private HttpUriRequest prepareRequest(String uri) {
        HttpGet request = new HttpGet();
        request.setURI(new URI(uri));
        return request;
    }
    /*@Test
    public void executeWithScanParameter() throws Exception {
        ScanTemplate template = new ScanTemplate();
        template.setUrl("http://example.com/api/getSome/${id}");

        List<ScanParameter> params = new ArrayList<>();
        params.add(new ScanParameter("id", new Range(1,2)));
        template.setParameters(params);

        restScanner = initializeRestScanner();
        restScanner.setScanTemplate(template);
        restScanner.scan();

        verifyHttpClientCall("http://example.com/api/getSome/1");
        verifyHttpClientCall("http://example.com/api/getSome/2");
    }

    @Test
    public void executeWithMultipleScanParameters() throws Exception {
        ScanTemplate template = new ScanTemplate();
        template.setUrl("http://example.com/api/getSome/${id1}/${id2}");

        List<ScanParameter> params = new ArrayList<>();
        params.add(new ScanParameter("id1", new Range(1,2)));
        params.add(new ScanParameter("id2", new Range(3,4)));
        template.setParameters(params);

        restScanner = initializeRestScanner();
        restScanner.scan();

        verifyHttpClientCall("http://example.com/api/getSome/1/3");
        verifyHttpClientCall("http://example.com/api/getSome/1/4");
        verifyHttpClientCall("http://example.com/api/getSome/2/3");
        verifyHttpClientCall("http://example.com/api/getSome/2/4");
    }*/
    @Test(expected=RestScannerException.class)
    public void handleInternalExceptionGracefully() throws Exception {
        RestScanner scanner = new RestScanner();
        scanner.scan();
    }


    private void verifyHttpClientCall(String expected) throws IOException {
        verify(httpClient, times(1))
                .execute(argThat(new RequestMatcher()
                        .withPath(expected)));
    }

    @Test
    public void assertBean() {
        BeanAssertion.assertBeanProperties(RestScanner.class);
        BeanAssertion.assertEqualsAndHashCode(RestScanner.class);
        BeanAssertion.assertToString(RestScanner.class);
    }

    private RestScanner initializeRestScanner() {
        RestScanner restScanner = new RestScanner();
        restScanner.setSubstitutionHandler(substitutionHandler);
        restScanner.setHttpClient(httpClient);
        restScanner.setScanTemplate(scanTemplate);
        return restScanner;
    }
}
