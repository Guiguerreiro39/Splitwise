/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import DAO.DAO_listaEx;
import DAO.DAO_listaRec;
import java.util.Date;

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
    private DAO_listaRec listaRec;
    private DAO_listaEx listaExtra;
    
    public Morador() {
        this.username = "";
        this.email = "";
        this.entrada = new Date();
        this.saida = new Date();
        this.conta = (float) 0.0;
        this.listaRec = new DAO_listaRec();
        this.listaExtra = new DAO_listaEx();
    }
    
    public Morador(String username, String email, Date entrada, Date saida, float conta) {

        this.username = username;
        this.email = email;
        this.entrada = entrada;
        this.saida = saida;
        this.conta = conta;
        this.listaRec = new DAO_listaRec();
        this.listaExtra = new DAO_listaEx();
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
    public DAO_listaRec getListaRec() {return listaRec;}
    public DAO_listaEx getListaExtra() {return listaExtra;}

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

    public void setListaRec(DAO_listaRec listaDespesas) {
        this.listaRec = listaDespesas;
    }

    public void setListaExtra(DAO_listaEx listaDespesas) {
        this.listaExtra = listaDespesas;
    }

    public void adicionaDespesa (Despesa a) {
      if (a.isTipo()) listaRec.put(a.getId_despesa(), a);
      else listaExtra.put(a.getId_despesa(), a);
      this.conta += a.getValor();
      }
    
    public void removeDespesa (Despesa a) {
      if (a.isTipo()) listaRec.remove(a.getId_despesa(), a);
      else listaExtra.remove(a.getId_despesa(), a);
      this.conta -= a.getValor();
      }  
      
    public void efetuapagamento(int id_despesa, boolean tipo, float quantia) throws QuantiaInvalidaException{
    if (tipo) {
        if (quantia != listaRec.get(id_despesa).getValor()) throw new QuantiaInvalidaException("Quantia inválida!");
        listaRec.remove(id_despesa, listaRec.get(id_despesa));
        listaRec.get(id_despesa).setPago(true);
    }
    else {
        if (quantia != listaExtra.get(id_despesa).getValor()) throw new QuantiaInvalidaException("Quantia inválida!");
        listaExtra.remove(id_despesa, listaRec.get(id_despesa));
        listaExtra.get(id_despesa).setPago(true);
    }
    this.conta -=quantia;
    }
}
