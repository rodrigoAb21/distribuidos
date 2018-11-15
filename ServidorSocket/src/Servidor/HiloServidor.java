package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread{
    private Socket cliente;
    private DataInputStream flujoDatosEntrada;
    private DataOutputStream flujoDatosSalida;
    private long puerto;


    public HiloServidor(Socket cliente) {
        this.cliente = cliente;
        puerto = cliente.getPort();
        try {
            this.flujoDatosEntrada =
                    new DataInputStream(cliente.getInputStream());
            this.flujoDatosSalida =
                    new DataOutputStream(cliente.getOutputStream());
            flujoDatosSalida.writeUTF("Servidor: Bienvenido, esta conectado" +
                    "en el puerto: " + puerto);
        }catch (IOException e){
            System.out.println("Error hilo servidor");
        }

    }

    public long getPuerto() {
        return puerto;
    }

    public void setPuerto(long puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mensaje = flujoDatosEntrada.readUTF();
                System.out.println("C-" + cliente.getPort() +": "+ mensaje);
            }
        }catch (IOException e){
            System.out.println("Se perdio conexion con C-" + getPuerto());
            close();
        }


    }

    public void close(){
        try {
            if (cliente != null) cliente.close();
            if (flujoDatosEntrada != null) flujoDatosEntrada.close();
            setPuerto(-1);

        }catch (IOException e){
            System.out.println(e);
        }
    }


}
