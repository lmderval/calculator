package calculator.complex;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Complex {
    private int re = 0;
    private int im = 0;

    public Complex add(@NonNull Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    public Complex sub(@NonNull Complex other) {
        return new Complex(re - other.re, im - other.im);
    }

    public Complex mul(@NonNull Complex other) {
        return new Complex(re * other.re - im * other.im, re * other.im + im * other.re);
    }

    public Complex div(@NonNull Complex other) {
        if (other.isZero()) throw new ArithmeticException("/ by zero");
        int div = other.re * other.re + other.im * other.im;
        int re = (this.re * other.re + this.im * other.im) / div;
        int im = (-this.re * other.im + this.im * other.re) / div;
        return new Complex(re, im);
    }

    public boolean isZero() {
        return re == 0 && im == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (re == 0) {
            if (im == 0) sb.append(0);
            else sb.append(im).append('j');
        } else {
            sb.append(re);
            if (im > 0) sb.append('+');
            if (im != 0) sb.append(im).append('j');
        }
        return sb.toString();
    }
}
