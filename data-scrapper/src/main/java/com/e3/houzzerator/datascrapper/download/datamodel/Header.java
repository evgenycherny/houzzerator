package com.e3.houzzerator.datascrapper.download.datamodel;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.XmlElement;

/**
 * Author:  etshiorny
 * Date:    7/10/16.
 */
@EqualsAndHashCode
@ToString
@Slf4j
public class Header {

    private String key;
    private String value;

    @XmlElement
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlElement
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
