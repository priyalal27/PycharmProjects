package Arrays;

public class twoDimensionalArray {
    public static void main(String[] args) {

        int[][] td= new int[3][3];

        int[][] td1= {{2,3,4},{1,4,6},{3,5,4}};

        for (int i = 0; i <td1.length ; i++) {
            for (int j = 0; j < td1.length; j++) {
                System.out.print(td1[i][j]);
            }
            System.out.println("");
        }


    }
}
