package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Servidor {
    private ServerSocket serverSocket;
    private Hashtable clientes = new Hashtable();

    public Servidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor socket iniciado en el puerto: " +
                    puerto);
        }catch (IOException e){
            System.out.println("No se pudo iniciar el Servidor socket en el" +
                    "puerto: " + puerto);
        }
    }

    public void escuchar(){
        try {
            while(true){
                Socket nuevoCliente = serverSocket.accept();

                DataInputStream flujoDatosEntrada =
                        new DataInputStream(nuevoCliente.getInputStream());

                OutputStream escribir = nuevoCliente.getOutputStream();

                DataOutputStream flujoDatosSalida =
                        new DataOutputStream(escribir);

                HiloServidor hiloServidor =
                        new HiloServidor(nuevoCliente, flujoDatosEntrada,
                                flujoDatosSalida);
                hiloServidor.start();
            }
        }catch (IOException e){
            System.out.println("Sucedio un error mientras se escuchaba...");
        }
    }

    private void agregarCliente(Socket socket){

    }

    public static void main(String[] args){
        Servidor servidor = new Servidor(1500);
        servidor.escuchar();
    }

}
