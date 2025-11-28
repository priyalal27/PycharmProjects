public class test {


    public static void main(String[] args) {
        System.out.println("Print");

        test t =new test();
        t.num(44);

        t.while_d();
        t.for_d();
        t.arr();
    }


    public void num(int n){
        if (n>10 && n<20){
            System.out.println("n is greater than 10 "+n);
        }
        else if (n>20){
            System.out.println("n is greater than 20 ----> "+n);
        }
        else {
            System.out.println("in else block "+n);
        }
    }


    public void while_d(){
        int i=10;
        while(1<i){
            System.out.println(i);
            i--;
        }

    }


    public void for_d(){

        for(int i=0;i<5;i++){
            System.out.println("");
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }

        }

    }


    public void arr(){
        String[] arr={"Volvo", "BMW", "Ford", "Mazda"};
        int[] numbs;


        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}




