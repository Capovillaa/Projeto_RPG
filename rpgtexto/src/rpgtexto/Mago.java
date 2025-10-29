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

    @Override
    public void usarPoderEspecial(Personagem alvo) {
        System.out.println("---------------------------------");
        System.out.println(this.getNome() + " conjura um Relâmpago Potencializado (PODER ESPECIAL)!");

        int danoBase = this.getAtaque();
        int danoFixo = danoBase * 3;

        alvo.receberDano(danoFixo);

        System.out.println(" IMPACTO MÁXIMO! O feitiço atingiu " + alvo.getNome() + " com dano garantido de " + danoFixo + "!");
        System.out.println("---------------------------------");
    }
}

