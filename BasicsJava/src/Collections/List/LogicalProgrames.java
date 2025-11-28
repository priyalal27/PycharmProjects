package Collections.List;

public class LogicalProgrames {

    public boolean helperFunc(int arr,int[] key){
        for (int i = 0; i < key.length; i++) {
            if(arr==key[i]){
                return true;
            }

            return false;

        }
            return true;
    }

    public void remove_duplicates(){
        /*Remove duplicates from a List
          Input: [1, 2, 3, 2, 4, 1, 5]
          Output: [1, 2, 3, 4, 5]*/

        int[] output={};
        int[] Input={1, 2, 3, 2, 4, 1, 5};
        for (int i = 0; i < Input.length; i++) {
            if(helperFunc(Input[i],output)){
                //output = appendToArray(output, num);
            }
            else{

            }
        }
    }


    public static void main(String[] args) {
        LogicalProgrames lp=new LogicalProgrames();

    }


}
