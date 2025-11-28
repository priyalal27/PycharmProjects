package Collections.List;

import java.util.*;

public class ArrayListClass {

    public void convert_from_array_to_list(String[] arr){
        List<String> l =new LinkedList<>(Arrays.asList(arr));
        Set<String> s= new TreeSet<>(l);
        System.out.println(s);
    }

    public void array_basic_ops(){
        ArrayList<Integer> al=new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            al.add(i);
        }
        System.out.println("Before Operation "+al);
        for (int i = 0; i < al.size(); i++) {
            if(al.get(i) %2==0){
                al.remove(i);
            }
        }
        al.add(null);
        al.add(null);
        System.out.println(al);
    }

    public void remove_duplicates(Integer[] arr) {
        List<Integer> l = new LinkedList<>(Arrays.asList(arr));
        Set<Integer> s = new TreeSet<>(l);
        System.out.println("Removed duplicates using set "+s);


        // Removing duplicates without in built reverse method
        Integer inp[]={1,1,1,2,4,3,2,1,2,5,3,4,6,7,4,3,2,2,1,34,32,837,56,6,34};
        ArrayList<Integer> al=new ArrayList<Integer>();
        for (int i = 0; i < inp.length; i++) {
            if (!al.contains(inp[i])){
                al.add(inp[i]);
            }
        }
        System.out.println("Removed duplicates using logic "+al);
    }

    public void remove_certain_occurances(String[] arr,String remove){
                ArrayList<String> newarraylist=new ArrayList<String>();
                for(String a:arr){
                    if (!a.equals(remove)){
                            newarraylist.add(a);
                    }
                }
        System.out.println("New ArrayList "+newarraylist);
    }

    public void find_min_max_arrayList(Integer[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;

                }
            }

        }
        System.out.println("Max"+arr[arr.length-1]);
        System.out.println("Min"+arr[0]);

    }

    public void reverse_an_array(Integer[] arr){
        ArrayList<Integer> aa=new ArrayList<Integer>();
        for (int i = arr.length-1; i >=0 ; i--) {
            aa.add(arr[i]);
        }
        System.out.println("Reversed arraylist => "+aa);
    }

    public void merge_two_arraylist_sort_result(Integer[] inp1,Integer[] inp2){
        ArrayList<Integer> al1=new ArrayList<Integer>(Arrays.asList(inp1));
        ArrayList<Integer> al2=new ArrayList<Integer>(Arrays.asList(inp2));

        al1.addAll(al2);
        System.out.println("Merged two array : "+al1);

        //sorting
        for (int i = 0; i < al1.size(); i++) {
            for (int j = 0; j < al1.size()-i-1; j++) {
                if(al1.get(j) > al1.get(j + 1)){
                    Integer temp=al1.get(j);
                    al1.set(j,al1.get(j + 1));
                    al1.set(j + 1, temp);

                }
            }
        }
        System.out.println("merge_two_arraylist_sort_result "+al1);
    }

    public void insertion_of_two_arraylist(Integer[] inp1,Integer[] inp2){
        ArrayList<Integer> al1=new ArrayList<Integer>();
        for (int i = 0; i < inp1.length; i++) {

        }

    }

    public static void main(String[] args) {


        ArrayListClass alc =new ArrayListClass();
        String[] s={"Priya","Divya","Hina","Rajkumar"};
        alc.convert_from_array_to_list(s);

        // remove duplicates
        Integer[] input={1,1,1,2,4,3,2,1,2,5,3,4,6,7,4,3,2,2,1,34,32,56,6,34};
        alc.remove_duplicates(input);

        String[] input1={"Priya","Divya","Hina","Priya","Rajkumar","Divya","Priya"};
        alc.remove_certain_occurances(input1,"Priya");

        Integer[] input2={1, 2, 3, 4, 5, 6, 7, 32, 34, 56};
        alc.find_min_max_arrayList(input2);
        alc.reverse_an_array(input2);

        alc.merge_two_arraylist_sort_result(input,input2);

        Integer[] a1={1,2,3,4};
        Integer[] a2={3,4,5,6};
        alc.insertion_of_two_arraylist(a1,a2);


    }

}
