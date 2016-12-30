/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import DAO.DAO_morador;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Houdini
 */
public class Apartamento {
    private float renda;
    private ArrayList<Despesa> ListaTotal;
    private DAO_morador ListaMoradores;

    public Apartamento(float renda, ArrayList<Despesa> ListaTotal) {
        this.renda = renda;
        this.ListaTotal = ListaTotal;
        this.ListaMoradores = new DAO_morador();
    }
    
    public Apartamento() {
        this.renda = (float) 0.0;
        this.ListaTotal = new ArrayList<>();
        this.ListaMoradores = new DAO_morador();
    }
    
    public Apartamento(Apartamento p) {
        this.renda = p.getRenda();
        this.ListaTotal = p.getListaTotal();
        this.ListaMoradores = p.getListaMoradores();
    }

    public float getRenda() {
        return renda;
    }

    public ArrayList<Despesa> getListaTotal() {
        return ListaTotal;
    }

    public DAO_morador getListaMoradores() {
        return ListaMoradores;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public void setListaTotal(ArrayList<Despesa> ListaTotal) {
        this.ListaTotal = ListaTotal;
    }

    public void setListaMoradores(DAO_morador ListaMoradores) {
        this.ListaMoradores = ListaMoradores;
    }

    @Override
    public String toString() {
        return "Apartamento{" + "renda=" + renda + ", ListaTotal=" + ListaTotal + ", ListaMoradores=" + ListaMoradores + '}';
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
        final Apartamento other = (Apartamento) obj;
        if (Float.floatToIntBits(this.renda) != Float.floatToIntBits(other.renda)) {
            return false;
        }
        if (!Objects.equals(this.ListaTotal, other.ListaTotal)) {
            return false;
        }
        if (!Objects.equals(this.ListaMoradores, other.ListaMoradores)) {
            return false;
        }
        return true;
    }

    public void removerMorador(String username) {
        for(Morador o: ListaMoradores.values()){
            if(o.getUsername().equals(username))
                ListaMoradores.remove(username);
        }
    }
    
    public void adicionarMorador(Morador m) {
        String key = m.getUsername();
        ListaMoradores.put(key, m);
    }
    
    public void removerDespesa(Despesa d) {
        int i = 0;
        for(Despesa o: ListaTotal) {
            if(o.getReferencia() == d.getReferencia()){
                ListaTotal.remove(i);
                for(Morador m: ListaMoradores.values()){
                    m.removeDespesa(d);
                }                
            }
            i++;
        }
    }
    
    public void adicionarDespesa(Despesa d) {
        int i = ListaTotal.size();
        int j = ListaMoradores.size();
        ListaTotal.add(i, d);
        Despesa dp = new Despesa((d.getValor())/j, d.getLimite(), d.isTipo(), d.getCategoria(), d.isPago(), d.getReferencia());
        for(Morador m: ListaMoradores.values()){
            m.adicionaDespesa(dp);
        }
    }
    
}
