package Cliente;

import java.io.*;
import java.net.Socket;

public class HiloCliente extends Thread {
    Socket conexion;
    DataOutputStream datosSalida;
    BufferedReader bufferedReader;

    public HiloCliente(Socket conexion) {
        this.conexion = conexion;
    }

    @Override
    public void run() {
        try {
            datosSalida = new DataOutputStream(conexion.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String cadena;
            while (true){
                cadena = bufferedReader.readLine();
                if (cadena.equals("Salir")) break;
                datosSalida.writeUTF(cadena);
            }
        }catch (IOException e){
            System.out.println("Error en el Hilo Cliente!");
        }
    }
}
