public class Ponto2D {
    double x, y;
    Ponto2D() {
    }
    Ponto2D(double xl, double yl) {
        x = xl; y = yl;
    }

    Ponto2D(Ponto2D ponto) {
        x = ponto.getX(); y = ponto.getY();
    }

    Double CalcularDistancia(Ponto2D ponto) {
        return Math.sqrt(Math.pow(this.x - ponto.getX(), 2) + Math.pow(this.y - ponto.getY(), 2));
    }

    @Override
    public String toString() {
        return "Ponto2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

class exec {
    public static void main(String[] args) {
        Ponto2D ponto1 = new Ponto2D();
        ponto1.setX(3);
        ponto1.setY(4);
        Ponto2D ponto2 = new Ponto2D(6.0, 8.0);
        Ponto2D ponto3 = new Ponto2D(ponto1);

        System.out.println("Ponto 1: " + ponto1);
        System.out.println("Ponto 2: " + ponto2);
        System.out.println("Ponto 3: " + ponto3);

        double distancia = ponto1.CalcularDistancia(ponto2);
        System.out.println("Distancia entre os pontos: " + distancia);
    }
}
