package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.services;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellWeatherNow;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository.ClimaCellRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ClimaCellDataService {

    private static String CLIMACELL_URL =
            "https://api.climacell.co/v3/weather/realtime?lat=54.687157&lon=25.279652&unit_system=si&apikey=bpKzR82N2Sj5hFMp1qzhAAk1IgC1Hcsk";


    @Autowired
    private ClimaCellRepository climaCellRepository;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    private void fetchCurrentClimaCellData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CLIMACELL_URL))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(httpResponse.body());

        ClimaCellWeatherNow todaysWeather = new ClimaCellWeatherNow();


        todaysWeather.setLat(jsonObject.getDouble("lat"));
        todaysWeather.setLon(jsonObject.getDouble("lon"));

        JSONObject prec_json = (JSONObject) jsonObject.get("precipitation");
        todaysWeather.setPrecipitation_val(prec_json.getDouble("value"));

        JSONObject type_json = (JSONObject) jsonObject.get("precipitation_type");
        todaysWeather.setPrecipitation_type(type_json.getString("value"));

        JSONObject time_json = (JSONObject) jsonObject.get("observation_time");
        todaysWeather.setTime(time_json.getString("value"));
        climaCellRepository.save(todaysWeather);

    }


}
