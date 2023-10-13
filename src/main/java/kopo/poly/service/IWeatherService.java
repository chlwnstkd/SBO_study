package kopo.poly.service;

import kopo.poly.dto.MovieDTO;
import kopo.poly.dto.WeatherDTO;

import java.util.List;

public interface IWeatherService {
    List<WeatherDTO> getWeatherNow() throws Exception;
    List<WeatherDTO> getWeatherTime() throws Exception;
}
