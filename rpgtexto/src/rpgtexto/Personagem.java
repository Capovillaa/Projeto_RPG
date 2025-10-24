package rpgtexto;

public class Personagem {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defesa;
    private int nivel;
    private int experiencia;
    private Inventario inventario;

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel, int experiencia) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public void setPontosVida(int vida) {
        this.pontosVida = vida;

    }

    public void receberDano(int dano) {
        int defesa = this.defesa;
        int vidaPersonagem = this.pontosVida;
        if (defesa >= dano) {
            System.out.println(this.nome + " defendeu o ataque!!!");
        } else {
            int danoRecebido = dano - defesa;
            vidaPersonagem -= danoRecebido;
            System.out.println(this.nome + " sofreu um ataque de " + danoRecebido + " dano!!!");
            setPontosVida(vidaPersonagem);
        }
    }

    public int atacar(Personagem inimigo) {

        Dado dado = new Dado();
        int valorRolagemDado = dado.rolarDado();
        int valorDoAtaque = this.ataque + valorRolagemDado;

        return valorDoAtaque;
    }


    public boolean estaVivo(){
        return this.pontosVida > 0;
    }

    /*

    public void ganharExperiencia(){

    }

    public void ganharNivel(){

    }

    public void usarItem(){


    }*/
}


