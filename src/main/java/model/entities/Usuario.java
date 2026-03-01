package model.entities;

public class Usuario {
    private Integer id;
    
    private String usuario;
    
    private String senha;
    
    private String cnpj;
    
    private String perfil;

    public Usuario() {
    }

    public Usuario(Integer id, String usuario, String senha, String cnpj, String perfil) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.cnpj = cnpj;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
}
