class Caneta {
    int r, g, b;
    public Caneta(int ar, int ag, int ab) {
        r = ar; g = ag; b = ab;
    }
    public void leCaneta(CorRGB cor) {
        cor.red = r; cor.green = g; cor.blue = b;
    }
}

class CorRGB {
    public int red, green, blue;
}

class Main {
    public static void main(String[] args) {
        CorRGB c = new CorRGB();
        Caneta caneta = new Caneta(0, 0, 255);
        caneta.leCaneta(c);
        System.out.println("Cor: (" + c.red + ", " + c.green + ", " + c.blue + ")");
    }
}