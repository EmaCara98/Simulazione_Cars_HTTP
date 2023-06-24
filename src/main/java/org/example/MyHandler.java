package org.example;    //il package è la cartella nella quale si trovano i file .java

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.URI;

public class MyHandler implements HttpHandler {

    @Override   // Il metodo handle() serve per accettare un client che cerca di connettersi
    public void handle(HttpExchange exchange) throws IOException {
        try
        {
            InputStream is = exchange.getRequestBody();     // Viene ricevuta il comando dell'utente

            URI uri = exchange.getRequestURI();
            System.out.println(uri);

            String method = exchange.getRequestMethod();
            System.out.println(method);
            String s;

            if(method.equals("POST"))                       // Viene fatta una distinzione tra metodo GET e POST
            {
                s = read(is);
            }
            else
            {
                s = uri.getQuery();
            }

            System.out.println(s);

            //String response = "MyJava Sample";
            String response = process(s);               // Viene utilizzato il metodo process() per processare il comando dell'utente

            exchange.sendResponseHeaders(200, response.length());   // La risposta viene inviata al Client
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        catch (Exception e)
        {
            String errorResponse = "Error 404: Pagina non trovata";             // Codice per gestire i comandi senza il "?" davanti
            exchange.sendResponseHeaders(200, errorResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(errorResponse.getBytes());
            os.close();
        }

    }

    private String process(String body){

        /*** CHECK ***/ System.out.println(body);

        //body = body.replace("?", "");     //può servire per rimuovere il "?"

        /*
        String[] splitted = body.split("&");
        if (splitted.length == 0) {
            return "no data\n";
        }
        else if (splitted.length == 1)
        {
            options = splitted[0];
        }
        else if (splitted.length == 2)
        {
            cmd = splitted[0].split("=")[0];
            options = splitted[0].split("=")[1];
        }
        */

        String cmd = body;

        /*** CHECK ***/ System.out.println(cmd);

        String result;

        /***************** COMANDI CLIENT *****************/
        switch(cmd)
        {
            case "all":
                result = WareHouse.getInstance().all();
                break;
            case "all_sorted":
                result = WareHouse.getInstance().all_sorted();
                break;
            case "more_expensive":
                result = WareHouse.getInstance().more_expensive();
                break;
            default:
                result = "Comando inesistente";     // Risposta nel caso venga inserito un comando che non esiste
        }

        return result;
    }

    private String read(InputStream is) {           // Metodo che legge il comando del client
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is));

        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            received += s;

        }
        return received;
    }

}