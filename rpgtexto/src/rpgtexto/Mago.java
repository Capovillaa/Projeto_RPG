package rpgtexto;

import java.util.Scanner;

public class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, 80, 30, 5, 0);
    }

    // Construtor de Cópia
    public Mago(Mago outro) {
        super(outro);
    }

    private boolean usarItem (Personagem alvo) {
        Inventario inventario = this.getInventario();
        inventario.mostrarInventarioParaUso();

        Scanner scanner = Personagem.scanner;

        int escolha = -2;

        System.out.print("Escolha um item para usar: ");

        if (scanner.hasNextInt()){
            escolha = scanner.nextInt();
        } else {
            System.out.println("Entrada invalida");
            scanner.next();
            return false;
        }

        if (escolha == -1) {
            System.out.println("Fechando o inventario...");
            return false;
        }

        Item itemEscolhido = inventario.getItemPorIndice(escolha);

        if (itemEscolhido == null) {
            System.out.println("Opção invalida");
            return false;
        }

        return inventario.usarItem(itemEscolhido.getNome(), this);
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
        int danoFixo =  (int) (danoBase * 2.5);

        alvo.receberDano(danoFixo);

        System.out.println(" IMPACTO MÁXIMO! O feitiço atingiu " + alvo.getNome());
        System.out.println("---------------------------------");
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

                        boolean usouItem = this.usarItem(this);
                        if(usouItem) {
                            escolhaValida = true;
                        }
                        break;
                    default:
                        System.out.println("Digite uma opcao valida");
                        break;
                }

            }

        }
    }
}

