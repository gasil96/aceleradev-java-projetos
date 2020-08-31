package br.com.codenation;

import java.util.*;
import java.util.stream.IntStream;


public class StatisticUtil {

    public  static int average(int[] elements) {
        return IntStream.of(elements).sum() / elements.length;
    }

    public static int mode(int[] elements) {
        int maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < elements.length; ++i) {
            int count = 0;
            for (int j = 0; j < elements.length; ++j) {
                if (elements[j] == elements[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = elements[i];
            }
        }
        return maxValue;
    }

    public static int median(int[] elements) {
        Arrays.sort(elements);
        int mediana = elements.length/2;
        if (elements.length%2 == 1) {
            System.err.println(elements[mediana]);
            return elements[mediana];
        } else {
            System.err.println((elements[mediana-1] + elements[mediana]) / 2);
            return (elements[mediana-1] + elements[mediana]) / 2;
        }
    }

    public static void main(String[] args) {
        int x[] = {1, 2, 14, 16 ,4, 5};

        StatisticUtil teste = new StatisticUtil();
        Arrays.sort(x);

        teste.median(x);


    }

}