package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.services;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellWeatherNow;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository.ClimaCellRepository;
import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.url.Url;
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

//    private static String CLIMACELL_URL =
//            "https://api.climacell.co/v3/weather/realtime?lat=54.687157&lon=25.279652&unit_system=si&fields=temp&apikey=bpKzR82N2Sj5hFMp1qzhAAk1IgC1Hcsk";


    @Autowired
    private ClimaCellRepository climaCellRepository;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    private void fetchCurrentClimaCellData() throws IOException, InterruptedException {
        Url VilniusUrl = new Url();
        String url = VilniusUrl.getUrlForTodaysWeather("bpKzR82N2Sj5hFMp1qzhAAk1IgC1Hcsk");
        /*
        * Must Include Api key above
        *
        * */
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(httpResponse.body());

        ClimaCellWeatherNow todaysWeather = new ClimaCellWeatherNow();


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


//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create())
//                .build();

    }


}
