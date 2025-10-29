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

    @Override
    public void usarPoderEspecial(Personagem alvo) {
        System.out.println("---------------------------------");
        System.out.println(this.getNome() + " dispara uma flecha concentrada (PODER ESPECIAL)!");

        // Dano fixo (ex: 2x o ataque base + 5)
        int danoFixo = this.getAtaque() * 2 + 5;

        alvo.receberDano(danoFixo);

        System.out.println(" Dano garantido de " + danoFixo + "!");
        System.out.println("---------------------------------");
    }
}

