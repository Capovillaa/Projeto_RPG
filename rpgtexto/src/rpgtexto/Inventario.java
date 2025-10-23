package rpgtexto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {

    private Map<String, Item> itens;

    public Inventario ()
    {
        this.itens = new HashMap<>();
    }

    public void adicionarItem (Item itemParaAdicionar)
    {
        if (this.itens.containsKey(itemParaAdicionar.getNome())) {

            Item itemNoInventario = this.itens.get(itemParaAdicionar.getNome());

            int quantidadeAtual = itemNoInventario.getQuantidade();
            int quantidadeParaAdicionar = itemParaAdicionar.getQuantidade();

            int novaQuantidade = quantidadeAtual + quantidadeParaAdicionar;

            System.out.println("Item " + itemNoInventario.getNome() + " atualizado no inventário.\nAgora voce possui: " + novaQuantidade);

        }

        this.itens.put(itemParaAdicionar.getNome(), itemParaAdicionar);

        System.out.println("Voce achou um novo item!!!\n" + itemParaAdicionar.getNome() + " adicionado ao inventário.\nQuantidade: " + itemParaAdicionar.getQuantidade());

    }
}
