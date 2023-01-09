/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.*;
import java.net.*;
/**
 *
 * @author mjrs1
 */
public class Servidor {
    
    static final int puerto = 5000;    
    
    public Servidor(){
        try{
            ServerSocket skServidor = new ServerSocket(puerto);
            System.out.println("Escucha el puerto :" + puerto);
            for(int i = 1; i<= 3; i++){
                Socket skCliente = skServidor.accept();
                System.out.println("Sirvo al cliente "+i);
                DataOutputStream flujosalida = new DataOutputStream(skCliente.getOutputStream());
                flujosalida.writeUTF("Hola cliente "+i);
                skCliente.close();
            
            }
            
            
        }catch(Exception e){
            
        }
    }
    
    public static void main(String[] args){
        new Servidor();
    }
}
