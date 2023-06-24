package org.example;    //il package è la cartella nella quale si trovano i file .java

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/****************************** COMANDI PER EFFETTUARE I TEST ******************************/
// POST:
// Aprire: Windows Powershell
    // Digitare il comando: curl -X POST "http://127.0.0.1:8000" -d "all"
    // Se il comando precedente vi ha dato errore, digitate il comando: Remove-item alias:curl e poi riprovare
    // Ripetere il comando cambiando "all" con "all_sorted"
    // Ripetere il comando cambiando "all" con "more_expensive"
    // Ripetere il comando cambiando "all" con lettere a caso, deve mostrare il messaggio di errore

//GET:
// Aprire il browser (io ho Microsoft Edge sulla macchina virtuale)
    // Inserire nell'url: http://127.0.0.1:8000/?all
    // Ripetere il comando cambiando ?all con ?all_sorted
    // Ripetere il comando cambiando ?all con ?more_expensive
    // Ripetere il comando cambiando ?all con ?lettereacaso, deve mostrare "Comando inesistente"
    // Ripetere il comando togliendo il punto di domanda davanti al comando, deve mostrare "Error 404: Pagina non trovata"


/****************************** CODICE ******************************/
public class App
{
    public static void main( String[] args ) {
        HttpServer server = null;                                                   // Creiamo un HttpServer
        try {                                                                       // il try/catch setta la porta 8000
            server = HttpServer.create(new InetSocketAddress(8000), 0); // e gestisce eventuali errori
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.createContext("/", new MyHandler());    // Sul server creiamo un Handler che servirà a leggere
        server.setExecutor(null);                           // il comando dei client che si connettono
        server.start();                                     // Dopo aver settato tutto avviamolo con il metodo start()
    }
}