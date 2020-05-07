package de.mobanisto.invoke.collections;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSwap {

    @Test
    public void testNative() throws Throwable {
        MethodHandle swap = MethodHandles.publicLookup().findStatic(Collections.class, "swap",
                MethodType.methodType(void.class, List.class, int.class, int.class));

        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        swap.invoke(values, 1, 0);
        assertEquals("[2, 1, 3, 4, 5]", values.toString());
    }

    @Test
    public void testOur() throws Throwable {
        de.mobanisto.invoke.MethodHandle swap = de.mobanisto.invoke.MethodHandles.publicLookup().findStatic(Collections.class, "swap",
                de.mobanisto.invoke.MethodType.methodType(void.class, List.class, int.class, int.class));

        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        swap.invoke(values, 1, 0);
        assertEquals("[2, 1, 3, 4, 5]", values.toString());
    }

}
