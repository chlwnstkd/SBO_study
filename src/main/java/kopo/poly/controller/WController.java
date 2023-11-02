package kopo.poly.controller;

import kopo.poly.dto.WDTO;
import kopo.poly.dto.WeatherDTO;
import kopo.poly.service.IWService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
@RestController
public class WController {
    private final IWService weatherService;

    @GetMapping(value = "getWeather")
    public WDTO getWeather(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getWeather Start!");

        String lat = CmmUtil.nvl(request.getParameter("lat"));
        String lon = CmmUtil.nvl(request.getParameter("lon"));

        WDTO pDTO = new WDTO();
        pDTO.setLat(lat);
        pDTO.setLon(lon);

        WDTO rDTO = weatherService.getWeather(pDTO);

        if(rDTO == null) {
            rDTO = new WDTO();
        }

        log.info(this.getClass().getName() + ".getWeather End!");

        return rDTO;
    }
}
