package kopo.poly.controller;

import kopo.poly.dto.WeatherDTO;
import kopo.poly.service.IWeatherService;
import kopo.poly.service.impl.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping(value = "now")
    public String getWeatherNow(ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".getWeatherNow Start!");

        List<WeatherDTO> rList1 = Optional.ofNullable(weatherService.getWeatherNow()).orElseGet(ArrayList::new);
        List<WeatherDTO> rList2 = Optional.ofNullable(weatherService.getWeatherTime()).orElseGet(ArrayList::new);
        model.addAttribute("rList1",rList1);
        model.addAttribute("rList2",rList2);

        log.info(this.getClass().getName() + ".getWeatherNow End!");

        return "/weather/now";
    }
}
