package fr.uga.devops.ndarray;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("=== NDArray demo ===");

        NDArray ones = NDArray.ones(2, 3);
        System.out.println();
        System.out.println("ones(2, 3):");
        System.out.println(ones);

        NDArray linspace = NDArray.linspace(0f, 1f, 5);
        System.out.println();
        System.out.println("linspace(0, 1, 5):");
        System.out.println(linspace);

        NDArray matrix = NDArray.array(new float[][]{
                {1f, 2f, 3f},
                {4f, 5f, 6f}
        });
        System.out.println();
        System.out.println("matrix:");
        System.out.println(matrix);

        NDArray transposed = matrix.transpose();
        System.out.println();
        System.out.println("matrix.transpose():");
        System.out.println(transposed);

        System.out.println();
        System.out.println("matrix.sum():");
        System.out.println(matrix.sum());

        NDArray negatives = NDArray.array(new float[]{-1f, 2f, -3f});
        System.out.println();
        System.out.println("abs([-1, 2, -3]):");
        System.out.println(negatives.abs());

        NDArray vector = NDArray.array(new float[]{10f, 20f, 30f});
        System.out.println();
        System.out.println("matrix + vector:");
        System.out.println(matrix.add(vector));

        System.out.println();
        System.out.println("=== End of demo ===");
    }
}
