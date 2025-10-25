package rpgtexto;

import java.util.*;

public class Inventario implements Cloneable {

    private Map<String, Item> itens;

    public Inventario ()
    {
        this.itens = new HashMap<>();
    }
    public Inventario(Inventario outro) {
        this.itens = new HashMap<>();

        for (Item itemOriginal : outro.itens.values()) {

            Item itemCopia = itemOriginal.clone();

            this.itens.put(itemCopia.getNome(), itemCopia);
        }
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

    public boolean removerItem(String nomeItem, int quantidadeParaRemover) {
        if (!this.itens.containsKey(nomeItem)) {
            System.out.println("Item " + nomeItem + " não encontrado no inventário.");
            return false;
        }

        Item itemNoInventario = this.itens.get(nomeItem);
        int quantidadeAtual = itemNoInventario.getQuantidade();

        if (quantidadeAtual < quantidadeParaRemover) {
            System.out.println("Quantidade insuficiente de " + nomeItem + ".");
            return false;
        }

        int novaQuantidade = quantidadeAtual - quantidadeParaRemover;
        itemNoInventario.setQuantidade(novaQuantidade);
        System.out.println("Removido " + quantidadeParaRemover + "x " + nomeItem + ".");

        if (novaQuantidade <= 0) {
            this.itens.remove(nomeItem);
            System.out.println(nomeItem + " esgotado e removido do inventário.");
        }
        return true;
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

        String resultado = "--- INVENTARIO ORDENADO ---\n";

        List<Item> itensOrdenados = new ArrayList<>(this.itens.values());

        Collections.sort(itensOrdenados);

        for (Item item : itensOrdenados) {
            resultado = resultado + item.toString() + "\n";
        }

        return resultado;
    }

    @Override
    public Inventario clone() throws CloneNotSupportedException {
        // 1. Cria uma cópia rasa do objeto Inventario em si (copia a referência do Map)
        Inventario cloneInventario = (Inventario) super.clone();

        // 2. Cria um NOVO Map para a cópia (o clone é shallow por padrão, precisamos do Deep Copy)
        cloneInventario.itens = new HashMap<>();

        // 3. Itera para clonar CADA Item do inventário original
        for (Item itemOriginal : this.itens.values()) {
            Item itemCopia = itemOriginal.clone(); // Chama o clone() do Item
            cloneInventario.itens.put(itemCopia.getNome(), itemCopia);
        }

        return cloneInventario;
    }

}
