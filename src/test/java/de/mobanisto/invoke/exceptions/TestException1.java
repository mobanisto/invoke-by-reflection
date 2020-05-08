package de.mobanisto.invoke.exceptions;

import org.junit.Test;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class TestException1 {

    @Test(expected = IOException.class)
    public void testNative() throws Throwable {
        MethodHandle exceptionThrower = MethodHandles.throwException(void.class, IOException.class);
        exceptionThrower.invoke(new IOException("test"));
    }

}
