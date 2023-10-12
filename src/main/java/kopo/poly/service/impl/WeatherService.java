package kopo.poly.service.impl;

import kopo.poly.dto.WeatherDTO;
import kopo.poly.service.IWeatherService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService implements IWeatherService {
    @Override
    public List<WeatherDTO> getWeatherNow() throws Exception {

        log.info(this.getClass().getName() + ".getWeatherNow Start!");

        String url = "https://weather.naver.com/";

        Document doc = null;

        doc = Jsoup.connect(url).get();

        Elements elements = doc.select("div.section_center");

        Iterator<Element> weatherTemp = elements.select("strong.current").iterator();
        Iterator<Element> weatherSummary = elements.select("span.weather").iterator();

        WeatherDTO pDTO = null;

        List<WeatherDTO> pList1 = new ArrayList<>();

        pDTO = new WeatherDTO();

        while (weatherTemp.hasNext()) {
            String temp = CmmUtil.nvl(weatherTemp.next().text()).trim();
            String summary = CmmUtil.nvl(weatherSummary.next().text()).trim();

            log.info("weather : " + temp);
            log.info("summary : " + summary);

            pDTO.setTemp(temp.substring(5, temp.length()));
            pDTO.setSummary(summary);

            log.info("temp : " + pDTO.toString());

            pList1.add(pDTO);
        }

        log.info(this.getClass().getName() + ".getweatherTemp End!");

        return pList1;
    }

    @Override
    public List<WeatherDTO> getWeatherTime() throws Exception {

        log.info(this.getClass().getName() + ".getWeatherNow Start!");

        String url = "https://weather.naver.com/";

        Document doc = null;

        doc = Jsoup.connect(url).get();

        Elements elements = doc.select("div.weather_table_wrap");

        Iterator<Element> Itime = elements.select("#_idHourly-2023101211 span").iterator();
        Iterator<Element> IweatherTime = elements.select("table tbody tr:nth-child(1) > td div svg g g.bb-chart g.bb-chart-texts g g text.bb-shape.bb-shape-2.bb-text.bb-text-2").iterator();

        WeatherDTO pDTO = null;

        List<WeatherDTO> pList2 = new ArrayList<>();

        pDTO = new WeatherDTO();

        while (Itime.hasNext()) {
            String time = CmmUtil.nvl(Itime.next().text()).trim();
            String weatherTime = CmmUtil.nvl(IweatherTime.next().text()).trim();

            log.info("time : " + time);
            log.info("weatherTime : " + weatherTime);


            pDTO.setTime(time);
            pDTO.setTimeWeather(weatherTime);

            log.info("temp : " + pDTO.toString());

            pList2.add(pDTO);
        }

        log.info(this.getClass().getName() + ".getweatherTemp End!");

        return pList2;
    }
}
