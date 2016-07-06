package com.e3.houzzerator.installcert.download;

import lombok.Data;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */
@Data
public class ScanParameter {
    private Range range;
    private String name;

    public ScanParameter(String name, Range range) {
        this.name = name;
        this.range = range;
    }
}
