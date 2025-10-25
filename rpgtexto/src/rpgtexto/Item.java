package rpgtexto;

import java.util.Objects;

public class Item implements Cloneable , Comparable<Item>{
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;


    public Item (String nome,String descricao,String efeito,int quantidade)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;

    }

    public void setQuantidade (int quantidade)
    {
        this.quantidade = quantidade;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getDescricao ()
    {
        return this.descricao;
    }

    public String getEfeito ()
    {
        return this.efeito;
    }

    public int getQuantidade ()
    {
        return this.quantidade;
    }



    @Override
    public String toString ()
    {
        return "Nome: " + this.nome +
                " | Descricao: " + this.descricao +
                " | Efeito: " + this.efeito +
                " | Quantidade: " + this.quantidade;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        Item item = (Item) obj;

        return  Objects.equals(this.nome,item.nome) &&
                Objects.equals(this.descricao,item.descricao) &&
                Objects.equals(this.efeito,item.efeito);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.nome, this.descricao, this.efeito);
    }

    @Override
    public Item clone() {
        try {
            // A chamada a super.clone() já é suficiente (Shallow Copy)
            // porque todos os atributos são primitivos ou imutáveis (String)
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            // Isso não deve acontecer, pois implementamos Cloneable
            throw new InternalError(e);
        }
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareTo(outro.nome);
    }
}
