package com.whx;

import java.util.Arrays;

/**
 * Created by Administrator on 2019/7/25.
 */
public class MaoPaoSortTest {
    /**
     * [11,35,24,5,65,88,67,22]
     *  i                   j
     * i=0时;j从a.length-1开始，a[j]<a[j-1]时三步换值，一直到j-1=i
     * i=1时;j从a.length-1开始，a[j]<a[j-1]时三步换值，一直到j-1=i
     */
    public static void main(String[] args) {
        int a[] ={11,35,24,5,65,88,67,22};
        for(int i=0;i<a.length;i++){
            boolean b=true;
            for (int j=a.length-1;j>i;j--){
                if (a[j-1]>a[j]){
                    int t=a[j-1];
                    a[j-1]=a[j];
                    a[j]=t;
                    b=false;
                }
            }
            if (b){
                break;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
