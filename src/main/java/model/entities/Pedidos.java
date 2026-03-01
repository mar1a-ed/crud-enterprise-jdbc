package model.entities;

import java.sql.Date;
import java.util.List;

public class Pedidos {
    private Integer id;
    
    private Clientes cliente;
    
    private Date dataPedido;
    
    private Double precoTotal;
    
    private List<ItemPedido> itensPedido;

    public Pedidos() {
    }

    public Pedidos(Integer id, Clientes cliente, Date dataPedido, Double precoTotal) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.precoTotal = precoTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }
   
}
