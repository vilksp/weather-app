package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.url;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Url {

    private String CLIMACELL_URL =
            "https://api.climacell.co/v3/weather/realtime?lat=54.687157&lon=25.279652&unit_system=si&fields=temp";


    public String getUrlForTodaysWeather(String api_key) {

        return this.CLIMACELL_URL += "&apikey=" + api_key;
    }
}
