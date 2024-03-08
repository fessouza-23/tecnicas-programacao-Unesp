class Pessoa {
    String nome;
    int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}

class Aluno extends Pessoa {
    int RA;

    public Aluno(String nome, int idade, int RA) {
        super(nome, idade);
        this.RA = RA;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "RA=" + RA +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}

class main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Joao", 20);
        Aluno aluno = new Aluno("Victor", 21, 23102012);
        System.out.println(pessoa);
        System.out.println(aluno);
    }
}