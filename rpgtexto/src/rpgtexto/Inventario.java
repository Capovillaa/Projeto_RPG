package rpgtexto;

import java.util.Collection;
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
            itemNoInventario.setQuantidade(novaQuantidade);
            System.out.println("Item " + itemNoInventario.getNome() + " atualizado no inventário.\nAgora voce possui: " + novaQuantidade);

        } else{
            this.itens.put(itemParaAdicionar.getNome(), itemParaAdicionar);

            System.out.println("Voce achou um novo item!!! " + itemParaAdicionar.getNome() + ".\nItem adicionado ao inventário.\nQuantidade: " + itemParaAdicionar.getQuantidade());
        }

    }

    public Collection<Item> getTodosOsItens() {
        return this.itens.values();
    }

    public void esvaziar() {
        this.itens.clear();
    }

    @Override
    public String toString ()
    {
        if (this.itens.isEmpty()) {
            return "Inventario vazio.";
        }

        String resultado = "--- INVENTARIO ---\n";


        // Aqui estava com dificuldade na logica, pesquisei e achei esse "for-each" que é um for com ":"
        // ele basicamente declara uma variavel item do tipo Item temporaria e pega todos os valores dos itens
        // armazenados no inventario
        for (Item item : this.itens.values()) {
            resultado = resultado + item.toString() + "\n";
        }

        return resultado;
    }
}
