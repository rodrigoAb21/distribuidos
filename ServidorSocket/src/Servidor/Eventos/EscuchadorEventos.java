package Servidor.Eventos;

public interface EscuchadorEventos {
    void agregarCliente(Evento e);
    void quitarCliente(int puerto);
}
