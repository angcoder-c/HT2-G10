package com.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostfixTests {

    @Test
    public void testEvaluatePostfix() {
        String[] expression = {"1", "2", "+", "4", "*", "3", "+"};
        assertEquals(15, Main.evaluatePostfix(expression));
    }

    @Test
    public void testInvalidExpression() {
        String[] invalidExp = {"1", "+"};
        assertThrows(IllegalArgumentException.class, () -> Main.evaluatePostfix(invalidExp));
    }

    @Test
    public void testDivisionByZero() {
        String[] divZeroExp = {"4", "0", "/"};
        assertThrows(ArithmeticException.class, () -> Main.evaluatePostfix(divZeroExp));
    }

    @Test
    public void testStackOperations() {
        Stack<Integer> stack = new PostfixVector<>();
        assertTrue(stack.empty());
        stack.push(10);
        stack.push(20);
        assertEquals((Integer) 20, stack.pop());
        assertEquals((Integer) 10, stack.peek());
        assertEquals(1, stack.size());
        assertFalse(stack.empty());
    }
}
