package com.e3.houzzerator.installcert.download;

import lombok.Data;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */
@Data
public class Range {
    private int from;
    private int to;
    private int step;

    public Range(int from, int to) {
        this(from, to, 1);
    }
    public Range(int from, int to, int step) {
        this.from = from;
        this.to = to;
        this.step = step;
    }
}
