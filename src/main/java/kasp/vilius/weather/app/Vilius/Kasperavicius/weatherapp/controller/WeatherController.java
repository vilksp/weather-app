package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.controller;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository.ClimaCellRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {

    private final ClimaCellRepository climaCellRepository;

    public WeatherController(ClimaCellRepository climaCellRepository) {
        this.climaCellRepository = climaCellRepository;
    }

    @RequestMapping({"","/"})
    public String todaysWeather(Model model) {
        model.addAttribute("todayWeathers", climaCellRepository.findAll());

        return "weather";
    }

}
