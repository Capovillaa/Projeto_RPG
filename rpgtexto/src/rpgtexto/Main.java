package rpgtexto;

public class Main {

    public static void main(String[] args) {

        // --- 1. PREPARAÇÃO ---

        // Criando itens para o inimigo
        // O Orc terá 2 poções, 1 espada e 50 de ouro
        System.out.println("--- CONFIGURANDO O CENÁRIO ---");
        Item pocao = new Item("Poção de Vida", "Cura 20 HP", "Cura", 2);
        Item espada = new Item("Espada Curta Gasta", "Uma espada velha", "Equipamento", 1);
        Item ouro = new Item("Ouro", "Moedas de ouro", "Dinheiro", 50);

        // Criando os personagens
        // Herói: Vida: 100, Ataque: 20, Defesa: 10
        Personagem heroi = new Personagem("Herói Valente", 100, 20, 10, 50);

        // Inimigo: Vida: 50, Ataque: 15, Defesa: 5
        // XP ao morrer: 120. (Importante! O herói precisa de 100 para upar, então 120 testará o uparNivel)
        Personagem orc = new Personagem("Orc Grunt", 50, 15, 5, 120);

        // Adicionando os itens ao inventário do Orc
        orc.getInventario().adicionarItem(pocao);
        orc.getInventario().adicionarItem(espada);
        orc.getInventario().adicionarItem(ouro);
        System.out.println("----------------------------------\n");


        // --- 2. MOSTRAR ESTADO INICIAL ---
        System.out.println("--- ESTADO ANTES DA BATALHA ---");
        System.out.println("Inventário de " + heroi.getNome() + ":");
        System.out.println(heroi.getInventario().toString());
        System.out.println("Inventário de " + orc.getNome() + ":");
        System.out.println(orc.getInventario().toString());
        System.out.println("---------------------------------\n");


        // --- 3. EXECUÇÃO DO TESTE (A BATALHA) ---
        // Esta única chamada irá testar:
        // 1. batalhar()
        // 2. ganharExperiencia() (quando o orc morrer)
        // 3. uparNivel() (pois o orc dá 120 XP > 100 XP necessários)
        // 4. saquearLoot() (pois o orc tem itens)
        heroi.batalhar(orc);


        // --- 4. MOSTRAR ESTADO FINAL ---
        System.out.println("\n--- ESTADO APÓS A BATALHA ---");

        // Verificando o inventário do Herói (deve ter os itens do orc)
        System.out.println("Inventário de " + heroi.getNome() + ":");
        System.out.println(heroi.getInventario().toString());

        // Verificando o inventário do Orc (deve estar vazio)
        System.out.println("Inventário de " + orc.getNome() + ":");
        System.out.println(orc.getInventario().toString());

        // Verificando os status do Herói (deve estar Nível 2)
        System.out.println("\n--- STATUS FINAL DO HERÓI ---");
        System.out.println(heroi.getNome() + " - Nível: " + heroi.getNivel());
        System.out.println("XP: " + heroi.getExperiencia() + "/" + heroi.getExperienciaParaProximoNivel());
        System.out.println("Vida: " + heroi.getPontosVida());
        System.out.println("Ataque: " + heroi.getAtaque());
        System.out.println("Defesa: " + heroi.getDefesa());
        System.out.println("-------------------------------");
    }
}