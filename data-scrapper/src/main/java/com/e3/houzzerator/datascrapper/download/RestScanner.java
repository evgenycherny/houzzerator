package com.e3.houzzerator.datascrapper.download;

import lombok.Data;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */

@Data
public class RestScanner {
    private ParametrizedHttpGet request;
    private List<ScanParameter> scanParameters;

    @Autowired
    private HttpClient httpClient;

    public RestScanner() {
        this.scanParameters = new ArrayList<>();
    }
    public RestScanner(ParametrizedHttpGet request) {
        this.request = request;
        this.scanParameters = new ArrayList<>();
    }


    public void scan() {
        try {
            doScan();
        } catch (Exception ex) {
            throw new RestScannerException(ex);
        }
    }


    private void doScan() throws Exception {
        List<ScanContext> list = populateListWithContextMutationsForAllScanParameters();

        for (ScanContext ctx: list) {
            executeRequestWithGivenContext(ctx);
        }

    }

    private void executeRequestWithGivenContext(ScanContext ctx) throws IOException {
        HttpGet req = createRequestFromParametrizedRequest(ctx);
        httpClient.execute(req);
    }

    private HttpGet createRequestFromParametrizedRequest(ScanContext ctx) {
        request.setContext(ctx);

        HttpGet req =  new HttpGet();
        req.setHeaders(request.getAllHeaders());
        req.setURI(request.getURI());
        return req;
    }

    private List<ScanContext> populateListWithContextMutationsForAllScanParameters() {
        List<ScanContext> list = prepareInitialContextList();
        for (ScanParameter param: scanParameters) {
            list = addMutationsForSingleScanParameter(list, param);
        }
        return list;
    }

    private List<ScanContext> prepareInitialContextList() {
        List<ScanContext> list = new ArrayList<>();
        list.add(new ScanContext());
        return list;
    }

    private List<ScanContext> addMutationsForSingleScanParameter(List<ScanContext> inList, ScanParameter param) {
        List<ScanContext> outList = new ArrayList<>();
        for (ScanContext inContext: inList) {
            Range range = param.getRange();
            for (int i=range.getFrom();i<=range.getTo(); i+=range.getStep()) {
                ScanContext context = (ScanContext)inContext.clone();
                context.put(param.getName(), Integer.valueOf(i).toString());
                outList.add(context);
            }
        }
        return outList;
    }

}
