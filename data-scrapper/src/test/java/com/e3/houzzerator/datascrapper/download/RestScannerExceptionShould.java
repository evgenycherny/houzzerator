package com.e3.houzzerator.datascrapper.download;

import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class RestScannerExceptionShould {
    @Test(expected = RestScannerException.class)
    public void inheritSuperclassBehavior() {
        throw new RestScannerException(new Exception());
    }
}

