package de.mobanisto.invoke;

import java.lang.invoke.MethodHandle;

public class BaseTest {

    protected IMethodHandle lang(MethodHandle swap) {
        return new MethodHandleNative(swap);
    }

    protected IMethodHandle ours(de.mobanisto.invoke.MethodHandle handle) {
        return new MethodHandleOurs(handle);
    }

}
