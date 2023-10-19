package kopo.poly.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherDTO {
    private String temp;
    private String summary;
    private String time;
    private String amRain;

    private String amWeather;

    private String pmRain;
    private String pmWeather;
    private String highTemp;
    private String lowTemp;
    private String loc;
}
