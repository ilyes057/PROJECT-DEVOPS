package fr.uga.devops.ndarray;

import java.util.Arrays;

public class NDArray {

    private final float[] data;
    private final int[] shape;

    public NDArray(float[] data, int... shape) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        if (shape == null || shape.length == 0) {
            throw new IllegalArgumentException("shape must not be empty");
        }

        int expectedSize = 1;
        for (int dim : shape) {
            if (dim < 0) {
                throw new IllegalArgumentException("shape dimensions must be >= 0");
            }
            expectedSize *= dim;
        }

        if (expectedSize != data.length) {
            throw new IllegalArgumentException(
                "data length does not match shape"
            );
        }

        this.data = Arrays.copyOf(data, data.length);
        this.shape = Arrays.copyOf(shape, shape.length);
    }

    public int ndim() {
        return shape.length;
    }

    public int size() {
        return data.length;
    }

    public int[] shape() {
        return Arrays.copyOf(shape, shape.length);
    }
}
