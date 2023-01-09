/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.*;
import java.net.*;
/**
 *
 * @author mjrs1
 */
public class Cliente {
    static final String host = "localhost";//"127.0.1";
    static final int puerto = 5000;
    
    public Cliente(){
        try{
            Socket skCliente = new Socket(host, puerto);
            DataInputStream flujoEntrada = new DataInputStream(skCliente.getInputStream());
            System.out.println(flujoEntrada.readUTF());
            skCliente.close();
        }catch(Exception e){
        }
    }
    
    public static void main(String[] args){
        new Cliente();
    }
}
