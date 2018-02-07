/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge Pucha
 */

public class Banco {

    static ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
    static String archivo = "";

    public static void agregar(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public static void grabar() {
        PrintWriter pw = null;
        try {
            // Examen002: La ruta y el nombre del 'archivo' debe ser 
            // establecido dinamicamente por el usuario en el lugar adecuado
            FileWriter fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Cuenta cuenta : listaCuenta) {
            String linea = "";
            if (cuenta instanceof CuentaAhorro) {
                linea = "Cuenta Ahorro" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaHipoteca) {
                linea = "Cuenta Hipoteca" + ";" + cuenta.toString();
            }
            if (cuenta instanceof CuentaPrestamo) {
                linea = "Cuenta Prestamo" + ";" + cuenta.toString();
            }
            pw.println(linea);
        }
        pw.close();
    }

    public static Cuenta buscarCuentaAhorro() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaAhorro cuentaAH = new CuentaAhorro(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static Cuenta buscarCuentaHipoteca() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaHipoteca cuentaAH = new CuentaHipoteca(nombre);
        Cuenta c = (Cuenta) cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public static Cuenta buscarCuentaPrestamo(){
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaPrestamo cp = new CuentaPrestamo(nombre);
        Cuenta c = (Cuenta) cp;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }
    public static void leerCuentas() throws IOException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(archivo)));
            String aux;
            while ((aux = bf.readLine()) != null) {                
                String []linea = aux.split(";");
                if(linea[0].equalsIgnoreCase("Cuenta Ahorro"))
                    crearCuentaAhorro(linea);
                if(linea[0].equalsIgnoreCase("Cuenta Hipoteca"))
                    crearCuentaHipoteca(linea);
                if(linea[0].equalsIgnoreCase("Cuenta Prestamo"))
                    crearCuentaPrestamo(linea);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void crearCuentaAhorro(String[] linea) {
        String temp = linea[1];
        String aux[] = temp.split(",");
        String datos[] = aux[0].split("=");
        String cliente = datos[1];
        datos = aux[1].split("=");
        String tipoCliente = datos[1];
        datos = aux[2].split("=");
        double balance = Double.parseDouble(aux[1]);
        datos = aux[3].split("=");
        double tasaInteres = Double.parseDouble(aux[1]);
        CuentaAhorro ca = new CuentaAhorro(cliente, tipoCliente, balance, tasaInteres);
        agregar(ca);
    }

    private static void crearCuentaHipoteca(String[] linea) {
        String temp = linea[1];
        String aux[] = temp.split(",");
        String datos[] = aux[0].split("=");
        String cliente = datos[1];
        datos = aux[1].split("=");
        String tipoCliente = datos[1];
        datos = aux[2].split("=");
        double balance = Double.parseDouble(aux[1]);
        datos = aux[3].split("=");
        double tasaInteres = Double.parseDouble(aux[1]);
        CuentaHipoteca ch = new CuentaHipoteca(cliente, tipoCliente, balance, tasaInteres);
        agregar(ch);
    }

    private static void crearCuentaPrestamo(String[] linea) {
        String temp = linea[1];
        String aux[] = temp.split(",");
        String datos[] = aux[0].split("=");
        String cliente = datos[1];
        datos = aux[1].split("=");
        String tipoCliente = datos[1];
        datos = aux[2].split("=");
        double balance = Double.parseDouble(aux[1]);
        datos = aux[3].split("=");
        double tasaInteres = Double.parseDouble(aux[1]);
        CuentaPrestamo cp = new CuentaPrestamo(cliente, tipoCliente, balance, tasaInteres);
        agregar(cp);
    }
    
}
