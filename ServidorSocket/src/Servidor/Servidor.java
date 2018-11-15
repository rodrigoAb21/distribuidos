package Servidor;

import java.io.IOException;
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
                System.out.println("Escuchando...");
                agregarCliente(serverSocket.accept());
            }
        }catch (IOException e){
            System.out.println("Sucedio un error mientras se escuchaba...");
        }
    }

    private void agregarCliente(Socket socket){
        System.out.println("Nuevo cliente: C-" + socket.getPort());
        HiloServidor cliente =
                new HiloServidor(socket);
        clientes.put(socket.getPort(), cliente);
        cliente.start();
    }



    public static void main(String[] args){
        Servidor servidor = new Servidor(1500);
        servidor.escuchar();
    }

}
