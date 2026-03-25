package fr.uga.devops.ndarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NDArrayTest {

    @Test
    void ndarrayStoresShapeSizeAndNdimFor1DArray() {
        NDArray array = new NDArray(new float[]{1f, 2f, 3f}, 3);

        assertEquals(1, array.ndim());
        assertEquals(3, array.size());
        assertArrayEquals(new int[]{3}, array.shape());
    }
}
