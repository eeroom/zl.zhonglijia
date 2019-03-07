package com.azeroth.utility;

import java.net.PortUnreachableException;

@FunctionalInterface
public interface Function3<T1,T2,T3,M> {

    public M run(T1 arg1,T2 arg2,T3 arg3);
}
