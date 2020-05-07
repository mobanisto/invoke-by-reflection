package de.mobanisto.invoke;

public class MethodHandleOurs implements IMethodHandle {

    private MethodHandle handle;

    public MethodHandleOurs(MethodHandle handle) {
        this.handle = handle;
    }

    @Override
    public Object invokeExact(Object... args) throws Throwable {
        return handle.invokeExact(args);
    }

    @Override
    public Object invoke(Object... args) throws Throwable {
        return handle.invoke(args);
    }

}
