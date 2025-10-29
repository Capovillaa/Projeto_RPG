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

    @Override
    public void usarPoderEspecial(Personagem alvo) {
        System.out.println("---------------------------------");
        System.out.println(this.getNome() + " concentra sua fúria em uma Cura de Combate (PODER ESPECIAL)!");

        int valorCura = this.getDefesa() + 15;

        int vidaAtual = this.getPontosVida();
        int novaVida = vidaAtual + valorCura;

        this.setPontosVida(novaVida);

        System.out.println(this.getNome() + " se cura em " + valorCura + " pontos de vida.");
        System.out.println("Vida atual: " + this.getPontosVida());

        System.out.println("---------------------------------");
    }
}

