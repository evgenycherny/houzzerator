package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.datamodel.Range;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanParameter;
import com.e3.houzzerator.datascrapper.download.datamodel.ScanTemplate;
import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */

@Data
@Slf4j
public class RestScanner {

    private ScanTemplate scanTemplate;

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private SubstitutionHandler substitutionHandler;

    public RestScanner() {

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
        substitutionHandler.setContext(ctx);
        HttpUriRequest req = scanTemplate.buildRequest(substitutionHandler);
        httpClient.execute(req);
    }

    private List<ScanContext> populateListWithContextMutationsForAllScanParameters() {
        List<ScanContext> list = prepareInitialContextList();
        for (ScanParameter param: scanTemplate.getParameters()) {
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
            range.iterate((i) -> {
                ScanContext context = (ScanContext)inContext.clone();
                context.put(param.getName(), i.toString());
                outList.add(context);
            });
        }
        return outList;
    }
}
