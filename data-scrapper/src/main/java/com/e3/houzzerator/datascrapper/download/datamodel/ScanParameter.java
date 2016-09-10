package com.e3.houzzerator.datascrapper.download.datamodel;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */
@EqualsAndHashCode
@ToString
@Slf4j
@XmlType(propOrder = {"name","range"})
public class ScanParameter {
    private static Logger logger = LoggerFactory.getLogger(ScanParameter.class);

    private Range range;
    private String name;

    public ScanParameter() {

    }
    public ScanParameter(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    @XmlElement(required = true)
    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
