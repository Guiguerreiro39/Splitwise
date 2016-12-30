/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiBills;

import Frame.Escolhe_Morador;
import Java.Apartamento;
import Java.Morador;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
