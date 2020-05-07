package de.mobanisto.invoke;

public interface IMethodHandle {

    public Object invokeExact(Object... args) throws Throwable;

    public Object invoke(Object... args) throws Throwable;

}
