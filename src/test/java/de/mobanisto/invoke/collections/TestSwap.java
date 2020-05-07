package de.mobanisto.invoke.collections;

import de.mobanisto.invoke.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSwap extends BaseTest {

    @Test
    public void testNative() throws Throwable {
        java.lang.invoke.MethodHandle swap = java.lang.invoke.MethodHandles.publicLookup().findStatic(Collections.class, "swap",
                java.lang.invoke.MethodType.methodType(void.class, List.class, int.class, int.class));
        test(lang(swap));
    }

    @Test
    public void testOur() throws Throwable {
        MethodHandle swap = MethodHandles.publicLookup().findStatic(Collections.class, "swap",
                MethodType.methodType(void.class, List.class, int.class, int.class));
        test(ours(swap));
    }

    private void test(IMethodHandle handle) throws Throwable {
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        handle.invoke(values, 1, 0);
        assertEquals("[2, 1, 3, 4, 5]", values.toString());
    }

}
