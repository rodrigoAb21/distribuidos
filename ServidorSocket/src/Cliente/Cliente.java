package Cliente;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private Socket conexion;

    public Cliente(String direccion, int puerto) {
        try {
            conexion =  new Socket(direccion, puerto);
            HiloCliente hiloCliente = new HiloCliente(conexion);
            hiloCliente.run();
            conexion.close();
        }catch (IOException e){
            System.out.println("No se pudo realizar la conexion con " +
                    direccion + "en el puerto: " + puerto);
        }
    }

    public static void main(String[] args){
        Cliente cliente = new Cliente("127.0.0.1", 1500);
    }
}
