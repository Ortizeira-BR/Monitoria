package dice;

/**
 *
 * @author Guilherme Ortiz
 */

public class User {
    private int pk_usuario;
    private String usuario;
    private String nome;
    private String senha;
    private boolean is_admin;
    private boolean is_monitor;

    public int getPk_usuario() {
        return pk_usuario;
    }

    public void setPk_usuario(int pk_usuario) {
        this.pk_usuario = pk_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_monitor() {
        return is_monitor;
    }

    public void setIs_monitor(boolean is_monitor) {
        this.is_monitor = is_monitor;
    }

    
}