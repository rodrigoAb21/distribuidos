package Servidor.Eventos;

import java.net.Socket;

public class Evento {

    private Socket socket;

    public Evento(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
