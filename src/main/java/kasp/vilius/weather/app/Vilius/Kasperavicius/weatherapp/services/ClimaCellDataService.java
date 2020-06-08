package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.services;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellDataOld;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellWeather;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository.ClimaCellOldDataRepo;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository.ClimaCellRepository;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.url.Url;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class ClimaCellDataService {


    private final ClimaCellRepository climaCellRepository;
    private final ClimaCellOldDataRepo climaCellOldDataRepo;


    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    private void fetchCurrentClimaCellData() throws IOException, InterruptedException {
        Url VilniusUrl = new Url("");
        /*
         * Must Include Api key above
         *
         */
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VilniusUrl.getUrlForTodaysWeather()))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(httpResponse.body());

        ClimaCellWeather todaysWeather = new ClimaCellWeather();

        todaysWeather.setLat(jsonObject.getDouble("lat"));
        todaysWeather.setLon(jsonObject.getDouble("lon"));

        JSONObject prec_json = (JSONObject) jsonObject.get("temp");
        todaysWeather.setTemp_val(prec_json.getDouble("value"));

        todaysWeather.setUnits(prec_json.getString("units"));

        JSONObject time_json = (JSONObject) jsonObject.get("observation_time");
        todaysWeather.setTime(time_json.getString("value"));
        climaCellRepository.save(todaysWeather);

    }

    @PostConstruct
    public void fetchOlderData() throws IOException, InterruptedException {

        Url olderWeatherData = new Url("");

        /*
         * Must Include Api key above
         *
         */
        String url = olderWeatherData.getUrlForOlderDates("2020-06-09", "now");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();


        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body().trim().charAt(0));

        JSONArray array = new JSONArray(httpResponse.body().trim());

        for (int i = 0; i < array.length(); i++) {
            ClimaCellDataOld obj = new ClimaCellDataOld();
            JSONObject row = array.getJSONObject(i);
            JSONObject temp_vals = (JSONObject) row.get("temp");
            obj.setTemp(temp_vals.getDouble("value"));
            obj.setUnits(temp_vals.getString("units"));
            JSONObject time_vals = (JSONObject) row.get("observation_time");
            obj.setObservation_time(time_vals.getString("value"));

            climaCellOldDataRepo.save(obj);

        }


    }


}
