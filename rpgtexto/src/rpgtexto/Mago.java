package rpgtexto;

public class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, 80, 30, 5, 0);
        System.out.println("Mago " + nome + " criado!");
    }

    // Construtor de Cópia
    public Mago(Mago outro) {
        super(outro);
    }

    @Override
    public String toString() {
        return "Classe: Mago | " + super.toString();
    }
}

