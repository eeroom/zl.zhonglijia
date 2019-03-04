package com.azeroth.bwl;

import android.view.View;

@FunctionalInterface
public interface FunctionWrapper<T> {
    void invoke(T obj) throws Exception;
}
