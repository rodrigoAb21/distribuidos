package Servidor;

import Servidor.Eventos.EscuchadorEventos;
import Servidor.Eventos.Evento;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class Servidor implements EscuchadorEventos {
    private ServerSocket serverSocket;
    private Hashtable clientes = new Hashtable();
    HiloEscucha hiloEscucha;

    public Servidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            hiloEscucha = new HiloEscucha(serverSocket);
            System.out.println("Servidor socket iniciado en el puerto: " +
                    puerto);
        }catch (IOException e){
            System.out.println("No se pudo iniciar el Servidor socket en el" +
                    "puerto: " + puerto);
        }
    }

    public void iniciar(){
        hiloEscucha.agregarEscuchador(this);
        hiloEscucha.start();
    }

    public void terminar(){
        try {
            hiloEscucha.detener();
            Enumeration<HiloServidor> lista = clientes.elements();
            while(lista.hasMoreElements()){
                HiloServidor cliente = lista.nextElement();
                if (cliente.getPuerto() > 0){
                    cliente.close();
                }
            }
            serverSocket.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private void agregarCliente(Socket socket){
        System.out.println("Nuevo cliente ---> C-" + socket.getPort());
        HiloServidor cliente = new HiloServidor(socket);
        clientes.put(socket.getPort(), cliente);
        cliente.agregarEscuchador(this);
        cliente.start();
    }

    private String listarClientes(){
        String cadena = "Lista de Clientes: \n";
        Enumeration<HiloServidor> lista = clientes.elements();
        while(lista.hasMoreElements()){
            HiloServidor cliente = lista.nextElement();
            if (cliente.getPuerto() > 0)
                cadena = cadena + "C-"+ cliente.getPuerto() + "\n";
        }
        return cadena;
    }

    @Override
    public void agregarCliente(Evento e) {
        agregarCliente(e.getSocket());
        System.out.println("Se agrego al cliente: C-" + e.getSocket().getPort());
        System.out.println(clientes.size());
    }

    @Override
    public void quitarCliente(int puerto) {
        HiloServidor cliente = (HiloServidor) clientes.get(puerto);
        System.out.println("Quitando hilo cliente C-" + puerto);
        if (cliente != null){
            cliente.close();
            clientes.remove(puerto);
            System.out.println("Se quito el hilo!");
            System.out.println(clientes.size());
        }
    }
}
