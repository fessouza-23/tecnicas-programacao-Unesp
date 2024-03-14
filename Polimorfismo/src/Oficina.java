import java.util.Random;

class Oficina {
    public Veiculo proximo() {
        Veiculo v;
        int R;
        Random r = new Random();
        R = r.nextInt(2);
        if(R == 0) {
            v = new Carro();
        } else {
            v = new Moto();
        }
        return v;
    }
    public void imprimir(Veiculo v) {
        v.verificarL();
        v.reparar();
    }
}

class GerenciaOficina {
    public static void main(String[] args) {
        Oficina of = new Oficina();
        Veiculo vec;
        for (int i = 0; i < 4; i++) {
            vec = of.proximo();
            of.imprimir(vec);
        }
    }
}