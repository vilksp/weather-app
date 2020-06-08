package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.url;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url {

    private String UrlForOlderDate = "https://api.climacell.co/v3/weather/historical/station?&lat=54.687157&lon=25.279652&";
    private String UrlForTodaysWeather =
            "https://api.climacell.co/v3/weather/realtime?lat=54.687157&lon=25.279652&unit_system=si&fields=temp";
    private String api_key;

    public Url(String api_key) {
        if (api_key.getClass().equals(String.class)) {
            this.api_key = api_key;
        } else {
            throw new IllegalStateException("Api is not string type!");
        }
    }

    public String getUrlForTodaysWeather() {

        return this.UrlForTodaysWeather += "&apikey=" + this.api_key;
    }

    public String getUrlForOlderDates(String dateFrom, String dateTo) {

        return this.UrlForOlderDate + "start_time=" + dateFrom + "&end_time=" + dateTo +
                "&unit_system=si&fields=temp&" +"&apikey="+ this.api_key;
    }
}
