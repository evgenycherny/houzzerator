package com.e3.houzzearator.datascrapper.download;

import com.e3.houzzerator.installcert.download.MissingSubstitutionParameterException;
import com.e3.houzzerator.installcert.download.ScanContext;
import com.e3.houzzerator.installcert.download.DefaultSubstitutor;
import com.e3.houzzerator.installcert.download.model.ISubstitutor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class DefaultSubstitutorShould {
    @Test
    public void returnNonParametrizedStringAsIs() {
        ScanContext context = new ScanContext();
        DefaultSubstitutor substitutor = new DefaultSubstitutor(context);
        Assert.assertEquals("abcd",substitutor.substitute("abcd"));
    }

    @Test
    public void substituteSingleParameter() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        ISubstitutor substitutor = new DefaultSubstitutor(context);
        Assert.assertEquals("abvalue1cd",substitutor.substitute("ab${param1}cd"));
    }

    @Test
    public void substituteMultipleParameterApperances() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        ISubstitutor substitutor = new DefaultSubstitutor(context);
        Assert.assertEquals("abvalue1cdvalue1ef",substitutor.substitute("ab${param1}cd${param1}ef"));
    }
    @Test
    public void substituteMultipleParameters() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        context.put("param2","value2");
        ISubstitutor substitutor = new DefaultSubstitutor(context);
        Assert.assertEquals("abvalue1cdvalue2ef",substitutor.substitute("ab${param1}cd${param2}ef"));
    }
    @Test(expected = MissingSubstitutionParameterException.class)
    public void throwWhenParameterNotFoundInContext() {
        ScanContext context = new ScanContext();
        ISubstitutor substitutor = new DefaultSubstitutor(context);
        substitutor.substitute("ab${param1}cd");
    }
}

