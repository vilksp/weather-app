package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaCellRepository extends CrudRepository<ClimaCellWeather,Long> {

}
