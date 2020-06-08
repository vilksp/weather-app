package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.url;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url {

    private String Url = "https://api.climacell.co/v3/weather/";

    private String api_key;

    public Url(String api_key) {
        if (api_key.getClass() != null) {
            this.api_key = api_key;
        } else {
            throw new IllegalStateException("You must enter value that is not null");
        }
    }

    public String getUrlForTodaysWeather() {
        String todaysWeatherUrl = this.Url + "realtime?lat=54.687157&lon=25.279652&unit_system=si&fields=temp" + "&apikey=" + this.api_key;
        return todaysWeatherUrl;
    }

    public String getUrlForOlderDates(String dateFrom, String dateTo) {
        String olderDatesUrl = this.Url + "historical/station?&lat=54.687157&lon=25.279652&" + "start_time="
                + dateFrom +
                "&end_time="
                + dateTo +
                "&unit_system=si&fields=temp&"
                + "&apikey=" + this.api_key;

        return olderDatesUrl;
    }
}
