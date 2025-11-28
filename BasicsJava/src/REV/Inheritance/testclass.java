package REV.Inheritance;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class testclass {


    public static void main(String[] args) {
            int[] aa={2,0,3,0,9,1,1,2,9};
            Map<Integer,Integer> map=new LinkedHashMap<>();

            for (int num:aa){
                map.put(num,map.getOrDefault(num,0)+1);
            }

           //System.out.println(map);
             for(Map.Entry<Integer,Integer> entry:map.entrySet()){
                 int num = entry.getKey();
                 //System.out.println(num);
                 int count = entry.getValue();
                 if(count>1){
                     StringJoiner sd=new StringJoiner("+");
                     for(int i=0;i<count;i++){
                         sd.add(String.valueOf(num));
                     }
                     int sum = num*count;
                     System.out.println(sd+" = "+sum);
                 }
             }


    }
}
