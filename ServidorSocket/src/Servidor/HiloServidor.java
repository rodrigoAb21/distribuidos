package Servidor;

import Servidor.Eventos.EscuchadorEventos;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HiloServidor extends Thread{
    private ArrayList<EscuchadorEventos> escuchadores = new ArrayList<>();
    private Socket cliente;
    private DataInputStream flujoDatosEntrada;
    private DataOutputStream flujoDatosSalida;
    private int puerto;

    static InputStreamReader isr;
    static BufferedReader br;
    static String message;


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

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            while (true) {

                isr = new InputStreamReader(cliente.getInputStream());
                br = new BufferedReader(isr);

                message = br.readLine();

                System.out.println("C-" + cliente.getPort() +": "+ message);
                flujoDatosEntrada.readUTF();
//                System.out.println("C-" + cliente.getPort() +": "+ mensaje);


            }
        }catch (IOException e){
            System.out.println("Se perdio conexion con C-" + getPuerto());
            for (EscuchadorEventos escuchador : escuchadores){
                escuchador.quitarCliente(getPuerto());
            }
            close();
        }

    }

    public void close(){
        try {
            if (flujoDatosEntrada != null) flujoDatosEntrada.close();
            if (cliente != null) cliente.close();
//            if (isr != null) isr.close();
//            if (br != null) br.close();

            setPuerto(-1);

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void agregarEscuchador(EscuchadorEventos escuchador){
        escuchadores.add(escuchador);
    }

    public void quitarEscuchador(EscuchadorEventos escuchador){
        escuchadores.remove(escuchador);
    }


}
