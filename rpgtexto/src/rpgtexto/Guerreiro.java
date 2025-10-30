package rpgtexto;

import java.util.Scanner;

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
    public void tomarAcao (Personagem alvo){
        Scanner scanner = Personagem.scanner;
        int opt = 0;
        boolean escolhaValida = false;

        System.out.println("O que fazer contra o(a) " + alvo.getNome());
        System.out.println("1- Ataque Basico\n2- Poder Especial\n3- Usar Item Do Inventario\n");

        while (!escolhaValida) {
            System.out.println("Escolha: ");

            if (scanner.hasNextInt()) {
                opt = scanner.nextInt();

                switch (opt) {
                    case 1:

                        this.atacar(alvo);
                        escolhaValida = true;
                        break;
                    case 2:

                        boolean usouEspecial = this.tentarUsarEspecial(alvo);
                        if (usouEspecial){
                            escolhaValida = true;
                        } else {
                            System.out.println("Escolha outra ação.");
                        }
                        break;
                    case 3:


                        escolhaValida = true;
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }

            }

        }
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

