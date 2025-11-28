    public class javamethods {


        int res=1;
        int x;

        static boolean mymethod1(int a, String b, char c, boolean f){
            System.out.println("Testing static method  --> "+ a+b+c+f);
            if(f==true){
                return f;
            }

            return f;
        }

        javamethods(int y){
                x=y;
        }


        public int recur(int b){
            if(b>1){
                res=b*res;
                b=b-1;
                recur(b);
            }
            return res;

        }



        public static void main(String[] args) {
            boolean result=mymethod1(20,"Priya",'d',true);
            System.out.println("Printing result "+result);

            javamethods jm=new javamethods(90);
            //new javamethods creates a object of type javamethods
            // jm creates a reference and stores adddress of the created object .

            int value=jm.recur(5);
            System.out.println("Result from recursive function "+value);
        }
    }
