/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProductorConsumidor;

/**
 *
 * @author mjrs1
 */
public class Consumidor extends Thread
{
    Productor _produce;
    
    
    public Consumidor(Productor produce)
    {
        _produce = produce;
    }
    
    
    public void run()
    {
        try
        {
//            System.out.println("CONSUMIDOR-> Antes de entrar al bucle");
            while(true)
            {
//                System.out.println("CONSUMIDOR->  Llama funcion produce.getMensage");
                String mnsj = _produce.getMessage();

                System.out.println("Recibi el mensaje: " + mnsj);
                Thread.sleep(2000);
            }
        }
        catch(Exception e ){}
    }
    
    public static void main(String[] args){
        Productor p = new Productor();
        p.start();
        
        Consumidor c = new Consumidor(p);
        c.start();
    }
 
}
