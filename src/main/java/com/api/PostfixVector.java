/**
 * Hoja de trabajo 2
 * Algoritmos y estructuras de datos
 * Fecha: 27 / 01 / 2025
 * Grupo 10
 */
package com.api;

import java.util.Vector;

public class PostfixVector<E> implements Stack<E> {
    private Vector<E> stack;

    public PostfixVector() {
        stack = new Vector<>();
    }

    @Override
    public void push(E item) {
        stack.add(item);
    }

    @Override
    public E pop() {
        if (empty()) {return null;}
        return stack.remove(stack.size() - 1);
    }

    @Override
    public E peek() {
        if (empty()) {return null;}
        return stack.lastElement();
    }

    @Override
    public boolean empty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}