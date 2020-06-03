import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
public class Weather {


    public static String getWeather(String message, Modul modul) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&appid=dae595d153ca0740ba30854431999ec2");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();

        }

        JSONObject object = new JSONObject(result);
        modul.setName(object.getString( "name"));

        JSONObject main = object.getJSONObject("main");
        modul.setTemp(main.getDouble("temp"));
        modul.setHimidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for( int i = 0; i < getArray.length(); ++) {
            JSONObject obj = getArray.getJSONObject(i);
            modul.setIcon((String) obj.get("icon"));
            modul.setMain((String) obj.get("main"));

        }
        return "City: " + modul.getName()+ "\n" +
                "Temperature:" + modul.getTemp() + "\n" +
                "Humidity:" +modul.getHimidity()+ "%" + "\n"+
                 "Main:" +modul.getMain() + "\n" +
                "http://openweathermap.org/img/w/" + modul.getIcon() + ".png";
    }


}

