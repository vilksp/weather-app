package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.services;

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
    // Vilnius Lat and Lon coords

    @PostConstruct
    private void fetchCurrentClimaCellData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CLIMACELL_URL))
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());
            System.out.println("Test");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
