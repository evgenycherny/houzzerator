package com.e3.houzzerator.datascrapper.download;

import com.e3.houzzerator.datascrapper.download.helpers.BeanAssertion;
import com.e3.houzzerator.datascrapper.download.helpers.IntArrayHelper;
import com.e3.houzzerator.datascrapper.download.datamodel.Range;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author:  etshiorny
 * Date:    7/1/16.
 */
public class RangeShould {
    @Test
    public void instanciate() {
        Range range = new Range(1,2);
        Assert.assertEquals(1,range.getFrom());
        Assert.assertEquals(2,range.getTo());
        Assert.assertEquals(1,range.getStep());
    }

    @Test
    public void instanciateWithStep() {
        Range range = new Range(1, 4, 2);
        Assert.assertEquals(1,range.getFrom());
        Assert.assertEquals(4,range.getTo());
        Assert.assertEquals(2,range.getStep());
    }

    @Test
    public  void assertProperties() {
        BeanAssertion.assertBeanProperties(Range.class);
    }
    @Test
    public void iterateFromToByOne() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(1, 3).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{1, 2, 3}, helper.toArray());
    }
    @Test
    public void iterateFromToBySteps() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(1, 5, 2).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{1, 3, 5}, helper.toArray());
    }

    @Test
    public void notCountBackward() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(2, 1).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{}, helper.toArray());
    }
    @Test
    public void countBackwardWithNegativeStep() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(2, 1, -1).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{2, 1}, helper.toArray());
    }

    @Test
    public void notCountWithZeroStep() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(1, 2, 0).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{}, helper.toArray());
    }

    @Test
    public void countAtLeastOneWhenStepBiggerThanRange() {
        IntArrayHelper helper = new IntArrayHelper();
        new Range(1, 2, 5).iterate(helper::push);
        Assert.assertArrayEquals(new int[]{1}, helper.toArray());
    }

    /*@Test
    public void provideEqualsMethod() {
        Range p1 = new Range(1, 2);
        Range p2 = new Range(1, 2, 1);
        Range p3 = new Range(1, 2, 2);
        Range p4 = new Range(1, 2);

        Assert.assertEquals(p1, p4);
        Assert.assertEquals(p1, p2);
        Assert.assertNotEquals(p1, p3);
        Assert.assertNotEquals(p2, p3);
    }*/
    @Test
    public void provideToString() {
        Range range = new Range(1, 5, 2);
        Assert.assertEquals("Range(from=1, to=5, step=2)", range.toString());
    }
}

