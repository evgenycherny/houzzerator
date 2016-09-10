package com.e3.houzzerator.datascrapper.download.model;

import com.e3.houzzerator.datascrapper.download.RestScanner;
import com.e3.houzzerator.datascrapper.download.ScanContext;

/**
 * Author:  etshiorny
 * Date:    7/5/16.
 */
public interface IScannerBuilder {
    RestScanner build();
}
