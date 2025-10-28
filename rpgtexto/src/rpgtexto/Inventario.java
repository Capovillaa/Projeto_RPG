package rpgtexto;

import java.util.*;

public class Inventario implements Cloneable {

    private List<Item> itens;

    public Inventario ()
    {
        this.itens = new ArrayList<>();
    }

    public Inventario(Inventario outro) {
        this.itens = new ArrayList<>();

        for (Item itemOriginal : outro.itens) {

            Item itemCopia = itemOriginal.clone();

            this.itens.add(itemCopia);
        }
    }


    public void adicionarItem (Item itemParaAdicionar) {
        Item itemExistente = null;

        for (Item itemNoInventario: this.itens) {
            if(itemNoInventario.equals(itemParaAdicionar)){
                itemExistente = itemNoInventario;
                break;
            }
        }

        if (itemExistente != null) {
            int quantidadeAtual = itemExistente.getQuantidade();
            int quantidadeParaAdicionar = itemParaAdicionar.getQuantidade();
            int novaQuantidade = quantidadeAtual + quantidadeParaAdicionar;

            itemExistente.setQuantidade(novaQuantidade);

            System.out.println("Item " + itemExistente.getNome() + " atualizado no inventário.\nAgora voce possui: " + novaQuantidade);
        } else {
            this.itens.add(itemParaAdicionar);
            System.out.println("Voce achou um novo item!!! " + itemParaAdicionar.getNome() + ".\nItem adicionado ao inventário.\nQuantidade: " + itemParaAdicionar.getQuantidade());
        }

    }

    private Item buscarItemPeloNome (String nomeItem) {
        for (Item item : this.itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item;
            }
        }
        return null;
    }

    public void removerItem (String nomeItem, int quantidadeParaRemover) {

        Item itemNoInventario = buscarItemPeloNome(nomeItem);

        if (itemNoInventario == null) {
            System.out.println("Item " + nomeItem + " não encontrado no inventário.");
            return;
        }

        int quantidadeAtual = itemNoInventario.getQuantidade();

        if (quantidadeAtual < quantidadeParaRemover) {
            System.out.println("Quantidade insuficiente de " + nomeItem + " para remover.");
            return;
        }

        int novaQuantidade = quantidadeAtual - quantidadeParaRemover;
        itemNoInventario.setQuantidade(novaQuantidade);

        if (novaQuantidade <= 0) {
            this.itens.remove(itemNoInventario);
        }
    }

    public Item selecionarItem (String nomeDoItem) {

        Item itemEncontrado = buscarItemPeloNome(nomeDoItem);

        if (itemEncontrado == null) {
            System.out.println("Item " + nomeDoItem + " nao encontrado no inventario");
        }
        return itemEncontrado;
    }

    public void usarItem (String nomeDoItem,Personagem usuario) {
        Item itemEncontrado = buscarItemPeloNome(nomeDoItem);

        if (itemEncontrado == null) {
            System.out.println("Item " + nomeDoItem + " nao encontrado no inventario");
            return;
        }

        itemEncontrado.aplicarEfeito(usuario);

        int novaQuantidade = itemEncontrado.getQuantidade() - 1;
        itemEncontrado.setQuantidade(novaQuantidade);

        System.out.println(itemEncontrado.getNome() + " foi utilizado.");

        if (novaQuantidade <= 0) {
            this.itens.remove(itemEncontrado);
            System.out.println(itemEncontrado.getNome() + " acabou!!! encontre mais para utilizar");
        } else {
            System.out.printf("Quantidade restante no inventario: " + novaQuantidade + "x");
        }

    }

    public Collection<Item> getTodosOsItens() {
        return this.itens;
    }

    public void esvaziar() {
        this.itens.clear();
    }

    @Override
    public String toString() {
        if (this.itens.isEmpty()) {
            return "Inventario vazio.";
        }

        String resultado = "--- INVENTARIO ORDENADO ---\n";

        List<Item> itensOrdenados = new ArrayList<>(this.itens);//fazemos uma cópia para nao alterar a original

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
        cloneInventario.itens = new ArrayList<>();

        // 3. Itera para clonar CADA Item do inventário original
        for (Item itemOriginal : this.itens) {
            Item itemCopia = itemOriginal.clone(); // Chama o clone() do Item
            cloneInventario.itens.add(itemCopia);
        }

        return cloneInventario;
    }

}
