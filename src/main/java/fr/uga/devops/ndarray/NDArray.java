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
    public static NDArray array(float[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values must not be null");
        }
        return new NDArray(values, values.length);
    }
     public static NDArray array(float[][] values) {
        if (values == null) {
            throw new IllegalArgumentException("values must not be null");
        }

        int rows = values.length;
        int cols = 0;

        if (rows > 0) {
            if (values[0] == null) {
                throw new IllegalArgumentException("rows must not be null");
            }
            cols = values[0].length;
        }

        for (int i = 0; i < rows; i++) {
            if (values[i] == null) {
                throw new IllegalArgumentException("rows must not be null");
            }
            if (values[i].length != cols) {
                throw new IllegalArgumentException("all rows must have the same length");
            }
        }

        float[] flat = new float[rows * cols];
        int index = 0;
        for (float[] row : values) {
            for (float value : row) {
                flat[index++] = value;
            }
        }

        return new NDArray(flat, rows, cols);
    }
    public static NDArray zeros(int... shape) {
        return new NDArray(new float[computeSize(shape)], shape);
    }
    public static NDArray arange(int endExclusive) {
        return arange(0, endExclusive);
    }

    public static NDArray arange(int startInclusive, int endExclusive) {
        if (endExclusive < startInclusive) {
            throw new IllegalArgumentException("endExclusive must be >= startInclusive");
        }

        float[] values = new float[endExclusive - startInclusive];
        for (int i = 0; i < values.length; i++) {
            values[i] = startInclusive + i;
        }

        return new NDArray(values, values.length);
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
    public float[] toFlatArray() {
        return Arrays.copyOf(data, data.length);
    }
    @Override
    public String toString() {
        if (ndim() == 1) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < data.length; i++) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(data[i]);
            }
            sb.append("]");
            return sb.toString();
        }

        if (ndim() == 2) {
            int rows = shape[0];
            int cols = shape[1];

            StringBuilder sb = new StringBuilder("[");
            for (int r = 0; r < rows; r++) {
                if (r > 0) {
                    sb.append(System.lineSeparator()).append(" ");
                }
                sb.append("[");
                for (int c = 0; c < cols; c++) {
                    if (c > 0) {
                        sb.append(" ");
                    }
                    sb.append(data[r * cols + c]);
                }
                sb.append("]");
            }
            sb.append("]");
            return sb.toString();
        }

        return Arrays.toString(data);
    }

    private static int computeSize(int... shape) {
        if (shape == null || shape.length == 0) {
            throw new IllegalArgumentException("shape must not be empty");
        }

        int size = 1;
        for (int dim : shape) {
            if (dim < 0) {
                throw new IllegalArgumentException("shape dimensions must be >= 0");
            }
            size *= dim;
        }
        return size;
    }
}
