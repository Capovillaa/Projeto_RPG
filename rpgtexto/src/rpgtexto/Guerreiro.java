package rpgtexto;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 120, 25, 10, 0);
        System.out.println("Guerreiro " + nome + " criado!");
    }

    // Construtor de Cópia
    public Guerreiro(Guerreiro outro) {
        super(outro);
    }

    @Override
    public String toString() {
        return "Classe: Guerreiro | " + super.toString();
    }
}

