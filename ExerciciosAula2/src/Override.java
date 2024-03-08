class A {
    public void m1() {
        System.out.println("A.M1");
        m2();
    }
    public void m2() {
        System.out.println("A.M2");
    }
}
class B extends A {
    public void m2() {
        System.out.println("B.M2");
    }
}
class PP {
    public static void main(String[] args) {
        A objA = new A();
        B objB = new B();
        objA.m1();
        objB.m1();
    }
}