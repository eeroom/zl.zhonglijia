package com.azeroth.utility;

@FunctionalInterface
public interface Function4<T1,T2,T3,T4,M> {

    public M run(T1 arg1,T2 arg2,T3 arg3,T4 arg4);
}
