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
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class ClimaCellWeatherNow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double lat; //latitude
    private double lon; //longitude
    private double precipitation_val;
    private String precipitation_type;
    private String time; //might change to Date type


    @Override
    public String toString() {
        return "ClimaCellWeatherNow{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", precipitation_val=" + precipitation_val +
                ", precipitation_type='" + precipitation_type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
