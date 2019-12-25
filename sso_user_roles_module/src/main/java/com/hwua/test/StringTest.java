package com.hwua.test;

import java.util.ArrayList;
import java.util.HashMap;

public class StringTest {
    public static void main(String[] args) {
        String a="hello";
        String b = "hello";
        ArrayList<String> strings = new ArrayList<>();
       strings.add(1,"hello");

        System.out.println(a==strings.get(1));

        System.out.println(a==b);
    }
}
