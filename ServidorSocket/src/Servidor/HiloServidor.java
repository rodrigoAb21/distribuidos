package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread{
    Socket cliente;
    DataInputStream flujoDatosEntrada;
    DataOutputStream flujoDatosSalida;

    public HiloServidor(Socket cliente, DataInputStream flujoDatosEntrada,
                        DataOutputStream flujoDatosSalida) {
        this.cliente = cliente;
        this.flujoDatosEntrada = flujoDatosEntrada;
        this.flujoDatosSalida = flujoDatosSalida;
    }

    @Override
    public void run() {
        System.out.println("Se acepto una nueva conexion.");
        try {
            while (true) {
                String mensaje = flujoDatosEntrada.readUTF();
                System.out.println(mensaje);
            }
        }catch (IOException e){
            System.out.println("Se perdio la conexion!.");
        }
    }
}
