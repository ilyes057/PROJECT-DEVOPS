package fr.uga.devops.ndarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class NDArrayTest {

    @Test
    void ndarrayStoresShapeSizeAndNdimFor1DArray() {
        NDArray array = new NDArray(new float[]{1f, 2f, 3f}, 3);

        assertEquals(1, array.ndim());
        assertEquals(3, array.size());
        assertArrayEquals(new int[]{3}, array.shape());
    }
    @Test
    void ndarrayStoresShapeSizeAndNdimFor2DArray() {
        NDArray matrix = new NDArray(new float[]{1f, 2f, 3f, 4f}, 2, 2);

        assertEquals(2, matrix.ndim());
        assertEquals(4, matrix.size());
        assertArrayEquals(new int[]{2, 2}, matrix.shape());
    }
@Test
void constructorThrowsWhenDataIsNull() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> new NDArray(null, 3));

    assertEquals("data must not be null", exception.getMessage());
}

@Test
void constructorThrowsWhenShapeIsEmpty() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> new NDArray(new float[]{1f, 2f, 3f}));

    assertEquals("shape must not be empty", exception.getMessage());
}

@Test
void constructorThrowsWhenShapeContainsNegativeDimension() {
    IllegalArgumentException exception =
	            assertThrows(IllegalArgumentException.class,
                    () -> new NDArray(new float[]{1f, 2f, 3f}, -3));

    assertEquals("shape dimensions must be >= 0", exception.getMessage());
}

@Test
void constructorThrowsWhenDataLengthDoesNotMatchShape() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> new NDArray(new float[]{1f, 2f, 3f}, 2, 2));

    assertEquals("data length does not match shape", exception.getMessage());
}

@Test
void shapeReturnsACopyAndNotInternalArray() {
    NDArray array = new NDArray(new float[]{1f, 2f, 3f}, 3);

    int[] shape = array.shape();
    shape[0] = 999;

    assertArrayEquals(new int[]{3}, array.shape());
}
}
