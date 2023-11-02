package kopo.poly.service;

import kopo.poly.dto.WDTO;

public interface IWService {
    String apiURL = "https://api.openweathermap.org/data/3.0/onecall";

    WDTO getWeather(WDTO pDTO) throws Exception;
}
