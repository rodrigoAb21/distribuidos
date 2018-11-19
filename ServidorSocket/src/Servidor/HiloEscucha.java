package Servidor;

import Servidor.Eventos.EscuchadorEventos;
import Servidor.Eventos.Evento;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class HiloEscucha extends Thread {
    ServerSocket serverSocket;
    ArrayList<EscuchadorEventos> escuchadores = new ArrayList<>();

    public HiloEscucha(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            while(true){
                Socket socket = serverSocket.accept();
                if (socket != null){
                    for (EscuchadorEventos escuchador : escuchadores){
                        System.out.println("Disparando evento AGREGAR CLIENTE");
                        escuchador.agregarCliente(new Evento(socket));
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Sucedio un error mientras se escuchaba...");
        }
    }

    public void agregarEscuchador(EscuchadorEventos escuchador){
        escuchadores.add(escuchador);
    }

    public void quitarEscuchador(EscuchadorEventos escuchador){
        escuchadores.remove(escuchador);
    }


}
