package com.azeroth.utility;

@FunctionalInterface
public interface Function2<T1,T2,M> {

    public M run(T1 arg1,T2 arg2);
}
