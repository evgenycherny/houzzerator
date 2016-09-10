package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class DefaultSubstitutionHandlerImplShould {
    @Test
    public void instanciate() {
        DefaultSubstitutionHandlerImpl substitutor = new DefaultSubstitutionHandlerImpl();
        Assert.assertNotNull(substitutor.getContext());
        Assert.assertEquals(0, substitutor.getContext().size());
    }

    @Test
    public void allowUsingProperties() {
        DefaultSubstitutionHandlerImpl substitutor = new DefaultSubstitutionHandlerImpl();
        ScanContext ctx = new ScanContext();
        ctx.put("test","value");
        substitutor.setContext(ctx);
        Assert.assertEquals(1, substitutor.getContext().size());
    }

    @Test
    public void returnNonParametrizedStringAsIs() {
        ScanContext context = new ScanContext();
        DefaultSubstitutionHandlerImpl substitutor = new DefaultSubstitutionHandlerImpl(context);
        Assert.assertEquals("abcd",substitutor.substitute("abcd"));
    }

    @Test
    public void substituteSingleParameter() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        SubstitutionHandler substitutor = new DefaultSubstitutionHandlerImpl(context);
        Assert.assertEquals("abvalue1cd",substitutor.substitute("ab${param1}cd"));
    }

    @Test
    public void substituteMultipleParameterApperances() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        SubstitutionHandler substitutor = new DefaultSubstitutionHandlerImpl(context);
        Assert.assertEquals("abvalue1cdvalue1ef",substitutor.substitute("ab${param1}cd${param1}ef"));
    }
    @Test
    public void substituteMultipleParameters() {
        ScanContext context = new ScanContext();
        context.put("param1","value1");
        context.put("param2","value2");
        SubstitutionHandler substitutor = new DefaultSubstitutionHandlerImpl(context);
        Assert.assertEquals("abvalue1cdvalue2ef",substitutor.substitute("ab${param1}cd${param2}ef"));
    }
    @Test(expected = MissingSubstitutionParameterException.class)
    public void throwWhenParameterNotFoundInContext() {
        ScanContext context = new ScanContext();
        SubstitutionHandler substitutor = new DefaultSubstitutionHandlerImpl(context);
        substitutor.substitute("ab${param1}cd");
    }
}

