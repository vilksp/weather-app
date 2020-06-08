package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.repository;

import kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model.ClimaCellDataOld;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaCellOldDataRepo extends CrudRepository<ClimaCellDataOld,Long> {
}
