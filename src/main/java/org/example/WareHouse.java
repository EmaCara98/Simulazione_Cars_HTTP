package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private static WareHouse istance;
    private static List<Car> carsList = new ArrayList();
    private WareHouse() {
        buildList();
    }

    public static WareHouse getInstance() {
        if (istance == null) {
            istance = new WareHouse();
        }
        return istance;
    }

    static void buildList() {
        carsList.add(new Car(123,"bmw",3594.9, 2));
        carsList.add(new Car(3634,"audi",38346.9, 1));
        carsList.add(new Car(135,"ferrari",130000.4, 10));
        System.out.println(carsList);
    }

    public String all_JSON() {
        Gson gson = new Gson();
        String s = gson.toJson(carsList);

        return s;
    }

    public String all_HTML()
    {
        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "* {font-family: \"Lucida Console\", \"Courier New\", monospace;}\n" +
                "body {margin: 0;display: flex;justify-content: center;align-items: center;min-height: 100vh;}\n" +
                ".items {border: 3px solid #800000;padding: 15px;font-family: Georgia, serif;}\n" +
                "table {text-align: center;border-collapse: collapse;border: 5px solid #ad0000;background-color: #fff9e5;}\n" +
                ".title {color: #f00;font-family: Verdana, Geneva, sans-serif;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th class=\"items title\">ID</th>\n" +
                "        <th class=\"items title\">NOME</th>\n" +
                "        <th class=\"items title\">PREZZO</th>\n" +
                "        <th class=\"items title\">QUANTITA</th>\n" +
                "    </tr>\n";

        for (Car car : carsList) {
            s += "    <tr>\n";
            s += "        <td class=\"items\">" + car.getId() + "</td>\n";
            s += "        <td class=\"items\">" + car.getNome() + "</td>\n";
            s += "        <td class=\"items\">" + car.getPrezzo() + "</td>\n";
            s += "        <td class=\"items\">" + car.getQuantita() + "</td>\n";
            s += "    </tr>\n";
        }

        s += "</table>\n</body>\n</html>";

        return s;
    }

    public String more_expensive_JSON() {
        double max=0;
        Car c_max = null;

        for(Car car : carsList)
        {
            if(car.getPrezzo()>max)
            {
                max=car.getPrezzo();
                c_max=car;
            }
        }

        Gson gson = new Gson();
        String s = gson.toJson(c_max);

        return s;
    }

    public String more_expensive_HTML() {
        double max=0;
        Car c_max = null;

        for(Car car : carsList)
        {
            if(car.getPrezzo()>max)
            {
                max=car.getPrezzo();
                c_max=car;
            }
        }

        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "* {font-family: \"Lucida Console\", \"Courier New\", monospace;}\n" +
                "body {margin: 0;display: flex;justify-content: center;align-items: center;min-height: 100vh;}\n" +
                ".items {border: 3px solid #800000;padding: 15px;font-family: Georgia, serif;}\n" +
                "table {text-align: center;border-collapse: collapse;border: 5px solid #ad0000;background-color: #fff9e5;}\n" +
                ".title {color: #f00;font-family: Verdana, Geneva, sans-serif;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th class=\"items title\">ID</th>\n" +
                "        <th class=\"items title\">NOME</th>\n" +
                "        <th class=\"items title\">PREZZO</th>\n" +
                "        <th class=\"items title\">QUANTITA</th>\n" +
                "    </tr>\n";

            s += "    <tr>\n";
            s += "        <td class=\"items\">" + c_max.getId() + "</td>\n";
            s += "        <td class=\"items\">" + c_max.getNome() + "</td>\n";
            s += "        <td class=\"items\">" + c_max.getPrezzo() + "</td>\n";
            s += "        <td class=\"items\">" + c_max.getQuantita() + "</td>\n";
            s += "    </tr>\n";

        s += "</table>\n</body>\n</html>";

        return s;
    }

    public String all_sorted_JSON() {

        List<Car> carList_sorted = new ArrayList(carsList);

        carList_sorted.sort((o1, o2) -> {
            return o1.getNome().compareTo(o2.getNome());
        });

        Gson gson = new Gson();
        String s = gson.toJson(carList_sorted);

        return s;
    }

    public String all_sorted_HTML() {

        List<Car> carList_sorted = new ArrayList(carsList);

        carList_sorted.sort((o1, o2) -> {
            return o1.getNome().compareTo(o2.getNome());
        });

        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "* {font-family: \"Lucida Console\", \"Courier New\", monospace;}\n" +
                "body {margin: 0;display: flex;justify-content: center;align-items: center;min-height: 100vh;}\n" +
                ".items {border: 3px solid #800000;padding: 15px;font-family: Georgia, serif;}\n" +
                "#sort {background-color: hsla(332, 83%, 40%, 0.77);color:white}\n" +
                "table {text-align: center;border-collapse: collapse;border: 5px solid #ad0000;background-color: #fff9e5;}\n" +
                ".title {color: #f00;font-family: Verdana, Geneva, sans-serif;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th class=\"items title\">ID</th>\n" +
                "        <th id=\"sort\" class=\"items title\">NOME</th>\n" +
                "        <th class=\"items title\">PREZZO</th>\n" +
                "        <th class=\"items title\">QUANTITA</th>\n" +
                "    </tr>\n";

        for (Car car : carList_sorted) {
            s += "    <tr>\n";
            s += "        <td class=\"items\">" + car.getId() + "</td>\n";
            s += "        <td class=\"items\">" + car.getNome() + "</td>\n";
            s += "        <td class=\"items\">" + car.getPrezzo() + "</td>\n";
            s += "        <td class=\"items\">" + car.getQuantita() + "</td>\n";
            s += "    </tr>\n";
        }

        s += "</table>\n</body>\n</html>";

        return s;
    }


}