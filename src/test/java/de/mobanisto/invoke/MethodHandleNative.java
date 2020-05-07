package de.mobanisto.invoke;

import java.lang.invoke.MethodHandle;

public class MethodHandleNative implements IMethodHandle {

    private MethodHandle handle;

    public MethodHandleNative(MethodHandle handle) {
        this.handle = handle;
    }

    @Override
    public Object invokeExact(Object... args) throws Throwable {
        return handle.invokeExact(args);
    }

    @Override
    public Object invoke(Object... args) throws Throwable {
        if (args.length == 1) {
            return handle.invoke(args[0]);
        } else if (args.length == 2) {
            return handle.invoke(args[0], args[1]);
        } else if (args.length == 3) {
            return handle.invoke(args[0], args[1], args[2]);
        } else if (args.length == 4) {
            return handle.invoke(args[0], args[1], args[2], args[3]);
        } else if (args.length == 5) {
            return handle.invoke(args[0], args[1], args[2], args[3], args[4]);
        } else if (args.length == 6) {
            return handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5]);
        } else if (args.length == 7) {
            return handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        }
        return handle.invoke(args);
    }

}
