/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiBills;

import Frame.Escolhe_Morador;
import Java.Apartamento;
import Java.Despesa;
import Java.Morador;
import java.sql.SQLException;

/**
 *
 * @author Houdini
 */
public class Main {
    
    public static void main(String args[]) throws SQLException {
        Apartamento a = new Apartamento();
        Escolhe_Morador f = new Escolhe_Morador(a);
        f.setVisible(true);
    }
}
