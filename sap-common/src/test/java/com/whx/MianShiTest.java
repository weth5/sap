package com.whx;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2019/7/25.
 */
public class MianShiTest {
    /**
     *
     *
     */
    public static void main(String[] args) {
        /*int count=1,i=1;
        System.out.println(f(count,i));*/
        System.out.println(f1(12));
    }
    private static long f1(int n) {
        //
        if(n==1 || n==2){
            return 1;
        }
        return f1(n-1)+f1(n-2);
    }
    static int f(int count,int i){
        int old=0;
        for (;i<=12;i++){
            if (++old>=2){
                count++;
                count+=f(1,i);
            }
        }
        return count;
    }
    @Test
    public void inputio() throws IOException {
        System.out.println("输入学生id");
        int id=new Scanner(System.in).nextInt();
        System.out.println("输入学生姓名");
        String name=new Scanner(System.in).nextLine();
        System.out.println("输入score1");
        double score1=new Scanner(System.in).nextDouble();
        System.out.println("输入score2");
        double score2=new Scanner(System.in).nextDouble();
        System.out.println("输入score3");
        double score3=new Scanner(System.in).nextDouble();
        double acgscore=(score1+score2+score3)/3;
        FileOutputStream fileOutputStream=new FileOutputStream("C:/Users/Administrator/Desktop/stud.txt");
        /*fileOutputStream.write(id);
        fileOutputStream.write(name.getBytes());
        fileOutputStream.write(String.valueOf(acgscore).getBytes());*/
        OutputStreamWriter out=new OutputStreamWriter(fileOutputStream,"utf-8");
        out.write(id);
        out.write(name);
        out.write(String.valueOf(acgscore));
        System.out.println("写出完毕");
        out.flush();
        fileOutputStream.close();
    }
}
