package com.java.api.controller.admin.seminarmanager.dto;

import java.lang.reflect.Array;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        // tim so lon nhat trong mang
        int[] array = {1,2,3,4,5,7};
        for (int i = 0; i < array.length ; i++) {
            int max = 0;
            if (array[i]> max) {
                max =  array[i];
                System.out.printf("gia tri lon nhat la :" + max);
            }

        }
       // tim so bi miss trong mang
        int[] array2 = {1,3,4,5,7};
        int[] array3  = {1,2,3,4,5,7};
        for (int i = 0 ; i < array2.length;i++) {
            if(array2[i]!=array3[i]) {
                System.out.printf("gia tri miss la : "+ array3[i]);
                break;
            }
        }
    }
}
