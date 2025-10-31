package rpgtexto;

import java.util.Scanner;

public class Jogo {

    private static Scanner scanner = new Scanner(System.in);
    private Personagem heroi;
    private Personagem savePoint;

    public Jogo() {
        iniciarJogo();
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
    }

    // BLOCO 1: MÉTODOS DE CONTROLE

    public void iniciarJogo() {
        apresentacao();
        criarPersonagem();
        criarCheckpoint();
        iniciarJornada();
    }

    private void apresentacao() {
        System.out.println("==================================================");
        System.out.println("        PROJETO RPG DE TEXTO: O ELMO PERDIDO        ");
        System.out.println("==================================================");
    }

    private void criarPersonagem() {
        String nome = "";
        String escolhaClasse = "";
        boolean classeValida = false;

        System.out.println("\nAntes de começar, digite o nome do seu herói:");
        nome = scanner.nextLine().toUpperCase();

        while (!classeValida) {
            System.out.println("\nEscolha sua Classe:");
            System.out.println(" [1] GUERREIRO (Defesa e Cura garantida)");
            System.out.println(" [2] MAGO      (Ataque mágico fixo e alto)");
            System.out.println(" [3] ARQUEIRO  (Dano de precisão garantido)");
            System.out.print("Sua escolha (1, 2 ou 3): ");

            escolhaClasse = scanner.nextLine();

            switch (escolhaClasse) {
                case "1":
                    this.heroi = new Guerreiro(nome);
                    classeValida = true;
                    break;
                case "2":
                    this.heroi = new Mago(nome);
                    classeValida = true;
                    break;
                case "3":
                    this.heroi = new Arqueiro(nome);
                    classeValida = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite 1, 2 ou 3 para escolher a classe.");
            }
        }

        Item pocaoCura = new Item("POÇÃO DE CURA", "Cura 25 HP", "cura", 25, 1, 1);
        this.heroi.getInventario().adicionarItem(pocaoCura);

        System.out.println("\n--------------------------------------------------");
        System.out.println("HERÓI PRONTO: " + heroi.getNome() + " DA CLASSE " + heroi.getClass().getSimpleName().toUpperCase());
        System.out.println("--------------------------------------------------");
    }

    private void criarCheckpoint() {
        try {
            if (heroi instanceof Guerreiro) {
                this.savePoint = new Guerreiro((Guerreiro) heroi);
            } else if (heroi instanceof Mago) {
                this.savePoint = new Mago((Mago) heroi);
            } else if (heroi instanceof Arqueiro) {
                this.savePoint = new Arqueiro((Arqueiro) heroi);
            }
            System.out.println("Checkpoint criado! O estado atual foi salvo.");
        } catch (Exception e) {
            System.out.println("ERRO ao criar Checkpoint: " + e.getMessage());
        }
    }

    private void restaurarCheckpoint() {
        if (this.savePoint != null) {
            try {
                if (savePoint instanceof Guerreiro) {
                    this.heroi = new Guerreiro((Guerreiro) savePoint);
                } else if (savePoint instanceof Mago) {
                    this.heroi = new Mago((Mago) savePoint);
                } else if (savePoint instanceof Arqueiro) {
                    this.heroi = new Arqueiro((Arqueiro) savePoint);
                }
                System.out.println("\n--- Ponto de Salvamento Restaurado! ---");
                System.out.println(heroi.getNome() + " retornou ao último Checkpoint.");
            } catch (Exception e) {
                System.out.println("ERRO ao restaurar Checkpoint.");
            }
        }
    }

    // bloco 2: MÉTODOS DE ENCONTRO

    private void encontroInimigo(Inimigo inimigo) {
        System.out.println("\n==================================================");
        System.out.println("!!! COMBATE INICIADO !!! Você se depara com um " + inimigo.getNome() + "!");
        System.out.println("==================================================");

        loopCombate(inimigo);
    }

    private void encontrarItem(String nome, String descricao, String tipoEfeito, int valorDoEfeito, int quantidade, int codigoDoItem) {

        Item itemEncontrado = new Item(nome, descricao, tipoEfeito, valorDoEfeito, quantidade, codigoDoItem);

        System.out.println("\nVocê encontra " + quantidade + "x " + nome + "!");
        this.heroi.getInventario().adicionarItem(itemEncontrado);
    }


    private void loopCombate(Personagem inimigo) {
        heroi.batalhar(inimigo);

        if (!heroi.estaVivo()) {
            System.out.println("\nVocê foi derrotado! Todo o seu esforço se esvai... Voltando ao último ponto de salvamento.");
            processarDerrota(inimigo.getNome());
        }
    }

    private void processarDerrota(String nomeInimigoDerrotado) {
        restaurarCheckpoint();

        if (nomeInimigoDerrotado.contains("Golem") || nomeInimigoDerrotado.contains("Guardião")) {
            irParaRuinaNorte();
        } else if (nomeInimigoDerrotado.contains("Feiticeira")) {
            iniciarUltimoCapitulo();
        } else {
            iniciarJornada();
        }
    }

    //  BLOCO 3: NARRATIVA DA JORNADA

    private void iniciarJornada() {

        System.out.println("\n*** CAPÍTULO I: A FLORESTA DAS SOMBRAS ***");
        System.out.println("Você está na Taverna do Dragão Adormecido. O cheiro de cerveja barata e fumaça de tabaco paira no ar. De repente, uma sombra esguia e vestida de seda para à sua mesa.");
        System.out.println("O emissário, com olhos que brilham com um toque de magia arcana, sussurra:");
        System.out.println("— " + heroi.getNome() + ", o Elmo de Aethelred não é apenas uma joia; ele pulsa com o poder de mil reis. Foi roubado pela Feiticeira da Névoa e a sua localização é desconhecida.");
        System.out.println("— Recupere o Elmo e o reino lhe cobrirá de ouro. Falhe, e o poder arcano dele corromperá tudo.");
        System.out.println("\nCom o peso do destino nos ombros, você viaja por dias, até alcançar a temida Floresta das Sombras, onde as árvores são retorcidas e o silêncio é ensurdecedor.");

        primeiroDilema();
    }

    private void primeiroDilema() {
        System.out.println("\nVocê se depara com uma bifurcação na trilha, sob a sombra constante das árvores milenares:");
        System.out.println("Caminho 1: Mais sombrio e silencioso. O ar está denso e frio, propício a emboscadas.");
        System.out.println("Caminho 2: Mais iluminado, porém é possível ouvir o ruído de ossos estalando e um canto gutural vindo do riacho.");

        String escolhaCaminho = "";
        boolean escolhaValida = false;

        while (!escolhaValida) {
            System.out.print("\nQual caminho você escolhe? [1] (Sombrio) ou [2] (Barulhento): ");
            escolhaCaminho = scanner.nextLine();

            switch (escolhaCaminho) {
                case "1":
                    caminhoSombrio();
                    escolhaValida = true;
                    break;
                case "2":
                    caminhoBarulhento();
                    escolhaValida = true;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, digite 1 ou 2.");
            }
        }
    }

    private void caminhoSombrio() {
        System.out.println("\nVocê escolheu o Caminho Sombrio. A tensão é palpável. O chão parece sugar o som de seus passos.");

        Inimigo Lobo = new Inimigo("Lobo das Sombras", 80, 22, 7, 50);
        encontroInimigo(Lobo);

        if (heroi.estaVivo()) {
            System.out.println("\nCom o Lobo das Sombras derrotado, o silêncio retorna. Você segue adiante.");
            System.out.println("Após mais algum tempo, você avista fumaça no horizonte. Você chegou a um vilarejo isolado, envolto em névoa.");

            interacaoVilarejo();
        }
    }

    private void caminhoBarulhento() {
        System.out.println("\nVocê segue o caminho iluminado, mas o barulho constante o deixa em alerta.");

        System.out.println("Você encontra um brilho na água do riacho. É uma poção abandonada!");
        encontrarItem("POÇÃO PELE DE PEDRA", "Dá uma camada protetora temporária.", "Defesa", 10, 1, 2);

        System.out.println("\nCom o crepúsculo, você decide montar acampamento rapidamente. Mal coloca sua mochila no chão...");
        System.out.println("Um ruído alto e uma criatura salta das sombras! Você é pego de surpresa!");

        Inimigo PeixeMaluco = new Inimigo("Goblin Roxo", 100, 10, 5, 50);
        encontroInimigo(PeixeMaluco);

        if (heroi.estaVivo()) {
            System.out.println("\nCom o Goblin derrotado, você finalmente consegue acender uma fogueira. O descanso é breve, mas necessário.");
            System.out.println("Ao amanhecer, você segue a trilha e avista fumaça no horizonte, indicando um vilarejo.");

            interacaoVilarejo();
        }
    }

    private void interacaoVilarejo() {
        System.out.println("\n*** VILAREJO DA NÉVOA ***");
        System.out.println("Os poucos habitantes te olham com desconfiança. Você para na porta de uma taverna velha e enfumaçada.");

        System.out.println("Você está na Taverna. O que você faz?");
        System.out.println(" [1] Pergunta sobre o Elmo (Ação Rápida).");
        System.out.println(" [2] Pede uma cerveja e tenta ouvir as conversas (Ação Cautelosa).");

        String escolhaAcao = "";
        boolean escolhaValida = false;

        while(!escolhaValida) {
            System.out.print("\nSua escolha (1 ou 2): ");
            escolhaAcao = scanner.nextLine();

            switch (escolhaAcao) {
                case "1":
                    System.out.println("\nO estalajadeiro empalidece e grita: 'Não sei de nada! Saia daqui!', chamando atenção indesejada.");
                    System.out.println("Dois brutamontes saem das sombras e te cercam!");

                    Inimigo brutamontes = new Inimigo("Brutamontes do Vilarejo", 60, 30, 12, 170);

                    Item espadada = new Item("ESCUDO DE AÇO VALERIANO", "Protege contra mosntros das sombras", "Defesa", 50, 1, 3);
                    brutamontes.getInventario().adicionarItem(espadada);

                    encontroInimigo(brutamontes);

                    if (heroi.estaVivo()) {
                        System.out.println("\nOs Brutamontes estão caídos. O estalajadeiro fugiu. Você não tem mais pistas aqui. Após uma longa viagem você chega a um local suspeito...");
                        irParaRuinaNorte();
                    }
                    escolhaValida = true;
                    break;
                case "2":
                    System.out.println("\nVocê senta, finge beber. Um bêbado murmura sobre 'um grande tesouro' levado 'para a ruína ao norte'.");
                    System.out.println("Você ganha uma Pista Crucial!");
                    irParaRuinaNorte();
                    escolhaValida = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite 1 ou 2.");
            }
        }
    }

    // ----------------------------------------------------------------------
    //                   CAPÍTULO II: A RUÍNA AO NORTE
    // ----------------------------------------------------------------------

    private void irParaRuinaNorte() {
        criarCheckpoint();

        System.out.println("\n*** CAPÍTULO II: A RUÍNA AO NORTE ***");
        System.out.println(" A Ruína ao Norte é uma antiga fortaleza élfica, marcada por uma chama roxa...");

        desafioRuina();
    }

    private void desafioRuina() {
        System.out.println("\nVocê entra na fortaleza. No centro, sobre um pedestal de obsidiana, está um pergaminho ancestral.");
        System.out.println("No entanto, um Golem de Pedra, ativado pela sua presença, se coloca entre você e o pergaminho.");

        System.out.println("\nO Golem não está atacando, apenas bloqueando. O que você faz?");
        System.out.println(" [1] Atacar o Golem! (Combate direto)");
        System.out.println(" [2] Tentar Decifrar a Inscrição no pergaminho (Ação arriscada)");

        String escolhaAcao = "";
        boolean escolhaValida = false;

        while (!escolhaValida) {
            System.out.print("\nSua escolha (1 ou 2): ");
            escolhaAcao = scanner.nextLine();

            switch (escolhaAcao) {
                case "1":
                    combateGolem();
                    escolhaValida = true;
                    break;
                case "2":
                    decifrarInscricao();
                    escolhaValida = true;
                    break;
                default:
                    System.out.println(" Opção inválida. Digite 1 ou 2.");
            }
        }
    }

    private void combateGolem() {
        System.out.println("\nVocê saca sua arma e ataca o Golem de Pedra! O chão treme com a reação dele.");

        Inimigo golem = new Inimigo("Guardião da Ruína (Golem)", 10, 40, 20, 250);

        encontroInimigo(golem);

        if (heroi.estaVivo()) {
            System.out.println("\nO Guardião se desfaz em pó. Você pega o pergaminho.");
            System.out.println("Ele revela a localização final da Feiticeira...");
            iniciarUltimoCapitulo();
        }
        // Se não estiver vivo, o loopCombate já chamou processarDerrota e reiniciou.
    }

    private void decifrarInscricao() {
        System.out.println("\nVocê ignora o Golem e se concentra nas inscrições arcanas. O Golem reage lentamente...");

        int rolagem = Dado.rolarDado();

        if (rolagem >= 15) {
            System.out.println("\n SUCESSO! (Dado: " + rolagem + ") Você decifra o pergaminho antes do Golem se mover!");
            System.out.println("O Golem desliga e a inscrição revela a localização: 'O Elmo está na Cidadela Congelada, no topo da Montanha do Desespero.'");

            iniciarUltimoCapitulo();
        } else {
            System.out.println("\n FALHA! (Dado: " + rolagem + ") Você não consegue decifrar a escrita a tempo! O Golem ataca de repente!");

            combateGolem();

            // Se o herói vencer o combate forçado, ele lê o pergaminho e o fluxo continua via combateGolem().
        }
    }

    // ----------------------------------------------------------------------
    //                   CAPÍTULO III: A CIDADELA CONGELADA (FINAL)
    // ----------------------------------------------------------------------

    private void iniciarUltimoCapitulo() {
        criarCheckpoint(); // Checkpoint final antes do chefe

        System.out.println("\n*** CAPÍTULO FINAL: A CIDADELA CONGELADA ***");
        System.out.println("A pista o leva à Montanha do Desespero. O frio é insuportável e a neve dificulta cada passo.");

        System.out.println("No topo, você encontra a Cidadela. O Elmo está à vista, guardado pela... FEITICEIRA DA NÉVOA!");

        // Combate Final
        encontroChefeFinal();
    }

    private void encontroChefeFinal() {
        // Criamos a Feiticeira (CHEFÃO FINAL)
        Inimigo feiticeira = new Inimigo("Feiticeira da Névoa", 250, 50, 30, 1000);

        // Loot de Chefe (O Amuleto pode ser a chave da vitória)
        Item amuleto = new Item("AMULETO ARCANO", "Aumenta permanentemente a defesa.", "Defesa", 10, 1, 3);
        feiticeira.getInventario().adicionarItem(amuleto);

        System.out.println("\n==================================================");
        System.out.println("!!! CHEFE FINAL: FEITICEIRA DA NÉVOA !!!");
        System.out.println("==================================================");

        // --- DIÁLOGO INTENSO ---
        System.out.println("\nO ar gélido estala com eletricidade. A Feiticeira da Névoa vira-se lentamente, os olhos brilhando.");

        System.out.println("\u001b[35mFEITICEIRA DA NÉVOA:\u001b[0m Interessante... Eu esperava as tropas reais, mas vejo " + heroi.getNome() + ", o " + heroi.getClass().getSimpleName().toUpperCase() + ".");
        System.out.println("\u001b[35mFEITICEIRA DA NÉVOA:\u001b[0m Eu ouvi sobre sua força e sua... moral questionável. É uma pena que ela não será suficiente.");

        System.out.print("\n" + heroi.getNome() + " (Você): ");
        scanner.nextLine();

        System.out.println("\u001b[35mFEITICEIRA DA NÉVOA:\u001b[0m Patético. O Elmo é meu. Morra!");

        // Fim do Diálogo, Início do Combate
        encontroInimigo(feiticeira);
    }

    //Apos derrotar o boss final vcc decide se resolve devolver o elmo para o reino ou se decide fugir com o elmo, casqo decida devolver vc sera aclamado e
    //todos irao um dia te dveer um favor, caso resolva fugir as tropas do reino irao atras sde voce e voce ainda tera uma ultima batalha em que provavelmente ira morrer
    //e assim acabara a historia

}