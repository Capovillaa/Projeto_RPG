package rpgtexto;

public class Inimigo extends Personagem {

    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int experienciaDadaAoMorrer) {
        super(nome, pontosVida, ataque, defesa, experienciaDadaAoMorrer);
    }

    // Construtor de Cópia
    public Inimigo(Inimigo outro) {
        super(outro);
    }

    @Override
    public String toString() {
        return "Inimigo: " + super.toString();
    }
}

