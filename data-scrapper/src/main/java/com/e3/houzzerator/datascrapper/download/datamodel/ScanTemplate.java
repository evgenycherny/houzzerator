package com.e3.houzzerator.datascrapper.download.datamodel;

import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  etshiorny
 * Date:    7/9/16.
 */
@EqualsAndHashCode
@ToString
@Slf4j
@XmlRootElement(name="scan")
@XmlType(propOrder = {"method","url", "headers","parameters"})
public class ScanTemplate {

    private String method;
    private String url;
    private List<Header> headers;
    private List<ScanParameter> parameters;

    public ScanTemplate() {
        this.headers = new ArrayList<>();
        this.parameters = new ArrayList<>();
    }

    @XmlElement(name="url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement(name = "header")
    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    @XmlElement(name = "parameter")
    public List<ScanParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScanParameter> parameters) {
        this.parameters = parameters;
    }

    @XmlElement
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @SneakyThrows
    public HttpUriRequest buildRequest(SubstitutionHandler substitutor) {
        HttpRequestBase request = buildBasicHttpRequest();
        generateSubstitutedUrl(request, substitutor);
        generateSubstitutedHeaders(request, substitutor);
        return request;
    }

    private void generateSubstitutedHeaders(HttpRequestBase request, SubstitutionHandler substitutor) {
        for(Header header: headers) {
            String name = substitutor.substitute(header.getKey());
            String value = substitutor.substitute(header.getValue());
            request.setHeader(name, value);
        }
    }

    private void generateSubstitutedUrl(HttpRequestBase request, SubstitutionHandler substitutor) throws URISyntaxException {
        request.setURI(new URI(substitutor.substitute(url)));
    }

    private HttpRequestBase buildBasicHttpRequest() {
        return new HttpRequestBase() {
                @Override
                public String getMethod() {
                    return method;
                }
            };
    }
}
