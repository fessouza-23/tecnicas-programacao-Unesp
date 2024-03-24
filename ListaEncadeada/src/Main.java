class No {
    Object obj;
    No prox;

    No(Object obj, No prox) {
        this.obj = obj;
        this.prox = prox;
    }
}

class Lista {
    No inicio = null;

    void insereInicio(Object obj) {
        No n = new No(obj, inicio);
        inicio = n;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        if (inicio != null) {
            s.append("(").append(inicio.obj).append(")");
            No p = inicio.prox;
            while(p != null) {
                s.append(" -> (").append(p.obj).append(")");
                p = p.prox;
            }
        }
        return s.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Lista l = new Lista();
        for (int i = 0; i < 10; i++) {
            l.insereInicio("Numero" + i);
        }
        System.out.println(l);
    }
}
