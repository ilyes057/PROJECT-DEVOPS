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
    private void ensureSameShape(NDArray other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }

        if (!Arrays.equals(this.shape, other.shape)) {
            throw new IllegalArgumentException("shape mismatch");
        }
    }
    public NDArray add(NDArray other) {
        ensureSameShape(other);

        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = this.data[i] + other.data[i];
        }
        return new NDArray(result, shape);
    }

    public void addInPlace(NDArray other) {
        ensureSameShape(other);

        for (int i = 0; i < data.length; i++) {
            this.data[i] += other.data[i];
        }
    }

    public NDArray subtract(NDArray other) {
        ensureSameShape(other);

        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = this.data[i] - other.data[i];
        }

        return new NDArray(result, shape);
    }

    public void subtractInPlace(NDArray other) {
        ensureSameShape(other);

        for (int i = 0; i < data.length; i++) {
            this.data[i] -= other.data[i];
        }
    }

    public NDArray multiply(NDArray other) {
        ensureSameShape(other);

        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = this.data[i] * other.data[i];
        }

        return new NDArray(result, shape);
    }

    public void multiplyInPlace(NDArray other) {
        ensureSameShape(other);

        for (int i = 0; i < data.length; i++) {
            this.data[i] *= other.data[i];
        }
    }

    public NDArray multiply(float scalar) {
        float[] result = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = this.data[i] * scalar;
        }

        return new NDArray(result, shape);
    }

    public void multiplyInPlace(float scalar) {
        for (int i = 0; i < data.length; i++) {
            this.data[i] *= scalar;
        }
    }
    private static int[] normalizeReshape(int dataLength, int... newShape) {
        if (newShape == null || newShape.length == 0) {
            throw new IllegalArgumentException("shape must not be empty");
        }

        int minusOneCount = 0;
        int knownProduct = 1;
        int minusOneIndex = -1;

        for (int i = 0; i < newShape.length; i++) {
            int dim = newShape[i];

            if (dim == -1) {
                minusOneCount++;
                minusOneIndex = i;
            } else if (dim < 0) {
                throw new IllegalArgumentException("shape dimensions must be >= -1");
            } else {
                knownProduct *= dim;
            }
        }

        if (minusOneCount > 1) {
            throw new IllegalArgumentException("only one dimension can be -1");
        }

        int[] normalized = Arrays.copyOf(newShape, newShape.length);

        if (minusOneCount == 1) {
            if (knownProduct == 0 || dataLength % knownProduct != 0) {
                throw new IllegalArgumentException("cannot infer shape");
            }
            normalized[minusOneIndex] = dataLength / knownProduct;
        }

        if (computeSize(normalized) != dataLength) {
            throw new IllegalArgumentException("data length does not match shape");
        }

        return normalized;
    }
    public NDArray reshape(int... newShape) {
        int[] normalizedShape = normalizeReshape(data.length, newShape);
        return new NDArray(this.data, normalizedShape);
    }
    public static NDArray ones(int... shape) {
        float[] values = new float[computeSize(shape)];
        Arrays.fill(values, 1f);
        return new NDArray(values, shape);
    }
    public NDArray transpose() {
        if (ndim() != 2) {
            throw new IllegalArgumentException("transpose is only supported for 2D arrays");
        }

        int rows = shape[0];
        int cols = shape[1];
        float[] transposedData = new float[data.length];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                transposedData[c * rows + r] = data[r * cols + c];
            }
        }

        return new NDArray(transposedData, cols, rows);
    }
    public float sum() {
        float total = 0f;

        for (float value : data) {
            total += value;
        }

        return total;
    }
    public static NDArray linspace(float start, float stop, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("num must be > 0");
        }

        float[] values = new float[num];

        if (num == 1) {
            values[0] = start;
            return new NDArray(values, 1);
        }

        float step = (stop - start) / (num - 1);

        for (int i = 0; i < num; i++) {
            values[i] = start + i * step;
        }

        return new NDArray(values, num);
    }
}
