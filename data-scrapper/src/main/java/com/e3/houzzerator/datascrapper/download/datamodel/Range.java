package com.e3.houzzerator.datascrapper.download.datamodel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import java.util.function.Consumer;

/**
 * Author:  etshiorny
 * Date:    6/26/16.
 */
@EqualsAndHashCode
@ToString
@Slf4j
public class Range {
    private int from;
    private int to;
    private int step;

    public Range() { step = 1; }
    public Range(int from, int to) {
        this(from, to, 1);
    }

    public Range(int from, int to, int step) {
        this.from = from;
        this.to = to;
        this.step = step;
    }

    @XmlElement(required = true)
    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @XmlElement(required = true)
    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @XmlElement
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void iterate(Consumer<Integer> consumer) {
        if (step==0) return;

        for (int i=from;to(i); i+=step)
            consumer.accept(i);
    }
    private boolean to(int i) {
        return (step>0 && i<=to) || (step<0 && i>=to);
    }
}
