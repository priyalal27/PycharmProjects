package InnerClasses;

public class staticOuter {

    static void f1() {
        System.out.println("Inside Outer class");
    }

    // ✅ Static nested class (not regular inner class)
    public static class staticInner {
        static void f2() {
            System.out.println("Inside Static Inner class (static method)");
        }

        void f3() {
            System.out.println("Inside Static Inner class (non-static method)");
        }
    }

    public static void main(String[] args) {
        // Call outer static method
        staticOuter.f1();

        // Call static method of static inner class
        staticOuter.staticInner.f2();

        // Create object of static inner class
        staticOuter.staticInner st = new staticOuter.staticInner();
        st.f3();
    }
}
