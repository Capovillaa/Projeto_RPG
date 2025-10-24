package rpgtexto;

import java.util.Collection;

public class Personagem {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defesa;
    private int nivel;
    private int experiencia;
    private Inventario inventario;
    private int experienciaParaProximoNivel;
    private int experienciaDadaAoMorrer;

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

    public void setPontosVida(int vida) { this.pontosVida = vida; }

    public void setAtaque(int novoValorAtaque) { this.ataque = novoValorAtaque; }

    public void setDefesa(int novoValorDefesa) { this.defesa = novoValorDefesa; }

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
        Dado dado = new Dado();

        int valorRolagemDado = dado.rolarDado();
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

            System.out.println(this.nome + " sofreu um ataque de " + danoRecebido + " dano!!!");
        }
    }

    public void atacar(Personagem inimigo) {

        int valorDoAtaque = calcularValorAtaque();
        System.out.println(this.nome + " ataca " + inimigo.getNome() + "!");
        inimigo.receberDano(valorDoAtaque);
    }

    public void batalhar(Personagem inimigo){
        System.out.println("---------------BATALHA INICIADA---------------");
        System.out.println(this.nome + " (Vida: " + this.pontosVida + ") VS " + inimigo.getNome() + " (Vida: " + inimigo.getPontosVida() + ")");

        int turno = 1;

        while(this.estaVivo() && inimigo.estaVivo()){
            System.out.println("\n--- Turno " + turno + " ---");
            int danoDoHeroi = this.calcularValorAtaque();
            int danoDoInimigo = inimigo.calcularValorAtaque();
            System.out.println(this.nome + " ataca!!");
            inimigo.receberDano(danoDoHeroi);
            System.out.println(inimigo.nome + " ataca!!");
            this.receberDano(danoDoInimigo);

            turno++;
        }
        System.out.println("\n---------------BATALHA FINALIZADA---------------");

        if( this.estaVivo() && !inimigo.estaVivo()){

            System.out.println(this.getNome() + "Venceu a batalha!!");
            this.ganharExperiencia(inimigo.getExperienciaDadaAoMorrer());
            this.saquearLoot(inimigo);

        } else if( !this.estaVivo() && inimigo.estaVivo()){
            System.out.println(inimigo.getNome() + "Te derrotou na batalha!!");
        } else{
            System.out.println();
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
            System.out.println("Pegando " + item.getQuantidade() + "x " + item.getNome());
            inventarioVencedor.adicionarItem(item);
        }

        inventarioPerdedor.esvaziar();
    }


}


