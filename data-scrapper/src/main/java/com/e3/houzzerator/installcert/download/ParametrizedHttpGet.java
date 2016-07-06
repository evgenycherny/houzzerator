package com.e3.houzzerator.installcert.download;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  etshiorny
 * Date:    6/30/16.
 */
public class ParametrizedHttpGet extends HttpGet implements HttpUriRequest {
    private String template;
    private DefaultSubstitutor substitutor;

    public void setContext(ScanContext context) {
        substitutor = new DefaultSubstitutor(context);
    }

    public ParametrizedHttpGet() {
        this(new ScanContext());
    }
    private ParametrizedHttpGet(ScanContext context) {
        substitutor = new DefaultSubstitutor(context);
    }

    public void setUriTemplate(String template) {
        this.template = template;
    }

    @Override
    public URI getURI() {
        return URI.create(substitutor.substitute(template));
    }

    @Override
    public Header[] getAllHeaders() {
        return substituteHeaders(this.headergroup.getAllHeaders());
    }

    @Override
    public Header[] getHeaders(String name) {
        return substituteHeaders(this.headergroup.getHeaders(name));
    }

    @Override
    public Header getFirstHeader(String name) {
        return substituteHeader(this.headergroup.getFirstHeader(name));
    }

    @Override
    public Header getLastHeader(String name) {
        return substituteHeader(this.headergroup.getLastHeader(name));
    }
    private Header substituteHeader(Header header) {
        return new BasicHeader(header.getName(), substitutor.substitute(header.getValue()));
    }
    private Header[] substituteHeaders(Header[] headers) {
        List<Header> newHeaders = new ArrayList<>();

        for (Header header: headers) {
            newHeaders.add(substituteHeader(header));
        }
        return newHeaders.toArray(new Header[0]);
    }

}

