
package entities;

/**
 *
 * @author gui_o
 */
public class Route {
    String rota;
    String erro;

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }
    
    public String getErro() {
        return erro;
    }

    public void setErro(String rota) {
        this.erro = rota;
    }
    

    @Override
    public String toString() {
        return "Login{" + "rota=" + rota + ", erro=" + erro + "}";
    }
    
}
