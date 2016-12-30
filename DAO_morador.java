/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Java.Morador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Houdini
 */
public class DAO_morador implements Map<String, Morador>{

    Connection conn;
    
    @Override
    public int size() {
        int size = -1;
        try{
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM Morador");
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
            PreparedStatement stm = conn.prepareStatement("SELECT username FROM morador WHERE username= ?");
            stm.setString(1, (String) key);
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
        
        if(value.getClass().getName().equals("dss.Java.Morador")){
            Morador mr = (Morador)value;
            String username = mr.getUsername();
            Morador m = this.get(username);
            if(m.equals(mr)){
                r=true;
            }
        }
        return r;
    }

    @Override
    public Morador get(Object key) {
        Morador mr = null;
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM morador WHERE username=?");
            stm.setString(1, (String)key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                mr = new Morador(rs.getString("username"),rs.getString("email"),rs.getDate("Entrada"), rs.getDate("Saida"), rs.getFloat("conta"));
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
        return mr;
    }

    @Override
    public Morador put(String key, Morador value) {
        Morador m;
        
        if(this.containsKey(key)){
            m = this.get(key);
        }
        else m = value;
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM morador WHERE username = ?");
            stm.setString(1,key);
            stm.executeUpdate();
            
            stm = conn.prepareStatement("INSERT INTO morador (username, email, Entrada, Saida, Conta) VALUES (?,?,?,?,?)");
            stm.setString(1, key);
            stm.setString(2, value.getEmail());
            stm.setDate(3, new java.sql.Date(value.getEntrada().getTime()));
            stm.setDate(4, new java.sql.Date(value.getSaida().getTime()));
            stm.setFloat(5, value.getConta());
            stm.executeUpdate();
            
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        } finally {
            try{
                Connector.close(conn);
            }catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return m;
    }

    @Override
    public Morador remove(Object key) {
        Morador m = this.get((String) key);
        try{
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM morador WHERE username = ?");
            stm.setString(1, (String) key);
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
        return m;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Morador> m) {
        for(Morador mr: m.values()){
            put(mr.getUsername(), mr);
        }
    }

    @Override
    public void clear() {
        try {
            conn = Connector.connectDB();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM morador");
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
    public Set<String> keySet() {
        Set<String> s = null;
        
        try{
            conn = Connector.connectDB();
            s = new HashSet<>();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM morador");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                s.add(rs.getString("Username"));
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
    public Collection<Morador> values() {
        Set<Morador> moradores = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        for(String s : keys){
            moradores.add(this.get(s));
        }
        return moradores;
    }

    @Override
    public Set<Entry<String, Morador>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        HashMap<String,Morador> map = new HashMap<>();
        for(String key : keys){
            map.put(key,this.get(key));
        }
        return map.entrySet();
    }
    
    public GregorianCalendar DateChange(Date d) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal;
    }
    
    public Date ChangeCal(Calendar g) {
        Date d = (Date) g.getTime();
        return d;
    }

}
