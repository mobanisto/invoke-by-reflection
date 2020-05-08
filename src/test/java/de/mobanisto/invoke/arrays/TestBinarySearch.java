package de.mobanisto.invoke.arrays;

import de.mobanisto.invoke.BaseTest;
import de.mobanisto.invoke.MethodHandle;
import de.mobanisto.invoke.MethodHandles;
import de.mobanisto.invoke.MethodType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestBinarySearch extends BaseTest {

    @Test
    public void testNative() throws NoSuchMethodException, IllegalAccessException {
        java.lang.invoke.MethodHandle search = java.lang.invoke.MethodHandles.publicLookup().findStatic(Arrays.class,
                "binarySearch", java.lang.invoke.MethodType.methodType(
                        int.class, double[].class, int.class, int.class, double.class));
        Assert.assertEquals("MethodHandle(double[],int,int,double)int", search.toString());
    }

    @Test
    public void testOur() throws NoSuchMethodException, IllegalAccessException {
        MethodHandle search = MethodHandles.publicLookup().findStatic(Arrays.class,
                "binarySearch", MethodType.methodType(
                        int.class, double[].class, int.class, int.class, double.class));
        Assert.assertEquals("MethodHandle(double[],int,int,double)int", search.toString());
    }

}
