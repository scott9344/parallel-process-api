package com.innovativeintell.parallel.process.api.practice;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) {
        int searchElement = 1;
        int[] allElements = {3,5,1,7,9,11,13,41,2,4};
        for (int i=0; i< allElements.length; i++) {
            for (int j=i+1;j< allElements.length; j++) {
                if (allElements[i] > allElements[j]) {
                    int temp = allElements[i];
                    allElements[i] = allElements[j];
                    allElements[j] = temp;
                }
            }
        }

       IntStream.of(allElements).forEach(System.out::println);


        int start = 0;
        int last = allElements.length-1;
        int mid = start + (last-start)/2;
        boolean found  = false;
        while(start <= last) {
            if(allElements[mid] == searchElement) {
                found = true;
                break;
            } else if (searchElement < allElements[mid]) {
                last = mid-1;
                mid = (start+last)/2;
            } else if (searchElement > allElements[mid]) {
                start = mid+1;
                mid = (start+last)/2;
            }
        }
        if(found) {
            System.out.println("Element Found");
        }else {
            System.out.println("Element Not Found");
        }
    }
}
