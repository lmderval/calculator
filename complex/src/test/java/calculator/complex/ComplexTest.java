package calculator.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {
    @Test
    void add_twoComplex() {
        Complex z1 = new Complex(4, -2);
        Complex z2 = new Complex(3, 5);
        Complex actual = z1.add(z2);
        Complex expected = new Complex(7, 3);
        assertEquals(expected, actual);
    }

    @Test
    void sub_twoComplex() {
        Complex z1 = new Complex(4, -2);
        Complex z2 = new Complex(3, 5);
        Complex actual = z1.sub(z2);
        Complex expected = new Complex(1, -7);
        assertEquals(expected, actual);
    }

    @Test
    void mul_twoComplex() {
        Complex z1 = new Complex(4, -2);
        Complex z2 = new Complex(3, 5);
        Complex actual = z1.mul(z2);
        Complex expected = new Complex(22, 14);
        assertEquals(expected, actual);
    }

    @Test
    void div_twoComplex() {
        assertDoesNotThrow(() -> {
            Complex z1 = new Complex(15, -18);
            Complex z2 = new Complex(3, 4);
            Complex actual = z1.div(z2);
            Complex expected = new Complex(-1, -4);
            assertEquals(expected, actual);
        });
    }

    @Test
    void div_divisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            Complex z1 = new Complex(4, -2);
            Complex z2 = new Complex();
            z1.div(z2);
        });
    }

    @Test
    void toString_zero() {
        Complex z = new Complex();
        assertEquals("0", z.toString());
    }

    @Test
    void toString_positiveReal() {
        Complex z = new Complex(7, 0);
        assertEquals("7", z.toString());
    }

    @Test
    void toString_negativeReal() {
        Complex z = new Complex(-3, 0);
        assertEquals("-3", z.toString());
    }

    @Test
    void toString_positiveImaginary() {
        Complex z = new Complex(0, 6);
        assertEquals("6j", z.toString());
    }

    @Test
    void toString_negativeImaginary() {
        Complex z = new Complex(0, -8);
        assertEquals("-8j", z.toString());
    }

    @Test
    void toString_complexWithPositiveImaginary() {
        Complex z = new Complex(5, 3);
        assertEquals("5+3j", z.toString());
    }

    @Test
    void toString_complexWithNegativeImaginary() {
        Complex z = new Complex(2, -3);
        assertEquals("2-3j", z.toString());
    }
}
