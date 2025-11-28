package Interface;

interface BMW extends car{

    void start();
    void stop();

    static void automatic() {
        System.out.println("Inside Interface method body for AUTOMATIC()");
    }

}
