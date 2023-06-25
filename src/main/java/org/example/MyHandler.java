package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.URI;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try
        {
            InputStream is = exchange.getRequestBody();

            URI uri = exchange.getRequestURI();
            System.out.println(uri);

            String method = exchange.getRequestMethod();
            System.out.println(method);
            String s;

            if(method.equals("POST"))
            {
                s = read(is);
            }
            else
            {
                s = uri.getQuery();
            }

            System.out.println(s);

            //String response = "MyJava Sample";
            String response = process(s, method);

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        catch (Exception e)
        {
            String errorResponse = "Error 404: Pagina non trovata";
            exchange.sendResponseHeaders(200, errorResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(errorResponse.getBytes());
            os.close();
        }

    }

    private String process(String body, String method){

        /*** CHECK ***/ System.out.println(body);

        //body = body.replace("?", "");

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
                if(method.equals("POST"))
                {
                    result = WareHouse.getInstance().all_JSON();
                }
                else
                {
                    result = WareHouse.getInstance().all_HTML();
                }

                break;
            case "all_sorted":
                if(method.equals("POST"))
                {
                    result = WareHouse.getInstance().all_sorted_JSON();
                }
                else
                {
                    result = WareHouse.getInstance().all_sorted_HTML();
                }

                break;
            case "more_expensive":
                if(method.equals("POST"))
                {
                    result = WareHouse.getInstance().more_expensive_JSON();
                }
                else
                {
                    result = WareHouse.getInstance().more_expensive_HTML();
                }
                break;
            default:
                result = "Comando inesistente";
        }

        return result;
    }

    private String read(InputStream is) {
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