package com.pr;

public class CustomFilter<T> implements Filter<T> {
    @Override
    public T apply(T o){
        System.out.println("Обработка " + o.toString());
        return o;
    }
}
