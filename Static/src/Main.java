class AnIntegerNamedX {
    public static int x; // static define que a variavel x sera somente da classe, ou seja, objetos que vem da classe
    // utilizarao a variavel x de dentro da classe e nao suas proprias variaveis

    public int x() {
        return x;
    }
    public void setX(int newX) {
        x = newX;
    }
}

public class Main {
    public static void main(String[] args) {
        AnIntegerNamedX myX = new AnIntegerNamedX();
        AnIntegerNamedX anotherX = new AnIntegerNamedX();
        myX.setX(1);
        anotherX.setX(2);
        System.out.println("MyX.x = " + myX.x());
        System.out.println("AnotherX.x = " + anotherX.x());
    }
}