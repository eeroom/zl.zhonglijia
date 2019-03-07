package com.azeroth.utility;

@FunctionalInterface
public interface Action2<T1,T2> {

    public  void  run(T1 arg1,T2 arg2);
}
