package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.helpers.BeanAssertion;
import com.e3.houzzerator.datascrapper.download.datamodel.Header;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class HeaderShould {
    @Test
    public void instanciate() {
        new Header();
    }
    @Test
    public void assertBean() {
        BeanAssertion.assertBeanProperties(Header.class);
        BeanAssertion.assertEqualsAndHashCode(Header.class);
        BeanAssertion.assertToString(Header.class);
    }
}

