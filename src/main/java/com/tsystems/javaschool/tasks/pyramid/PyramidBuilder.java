package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if (inputNumbers == null || inputNumbers.isEmpty() || inputNumbers.contains(null)) {
            throw new CannotBuildPyramidException();
        }
        int n = 0;
        int rows = 0;
        int k = 1;
        while (n < inputNumbers.size() && n < Integer.MAX_VALUE - k) {
            n += k;
            k++;
        }
        if (n == inputNumbers.size()) {
            rows = k-1;
        } else {
            throw new CannotBuildPyramidException();
        }

        int columns = 2 * rows - 1;

        Collections.sort(inputNumbers);

        int[][] result = new int[rows][columns];
        int cur = 0;
        int amount = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < amount; j++) {
                int index = rows - amount + j * 2;
                result[i][index] = inputNumbers.get(cur);
                cur++;
            }
            amount++;
        }

        return result;
    }


}
