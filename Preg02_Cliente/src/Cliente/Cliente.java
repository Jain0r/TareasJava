/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mjrs1
 */
public class Cliente {
    
    DataInputStream flujoEntrada;
    DataOutputStream flujoSalida;
    Socket skCliente;
    HiloTCP listener;
    
    
    JFrame frame;
    JLabel lblMensaje = new JLabel("");
    static boolean Iniciar = false;
    static boolean Receptor = false;
    public Cliente(){
        iniciar();
    }
    
    public void iniciar(){
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnComenzar = new JButton("Iniciar");
        btnComenzar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnComenzar.addActionListener(new IniciarCarrera());
        btnComenzar.setBounds(65, 287, 425, 40);
        frame.getContentPane().add(btnComenzar);
        
        lblMensaje.setBounds(195, 250, 410, 14);
        lblMensaje.setVisible(false);
        frame.getContentPane().add(lblMensaje);
        
        
        try{
            skCliente = new Socket("localhost", 5001);
            flujoEntrada = new DataInputStream(skCliente.getInputStream());
            flujoSalida = new DataOutputStream(skCliente.getOutputStream());
            listener = new HiloTCP();
            listener.start();
        }
        catch(Exception e){
            System.out.println("Error en iniciar" + e.getMessage());
        }
    }
    
    
    class IniciarCarrera implements ActionListener {

        boolean aux = false;
        public void actionPerformed(ActionEvent arg0) {
            try{
                lblMensaje.setVisible(true);
        //Mensaje que aparecerá cuando ya llego un auto a la meta
                lblMensaje.setText("Esperando ganador");
                flujoSalida.writeUTF("go");
                
                
            }
            
            catch(Exception e){ 
                lblMensaje.setText(e.getMessage());
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    class HiloTCP extends Thread{
        public void run(){
            try{
                while(true){
                    String line = flujoEntrada.readUTF();
                    if(line.contains("Gano la carrera el auto"))
                        System.out.println(line);
                            
                    lblMensaje.setVisible(true);
        //Mensaje que aparecerá cuando ya llego un auto a la meta
                    lblMensaje.setText(line);
                }
            }
            catch(Exception x){}
        }
    }
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Cliente window = new Cliente();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
