package com.pr;

public interface Filter<T> {
    T apply(T o);
}
