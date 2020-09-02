package com.tsystems.javaschool.tasks.subsequence;

import java.util.Iterator;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }

        if (x.isEmpty() || y.isEmpty()) {
            return true;
        }

        Iterator iter = y.iterator();

        for (Object X : x) {
            while (iter.hasNext()) {
                Object Y = iter.next();
                if (X.equals(Y)) {
                    break;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

}
