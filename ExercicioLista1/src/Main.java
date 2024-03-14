abstract class MostraDados {
    abstract void mostra(int l);
    abstract void mostra(float f);
}

class Mostra extends MostraDados {
    @Override
    void mostra(int l) {
        System.out.println("Inteiro: " + l);
    }

    @Override
    void mostra(float f) {
        System.out.println("Flutuante: " + f);
    }
}

class ProgPrincipal {
    static int valor = 123;
    static float x = 4.56F;

    static void mostraValores(MostraDados db) {
        db.mostra(x);
        db.mostra(valor);
    }

    public static void main(String[] args) {
        Mostra mostra = new Mostra();
        mostraValores(mostra);
    }
}
