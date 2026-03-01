package model.entities;

public class Produtos {
    private Integer id;
    
    private String nome;
    
    private Categoria categoria;
    
    private Double precoUni;
    
    private Integer qtdEstoque;

    public Produtos() {
    }

    public Produtos(Integer id, String nome, Categoria categoria, Double precoUni, Integer qtdEstoque) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoUni = precoUni;
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecoUni() {
        return precoUni;
    }

    public void setPrecoUni(Double precoUni) {
        this.precoUni = precoUni;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    
}
