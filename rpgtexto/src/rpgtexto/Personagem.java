package rpgtexto;

import java.util.Collection;
import java.util.Scanner;

public abstract class Personagem implements Cloneable {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defesa;
    private int nivel;
    private int experiencia;
    private Inventario inventario;
    private int experienciaParaProximoNivel;
    private int experienciaDadaAoMorrer;
    private int turnosParaRecargaEspecial;
    private final int COOLDOWN_MAXIMO = 3;
    public static Scanner scanner = new Scanner(System.in);

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int experienciaDadaAoMorrer) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.experienciaDadaAoMorrer = experienciaDadaAoMorrer;
        this.nivel = 1;
        this.experiencia = 0;
        this.inventario = new Inventario();
        this.experienciaParaProximoNivel = 100;
        this.turnosParaRecargaEspecial = 0;

    }
    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.experiencia = outro.experiencia;
        this.experienciaParaProximoNivel = outro.experienciaParaProximoNivel;
        this.experienciaDadaAoMorrer = outro.experienciaDadaAoMorrer;
        this.inventario = new Inventario(outro.inventario);
        this.turnosParaRecargaEspecial = outro.turnosParaRecargaEspecial;
    }

    public String getNome() { return this.nome; }

    public int getPontosVida() {
        return this.pontosVida;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public int getNivel() { return this.nivel; }

    public int getExperiencia() { return this.experiencia; }

    public int getExperienciaParaProximoNivel() { return this.experienciaParaProximoNivel; }

    public int getExperienciaDadaAoMorrer() { return this.experienciaDadaAoMorrer; }

    public Inventario getInventario() { return this.inventario; }

    public int getTurnosParaRecargaEspecial() { return this.turnosParaRecargaEspecial; }

    public void setPontosVida(int vida) { this.pontosVida = vida; }

    public void setAtaque(int novoValorAtaque) { this.ataque = novoValorAtaque; }

    public void setDefesa(int novoValorDefesa) { this.defesa = novoValorDefesa; }

    public void setTurnosParaRecargaEspecial() { this.turnosParaRecargaEspecial = COOLDOWN_MAXIMO; };

    private void uparNivel(int novoNivel) {
        this.nivel = novoNivel;
        System.out.println("\n---------------------------------");
        System.out.println(this.nome + " subiu para o Nível " + this.nivel + "!");
        System.out.println("---------------------------------");

        int bonusAtaque = 5;
        int bonusDefesa = 5;
        int bonusVida = 10;

        setAtaque(getAtaque() + bonusAtaque);
        setDefesa(getDefesa() + bonusDefesa);
        setPontosVida(getPontosVida() + bonusVida);

        this.experienciaParaProximoNivel = this.experienciaParaProximoNivel * 2;

        System.out.println("Status atualizados:");
        System.out.println("Vida: " + this.pontosVida + " (+" + bonusVida + ")");
        System.out.println("Ataque: " + this.ataque + " (+" + bonusAtaque + ")");
        System.out.println("Defesa: " + this.defesa + " (+" + bonusDefesa + ")");
        System.out.println("XP necessária para o Nível " + (this.nivel + 1) + ": " + this.experienciaParaProximoNivel);
    }

    public void ganharExperiencia(int experienciaGanha){
        this.experiencia += experienciaGanha;
        System.out.println(this.nome + " ganhou " + experienciaGanha + " de XP.");

        while( this.experiencia >= this.experienciaParaProximoNivel) {

            this.experiencia -= this.experienciaParaProximoNivel;
            this.uparNivel(getNivel() + 1);
        }

        System.out.println("XP Total: " + this.experiencia + "/" + this.experienciaParaProximoNivel);

    }

    public boolean estaVivo(){
        return this.pontosVida > 0;
    }

    public int calcularValorAtaque(){

        int valorRolagemDado = Dado.rolarDado();
        int valorDoAtaque = this.ataque + valorRolagemDado;

        System.out.println(this.nome + " prepara um ataque com força " + valorDoAtaque + " (Ataque: " + this.ataque + " + Dado: " + valorRolagemDado + ")");
        return valorDoAtaque;
    }

    public void receberDano(int dano) {
        int defesa = this.defesa;
        int vidaPersonagem = this.pontosVida;

        if ( defesa >= dano ) {
            System.out.println(this.nome + " defendeu o ataque!!!");
        } else {
            int danoRecebido = dano - defesa;

            vidaPersonagem -= danoRecebido;
            setPontosVida(vidaPersonagem);

            System.out.println(this.nome + " defendeu " + this.getDefesa() + " de dano sofrendo um ataque de " + danoRecebido + " dano!!!");
        }
    }

    public boolean tentarUsarEspecial(Personagem alvo) {
        if (this.turnosParaRecargaEspecial > 0) {
            System.out.println("O Poder Especial ainda está em recarga! (" + this.turnosParaRecargaEspecial + " turnos restantes).");
            return false;
        }

        this.usarPoderEspecial(alvo);

        this.setTurnosParaRecargaEspecial();

        return true;
    }

    public abstract void usarPoderEspecial(Personagem alvo);

    public abstract void tomarAcao (Personagem alvo);

    public void diminuirRecarga() {
        if (this.turnosParaRecargaEspecial > 0) {
            this.turnosParaRecargaEspecial--;
            if (this.turnosParaRecargaEspecial == 0) {
                System.out.println(this.getNome() + ": Poder Especial pronto!");
            }
        }
    }

    public void atacar(Personagem inimigo) {

        int valorDoAtaque = calcularValorAtaque();
        System.out.println(this.nome + " ataca " + inimigo.getNome() + "! com um ataque basico.");
        inimigo.receberDano(valorDoAtaque);
    }

    public void batalhar(Personagem inimigo){
        System.out.println("---------------BATALHA INICIADA---------------");

        int turno = 1;

        while(this.estaVivo() && inimigo.estaVivo()){

            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println(this.getNome() + " (Vida: " + this.getPontosVida() + ") | Cooldown Especial: " + this.getTurnosParaRecargaEspecial() + "t");
            System.out.println(inimigo.getNome() + " (Vida: " + inimigo.getPontosVida() + ") | Cooldown Especial: " + inimigo.getTurnosParaRecargaEspecial() + "t");
            System.out.println("---------------------------------");

            System.out.println(">> Turno de " + this.getNome() + ":");
            this.tomarAcao(inimigo);

            if (!inimigo.estaVivo())
            {
                break;
            }

            System.out.println("---------------------------------");

            System.out.println(">> Turno de " + inimigo.getNome() + ":");
            inimigo.tomarAcao(this);

            if (!this.estaVivo())
            {
                break;
            }

            this.diminuirRecarga();
            inimigo.diminuirRecarga();

            turno++;
        }

        System.out.println("\n---------------BATALHA FINALIZADA---------------");

        if( this.estaVivo() && !inimigo.estaVivo()){
            this.setPontosVida(this.getPontosVida() + 25);
            System.out.println(this.getNome() + " Venceu a batalha!!");
            this.ganharExperiencia(inimigo.getExperienciaDadaAoMorrer());
            this.saquearLoot(inimigo);

        } else if( !this.estaVivo() && inimigo.estaVivo()){
            System.out.println(inimigo.getNome() + " Te derrotou na batalha!!");
        } else{
            System.out.println("os dois morreram");
        }

    }

    private void saquearLoot(Personagem perdedor) {
        Inventario inventarioVencedor = this.getInventario();
        Inventario inventarioPerdedor = perdedor.getInventario();

        Collection<Item> itensDoPerdedor = inventarioPerdedor.getTodosOsItens();

        if( itensDoPerdedor.isEmpty()){
            System.out.println(perdedor.getNome() + " não tinha itens para saquear.");
            return;
        }

        System.out.println(this.getNome() + " pega o loot de " + perdedor.getNome() + ":");

        for( Item item: itensDoPerdedor){
            // MUDANÇA ESSENCIAL: Clonar o item antes de adicionar ao novo inventário
            Item itemCopia = item.clone();

            System.out.println("Pegando " + itemCopia.getQuantidade() + "x " + itemCopia.getNome());
            inventarioVencedor.adicionarItem(itemCopia);
        }

        inventarioPerdedor.esvaziar();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Clona o Personagem e seu Inventário
        Personagem clone = (Personagem) super.clone();

        // Faz a cópia profunda do Inventário
        clone.inventario = (Inventario) this.inventario.clone();

        return clone;
    }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s | Vida: %d | Ataque: %d | Defesa: %d | Nível: %d | XP: %d/%d",
                this.nome,
                this.pontosVida,
                this.ataque,
                this.defesa,
                this.nivel,
                this.experiencia,
                this.experienciaParaProximoNivel
        );
    }


}


