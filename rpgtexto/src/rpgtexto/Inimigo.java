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
    public boolean tentarUsarEspecial(Personagem alvo) {
        if (this.getTurnosParaRecargaEspecial() > 0) return false;

        this.usarPoderEspecial(alvo);
        this.setTurnosParaRecargaEspecial();

        return true;
    }

    @Override
    public void tomarAcao (Personagem alvo) {
        if(this.getTurnosParaRecargaEspecial() == 0 && Dado.caraOuCoroa()) {
            this.tentarUsarEspecial(alvo);
        } else {
            this.atacar(alvo);
        }
    }

    @Override
    public String toString() {
        return "Inimigo: " + super.toString();
    }

    @Override
    public void usarPoderEspecial(Personagem alvo) {
        System.out.println(this.getNome() + " Utiliza seu ataque mortal na direção do(a) " + alvo.getNome());

        int danoCausado = this.getAtaque() * 2;
        alvo.receberDano(danoCausado);

        System.out.println(" IMPACTO MÁXIMO! O ataque mortal atingiu " + alvo.getNome());
        System.out.println("---------------------------------");
    }

}

