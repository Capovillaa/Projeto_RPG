package rpgtexto;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 100, 22, 8, 0); // Status base equilibrados
        System.out.println("Arqueiro " + nome + " criado!");
    }

    // Construtor de Cópia
    public Arqueiro(Arqueiro outro) {
        super(outro);
    }

    @Override
    public String toString() {
        return "Classe: Arqueiro | " + super.toString();
    }
}

