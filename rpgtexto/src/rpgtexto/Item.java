package rpgtexto;

import java.util.Objects;

public class Item {
    private String nome;
    private String descricao;
    private String efeito;
    private byte quantidade;


    public Item (String nome,String descricao,String efeito,byte quantidade)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = quantidade;

    }

    public void setQuantidade (byte quantidade)
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

    public byte getQuantidade ()
    {
        return this.quantidade;
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
}
