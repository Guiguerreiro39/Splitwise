/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Houdini
 */
public class Morador {
    private String username;
    private String email;
    private Date entrada;
    private Date saida;
    private float conta;
    private Map<Integer, Despesa> listaRec;
    private Map<Integer,Despesa> listaExtra;
    
    public Morador() {
        this.username = "";
        this.email = "";
        this.entrada = new Date();
        this.saida = new Date();
        this.conta = (float) 0.0;
        this.listaRec = new HashMap<>();
        this.listaExtra = new HashMap<>();
    }
    
    public Morador(String username, String email, Date entrada, Date saida, float conta) {

        this.username = username;
        this.email = email;
        this.entrada = entrada;
        this.saida = saida;
        this.conta = conta;
        this.listaRec = null;
        this.listaExtra = null;
    }
    
    public Morador(Morador m) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.entrada = m.getEntrada();
        this.saida = m.getSaida();
        this.conta = m.getConta();
        this.listaRec = m.getListaRec();
        this.listaExtra = m.getListaExtra();
    }
    
    public String getUsername() {return this.username;}
    public String getEmail() {return this.email;}
    public Date getEntrada(){return this.entrada;}
    public Date getSaida(){return this.saida;}
    public float getConta(){return this.conta;}
    public Map<Integer, Despesa> getListaRec() {return listaRec;}
    public Map<Integer,Despesa> getListaExtra() {return listaExtra;}

    @Override
    public String toString() {
        return "Morador{" + " username=" + username + ", email=" + email + ", entrada=" + entrada + ", saida=" + saida + ", conta=" + conta + ", listaRec=" + listaRec + '}';
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public void setConta(float conta) {
        this.conta = conta;
    }

    public void setListaRec(Map<Integer, Despesa> listaDespesas) {
        this.listaRec = listaDespesas;
    }

    public void setListaExtra(Map<Integer, Despesa> listaDespesas) {
        this.listaExtra = listaDespesas;
    }

    public void adicionaDespesa (Despesa a) {
      if (a.isTipo() == true) listaRec.put(a.getReferencia(), a);
      else listaExtra.put(a.getReferencia(), a);
      this.conta += a.getValor();
      }
    
    public void removeDespesa (Despesa a) {
      if (a.isTipo() == true) listaRec.remove(a.getReferencia(), a);
      else listaExtra.remove(a.getReferencia(), a);
      this.conta -= a.getValor();
      }  
      
    public void efetuapagamento(int referencia, boolean tipo, float quantia) throws QuantiaInvalidaException{
    if (tipo == true) {
        if (quantia != listaRec.get(referencia).getValor()) throw new QuantiaInvalidaException("Quantia inválida!");
        listaRec.remove(referencia, listaRec.get(referencia));
        listaRec.get(referencia).setPago(true);
    }
    else {
        if (quantia != listaExtra.get(referencia).getValor()) throw new QuantiaInvalidaException("Quantia inválida!");
        listaExtra.remove(referencia, listaRec.get(referencia));
        listaExtra.get(referencia).setPago(true);
    }
    this.conta -=quantia;
    }
}
