package com.azeroth.utility;

@FunctionalInterface
public interface FunctionWrapper<T> {
    void invoke(T obj) throws Exception;
}
