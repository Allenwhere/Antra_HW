package Week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort {

    public static void qsort(List<Integer> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start);
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i) >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i);
            }
            if (k > index - start + 1) {
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<Integer>();
        int[] ret = new int[8];
        list1.add(8);
        list1.add(3);
        list1.add(7);
        list1.add(6);
        list1.add(9);
        list1.add(4);
        list1.add(2);
        list1.add(5);
        System.out.println(list1);
        qsort(list1,0,list1.size()-1, ret, 0,4);
        System.out.println(Arrays.toString(ret));
    }

}
