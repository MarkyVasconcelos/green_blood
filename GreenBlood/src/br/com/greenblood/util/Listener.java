package br.com.greenblood.util;

public interface Listener<T> {
    void fire(T t);
}
