package kasp.vilius.weather.app.Vilius.Kasperavicius.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClimaCellDataOld {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private double temp;
    private String units;
    private String observation_time;

}
