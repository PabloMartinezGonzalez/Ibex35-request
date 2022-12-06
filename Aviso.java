
import java.rmi.RemoteException;
import java.io.Serializable;
import java.util.Objects;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pacor
 */
public class Aviso implements Serializable{
    private String empresa;
    private Double valor;
    private String tipo;
    private CallbackClientInterface cliente;

    public Aviso(String empresa, Double valor, String tipo) throws RemoteException {
        this.empresa = empresa;
        this.valor = valor;
        this.tipo = tipo;
        this.cliente = new CallbackClientImpl();
    }

    public void setCliente(CallbackClientInterface cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public CallbackClientInterface getCliente() {
        return cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aviso other = (Aviso) obj;
        if (!Objects.equals(this.empresa, other.empresa)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }
    
    
}
