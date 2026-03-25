package fr.uga.devops.ndarray;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NDArrayTest {

    @Test
    void ndarrayClassCanBeInstantiated() {
        NDArray array = new NDArray();
        assertNotNull(array);
    }
}
