package com.e3.houzzerator.datascrapper.download.helpers;

import org.apache.http.client.methods.HttpUriRequest;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:  etshiorny
 * Date:    7/7/16.
 */
public class IntArrayHelper {
    private List<Integer> list;

    public IntArrayHelper() {
        list = new ArrayList<>();
    }
    public void push(int i) {
        list.add(i);
    }
    public int[] toArray() {
        int[] res = new int[list.size()];
        for (int i=0;i<res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
