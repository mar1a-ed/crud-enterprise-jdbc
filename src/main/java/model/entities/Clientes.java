package model.entities;

import java.sql.Date;

public class Clientes {
    private Integer id;
    
    private String fname;
    
    private String lname;
    
    private String cpf;
    
    private Date data_nasc;
    
    private String email;
    
    private String rua;
    
    private Integer numero;
    
    private String bairro;

    public Clientes() {
    }

    public Clientes(Integer id, String fname, String lname, String cpf, Date data_nasc, String email, String rua, Integer numero, String bairro) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    
}
