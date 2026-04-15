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
@Test
void arrayFactoryCreates1DArray() {
    NDArray array = NDArray.array(new float[]{1f, 2f, 3f});

    assertEquals(1, array.ndim());
    assertEquals(3, array.size());
    assertArrayEquals(new int[]{3}, array.shape());
    assertArrayEquals(new float[]{1f, 2f, 3f}, array.toFlatArray(), 0.0001f);
}

@Test
void arrayFactoryCreates2DArray() {
    NDArray matrix = NDArray.array(new float[][]{
            {1f, 2f},
            {3f, 4f}
    });

    assertEquals(2, matrix.ndim());
    assertEquals(4, matrix.size());
    assertArrayEquals(new int[]{2, 2}, matrix.shape());
    assertArrayEquals(new float[]{1f, 2f, 3f, 4f}, matrix.toFlatArray(), 0.0001f);
}

@Test
void array2DFactoryThrowsWhenRowsHaveDifferentLengths() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.array(new float[][]{
                            {1f, 2f},
                            {3f}
                    }));

    assertEquals("all rows must have the same length", exception.getMessage());
}

@Test
void zerosCreatesArrayFilledWithZeros() {
    NDArray zeros = NDArray.zeros(2, 3);

    assertEquals(2, zeros.ndim());
    assertEquals(6, zeros.size());
    assertArrayEquals(new int[]{2, 3}, zeros.shape());
    assertArrayEquals(new float[]{0f, 0f, 0f, 0f, 0f, 0f}, zeros.toFlatArray(), 0.0001f);
}

@Test
void arangeCreatesSequenceFromZero() {
    NDArray array = NDArray.arange(4);

    assertEquals(1, array.ndim());
    assertEquals(4, array.size());
    assertArrayEquals(new int[]{4}, array.shape());
    assertArrayEquals(new float[]{0f, 1f, 2f, 3f}, array.toFlatArray(), 0.0001f);
}

@Test
void arangeCreatesSequenceFromStartToEnd() {
    NDArray array = NDArray.arange(2, 6);

    assertEquals(1, array.ndim());
    assertEquals(4, array.size());
    assertArrayEquals(new int[]{4}, array.shape());
    assertArrayEquals(new float[]{2f, 3f, 4f, 5f}, array.toFlatArray(), 0.0001f);
}

@Test
void arangeThrowsWhenEndIsSmallerThanStart() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.arange(5, 2));

    assertEquals("endExclusive must be >= startInclusive", exception.getMessage());
}
@Test
void array1DFactoryThrowsWhenValuesIsNull() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.array((float[]) null));

    assertEquals("values must not be null", exception.getMessage());
}

@Test
void array2DFactoryThrowsWhenValuesIsNull() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.array((float[][]) null));

    assertEquals("values must not be null", exception.getMessage());
}
@Test
void array2DFactoryThrowsWhenARowIsNull() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.array(new float[][]{
                            null,
                            {1f, 2f}
                    }));

    assertEquals("rows must not be null", exception.getMessage());
}

@Test
void zerosCreates1DArrayFilledWithZeros() {
    NDArray zeros = NDArray.zeros(3);

    assertEquals(1, zeros.ndim());
    assertEquals(3, zeros.size());
    assertArrayEquals(new int[]{3}, zeros.shape());
    assertArrayEquals(new float[]{0f, 0f, 0f}, zeros.toFlatArray(), 0.0001f);
}

@Test
void zerosThrowsWhenShapeIsEmpty() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    NDArray::zeros);

    assertEquals("shape must not be empty", exception.getMessage());
}

@Test
void zerosThrowsWhenShapeContainsNegativeDimension() {
    IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class,
                    () -> NDArray.zeros(2, -3));

    assertEquals("shape dimensions must be >= 0", exception.getMessage());
}

@Test
void arangeCreatesEmptyArrayWhenStartEqualsEnd() {
    NDArray array = NDArray.arange(3, 3);

    assertEquals(1, array.ndim());
    assertEquals(0, array.size());
    assertArrayEquals(new int[]{0}, array.shape());
    assertArrayEquals(new float[]{}, array.toFlatArray(), 0.0001f);
}

@Test
void toFlatArrayReturnsCopyAndNotInternalArray() {
    NDArray array = NDArray.array(new float[]{1f, 2f, 3f});

    float[] flat = array.toFlatArray();
    flat[0] = 999f;

    assertArrayEquals(new float[]{1f, 2f, 3f}, array.toFlatArray(), 0.0001f);
}
@Test
void oneDimensionalArrayHasReadableStringRepresentation() {
    NDArray array = NDArray.array(new float[]{1f, 2f, 3f});

    assertEquals("[1.0 2.0 3.0]", array.toString());
}

@Test
void twoDimensionalArrayHasReadableStringRepresentation() {
    NDArray matrix = NDArray.array(new float[][]{
            {1f, 2f},
            {3f, 4f}
    });

    String expected = "[[1.0 2.0]" + System.lineSeparator() + " [3.0 4.0]]";

    assertEquals(expected, matrix.toString());
}
}
