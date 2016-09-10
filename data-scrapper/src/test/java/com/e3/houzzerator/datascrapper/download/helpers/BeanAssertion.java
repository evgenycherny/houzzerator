package com.e3.houzzerator.datascrapper.download.helpers;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;

import javax.activation.UnsupportedDataTypeException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Author:  etshiorny
 * Date:    7/10/16.
 */
public class BeanAssertion {
    @SneakyThrows
    public static void assertBeanProperties(Class<?> clazz) {
        Object bean = BeanUtils.instantiate(clazz);
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
        for (PropertyDescriptor pd: pds) {
            Method getter = pd.getReadMethod();
            Method setter = pd.getWriteMethod();
            if (!isValidGetter(getter) || !isValidSetter(setter)) continue;
            assertProperty(bean, getter, setter);
        }
    }
    private static boolean isValidGetter(Method getter) {
        return (getter!=null)
                && (getter.getParameterTypes().length==0);
    }
    private static boolean isValidSetter(Method setter) {
        return (setter!=null)
                && (setter.getParameterTypes().length==1);
    }
    @SneakyThrows
    private static void assertProperty(Object bean, Method getter, Method setter) {
        Object inValue = buildValue(setter.getParameterTypes()[0]);
        setter.invoke(bean, inValue);
        Object outValue = getter.invoke(bean);
        Assert.assertEquals(inValue, outValue);
    }
    @SneakyThrows
    private static Object buildValue(Class<?> paramClass) {
        if (paramClass==String.class) return "someValue";
        if (paramClass==int.class) return 3;
        if (paramClass==double.class) return 3.5;

        return null;
    }
    public static void assertEqualsAndHashCode(Class<?> clazz) {
        Object bean = BeanUtils.instantiate(clazz);
        Assert.assertEquals(bean, bean);
    }
    public static void assertToString(Class<?> clazz) {
        Object bean = BeanUtils.instantiate(clazz);
        Assert.assertEquals(bean.toString(), bean.toString());
    }
}
