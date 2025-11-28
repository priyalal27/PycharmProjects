package com.demo.testngsel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class test {


    public static void main(String[] args) {
//        String s ="Hi";
//        String s1=s.concat("Hello");
//        System.out.print(s1);
//

//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("Country1", "Capital1");
//        map.put("Country2", "Capital2");
//        map.put("Country3", "Capital3");
//        map.put("Country4", "Capital4");
//
//
//        for(Map.Entry<String,String> entry:map.entrySet()){
//            System.out.println(entry.getKey()+" = "+entry.getValue());
//        }



        /// Country1 = Capital1
        /// Country2 = Capital2
        /// Country3 = Capital3
        /// Country4 = Capital4


        /// String s = “aaAAAbbbBaaaadcccBBBB”
        ///
        /// Count the characters and print like, a2 A3 b3 B1 a4 d1 c3 B4

        String s = "aaAAAbbbBaaaadcccBBBB";
        Map<Character,Integer> freq=new LinkedHashMap<>();

//        for (char c : s.toCharArray()){
//
//            if(c!=' '){
//                freq.put(c,freq.getOrDefault(c,0)+1);
//            }
//        }

        System.out.println(freq);

        StringBuilder res=new StringBuilder();
        for (Map.Entry<Character,Integer> entry:freq.entrySet()){
            res.append(entry.getKey()).append(entry.getValue()).append("  ");
        }

        System.out.println(res);



    }





}
