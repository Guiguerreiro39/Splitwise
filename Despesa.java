/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Houdini
 */
class Despesa {
    private float valor;
    private GregorianCalendar limite;
    private boolean tipo;
    private String categoria;
    private boolean pago;
    private int referencia;

    public Despesa(float valor, GregorianCalendar limite, boolean tipo, String categoria, boolean pago, int referencia) {
        this.valor = valor;
        this.limite = limite;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pago = pago;
        this.referencia = referencia;
    }
    
    public Despesa(){
        this.valor = (float) 0.0;
        this.limite = new GregorianCalendar();
        this.tipo = false;
        this.categoria = "";
        this.pago = false;
        this.referencia = 0;
    }
    public Despesa(Despesa d){
        this.valor = d.getValor();
        this.limite = d.getLimite();
        this.tipo = d.isTipo();
        this.categoria = d.getCategoria();
        this.pago = d.isPago();
        this.referencia = d.getReferencia();
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public GregorianCalendar getLimite() {
        return limite;
    }

    public void setLimite(GregorianCalendar limite) {
        this.limite = limite;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public boolean isPago() {
        return pago;
    }
    
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    public int getReferencia() {
        return referencia;
    }
    
    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
            
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Despesa other = (Despesa) obj;
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.pago != other.pago) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.limite, other.limite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Despesa{" + "valor=" + valor + ", limite=" + limite + ", tipo=" + tipo + ", categoria=" + categoria + ", pago=" + pago + '}';
    }


    
    
}
