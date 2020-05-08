package de.mobanisto.invoke.exceptions;

import org.junit.Test;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class TestException3 {

    @Test(expected = IOException.class)
    public void testNative() throws Throwable {
        MethodHandle exceptionConstructor = MethodHandles.publicLookup().findConstructor(
                IOException.class, MethodType.methodType(void.class, String.class));

        System.out.println(exceptionConstructor);

        MethodHandle exceptionThrower = MethodHandles.throwException(void.class, IOException.class);

        System.out.println(exceptionThrower);

        MethodHandle handle = MethodHandles.foldArguments(exceptionThrower, exceptionConstructor);

        handle.invoke("test");
    }

}
