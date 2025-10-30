package rpgtexto;

import java.util.Scanner;

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

