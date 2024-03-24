import java.util.*;

public class Vector_Iteractor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vector vetor = new Vector();
        char resp;
        do {
            System.out.print("Add valor: ");
            vetor.add(scanner.nextInt());
            System.out.println("Continuar? (s ou n)");
            resp = scanner.next().charAt(0);
        } while(resp == 's');
        System.out.println(vetor);

        // Remover duplicatas: converter vector para objeto HashSet
        LinkedHashSet hashset = new LinkedHashSet(vetor);
        // limpa vector
        vetor.clear();
        // add valores do hashset no vetor
        vetor.addAll(hashset);
        System.out.println(vetor);
    }
}