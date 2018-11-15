package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread{
    Socket cliente;
    DataInputStream flujoDatosEntrada;


    public HiloServidor(Socket cliente) {
        this.cliente = cliente;
        try {
            this.flujoDatosEntrada = new DataInputStream(cliente.getInputStream());
        }catch (IOException e){
            System.out.println("Error hilo seridor");
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensaje = flujoDatosEntrada.readUTF();
                System.out.println("C-" + cliente.getPort() +": "+ mensaje);
            }
        }catch (IOException e){
            System.out.println("Se perdio conexion con C-"+cliente.getPort());
        }
    }


}
