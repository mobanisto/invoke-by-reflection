package de.mobanisto.invoke;

import java.lang.reflect.*;

public class MethodHandle {

    private static enum Type {
        CONSTRUCTOR,
        METHOD
    }

    private Type type = null;

    private Constructor<?> constructor;
    private Method method;

    public MethodHandle(Constructor<?> constructor) {
        this.constructor = constructor;
        type = Type.CONSTRUCTOR;
    }

    public MethodHandle(Method method) {
        this.method = method;
        type = Type.METHOD;
    }

    public Object invokeExact(Object... args) throws Throwable {
        return invoke(args);
    }

    public Object invoke(Object... args) throws Throwable {
        switch (type) {
            case CONSTRUCTOR: {
                return invokeConstructor(args);
            }
            case METHOD: {
                return invokeMethod(args);
            }
        }
        return null;
    }

    private Object invokeMethod(Object[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?>[] types = method.getParameterTypes();
        Class<?> lastType = types[types.length - 1];

        if (!lastType.isArray()) {
            return method.invoke(null, args);
        }

        // last argument type is array
        Object[] callParameters = buildVarArgParameters(types, args);
        return method.invoke(null, new Object[]{args});
    }

    private Object invokeConstructor(Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] types = constructor.getParameterTypes();
        Class<?> lastType = types[types.length - 1];

        if (!lastType.isArray()) {
            return constructor.newInstance(args);
        }

        // last argument type is array
        Object[] callParameters = buildVarArgParameters(types, args);
        return constructor.newInstance(callParameters);
    }

    private Object[] buildVarArgParameters(Class<?>[] types, Object[] args) {
        Class<?> lastType = types[types.length - 1];

        Object[] callParameters = new Object[types.length];
        for (int i = 0; i < types.length - 1; i++) {
            callParameters[i] = args[i];
        }

        int nVarArgs = args.length - types.length + 1;
        Object[] varargs = (Object[]) Array.newInstance(lastType.getComponentType(), nVarArgs);
        for (int k = types.length - 1; k < args.length; k++) {
            varargs[k] = args[k];
        }
        callParameters[types.length - 1] = varargs;
        return callParameters;
    }

    @Override
    public String toString() {
        switch (type) {
            case CONSTRUCTOR: {
                // TODO: implement
            }
            case METHOD: {
                StringBuilder buffer = new StringBuilder();
                buffer.append("MethodHandle(");
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < method.getParameterCount(); i++) {
                    Parameter parameter = parameters[i];
                    if (parameter.getType().isArray()) {
                        buffer.append(parameter.getType().getComponentType());
                        buffer.append("[]");
                    } else {
                        buffer.append(parameter.getType());
                    }
                    if (i < method.getParameterCount() - 1) {
                        buffer.append(",");
                    }
                }
                buffer.append(")");
                buffer.append(method.getReturnType());
                return buffer.toString();
            }
        }
        return super.toString();
    }

}
