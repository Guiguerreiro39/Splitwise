/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Java.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Houdini
 */
public class DAO_listaEx implements Map<Integer, Despesa>{

    Connection conn;
    
    @Override
    public int size() {
        int size = -1;
        try{
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM despesa WHERE tipo = 0");
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connector.close(conn);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size()== 0;
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException{
        boolean r = false;
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("SELECT id_despesa FROM despesa WHERE id_despesa= ? and tipo = 0");
            stm.setInt(1, (Integer) key);
            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        } finally {
            Connector.close(conn);
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean r = false;
        
        if(value.getClass().getName().equals("dss.Java.Despesa")){
            Despesa d = (Despesa)value;
            int id = d.getId_despesa();
            Despesa dp = this.get(id);
            if(dp.getId_despesa() == id && !dp.isTipo()){
                r=true;
            }
        }
        return r;
    }

    @Override
    public Despesa get(Object key) {
        Despesa d = null;
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM despesa WHERE id_despesa=? and tipo = 0");
            stm.setInt(1, (Integer)key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                d = new Despesa(rs.getFloat("custo"),rs.getDate("data_limite"), rs.getBoolean("tipo"), rs.getString("categoria"), rs.getBoolean("pago"), rs.getInt("referencia"), rs.getInt("id_despesa"));
            }
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        } finally {
            try{
                Connector.close(conn);
            }catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return d;
    }

    @Override
    public Despesa remove(Object key) {
        Despesa d = this.get(key);
        try{
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM despesa WHERE id_despesa = ? and tipo = 0");
            stm.setInt(1,(Integer) key);
            stm.executeUpdate();
        } catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connector.close(conn);
            } catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return d;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Despesa> dp) {
        for(Despesa d: dp.values()){
            if(!d.isTipo())
                put(d.getId_despesa(), d);
        }
    }

    @Override
    public void clear() {
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM despesa WHERE tipo = 0");
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        } finally {
            try{
                Connector.close(conn);
            }catch(Exception e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> s = null;
        
        try{
            conn = Connector.connectDB();
            s = new HashSet<>();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM despesa WHERE tipo = 0");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s.add(rs.getInt("id_despesa"));
            }
        }catch(SQLException e){
            System.out.printf(e.getMessage());
        }finally{
                try{
                    Connector.close(conn);
                }catch(Exception e){
                    System.out.printf(e.getMessage());
                }
        }
        return s;
    }

    @Override
    public Collection<Despesa> values() {
        Set<Despesa> despesas = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());
        for(int s : keys){
            despesas.add(this.get(s));
        }
        return despesas;
    }

    @Override
    public Set<Map.Entry<Integer, Despesa>> entrySet() {
        Set<Integer> keys = new HashSet<>(this.keySet());
        HashMap<Integer,Despesa> map = new HashMap<>();
        for(int key : keys){
            map.put(key,this.get(key));
        }
        return map.entrySet();
    }

    @Override
    public Despesa put(Integer key, Despesa value) {
        Despesa d;
        
        if(this.containsKey(key)){
            d = this.get(key);
        }
        else d = value;
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM despesa WHERE id_despesa = ? and tipo = 0");
            stm.setInt(1, key);
            stm.executeUpdate();
            if(!value.isTipo()){
                stm = conn.prepareStatement("INSERT INTO despesa (referencia, categoria, tipo, custo, data_limite, pago, id_despesa) VALUES (?,?,0,?,?, ?, ?)");
                stm.setInt(1, value.getReferencia());
                stm.setString(2, value.getCategoria());
                stm.setFloat(3, value.getValor());
                stm.setDate(4, new java.sql.Date(value.getLimite().getTime()));
                stm.setBoolean(5, value.isPago());
                stm.setInt(6, value.getId_despesa());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        } finally {
            try{
                Connector.close(conn);
            }catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return d;
    }
    
}
