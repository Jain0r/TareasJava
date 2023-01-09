/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProductorConsumidor;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author mjrs1
 */
public class Productor extends Thread 
{
    static final int MAX = 7;
    private Vector mensajes = new Vector();
    
    public void run()
    {
        try {
            while(true){
                putMessage();
                sleep(100);
            }
        } catch (Exception ex) {
            
            
        }
    }
    
    public Productor(){
        System.out.println("Productor creado");
    }
    
    private synchronized void putMessage() throws Exception
    {
//        while(mensajes.size() = MAX){
            while(mensajes.size() <= MAX){
            wait();
            String mensaje = new Date().toString();
            System.out.println("Enviando mensaje: " + mensaje);
            mensajes.addElement(mensaje);
            notify();
        }
    }
    public synchronized String getMessage() throws Exception
    {
        notify();
        while(mensajes.size() == 0)
        {
            wait();
        }
        String mnsj = (String)mensajes.firstElement();
        mensajes.removeElement(mnsj);
        return mnsj;
    }
    
}
