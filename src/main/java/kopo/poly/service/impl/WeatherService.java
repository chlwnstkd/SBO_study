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

        Iterator<Element> location = elements.select("strong.location_name").iterator();
        Iterator<Element> weatherTemp = elements.select("strong.current").iterator();
        Iterator<Element> weatherSummary = elements.select("span.weather").iterator();

        WeatherDTO pDTO = null;

        List<WeatherDTO> pList1 = new ArrayList<>();

        pDTO = new WeatherDTO();


        String loc = CmmUtil.nvl(location.next().text()).trim();
        String temp = CmmUtil.nvl(weatherTemp.next().text()).trim();
        String summary = CmmUtil.nvl(weatherSummary.next().text()).trim();

        log.info("location : " + loc);
        log.info("weather : " + temp);
        log.info("summary : " + summary);

        pDTO.setLoc(loc);
        pDTO.setTemp(temp.substring(5, temp.length()));
        pDTO.setSummary(summary);

        log.info("temp : " + pDTO.toString());

        pList1.add(pDTO);

        log.info(this.getClass().getName() + ".getweatherTemp End!");

        return pList1;
    }

    @Override
    public List<WeatherDTO> getWeatherTime() throws Exception {

        log.info(this.getClass().getName() + ".getWeatherNow Start!");

        String url = "https://weather.naver.com/";


        Document doc = null;

        //Jsoup은 동적 페이지는 크롤링하지 못한다
        //동적페이지를 크롤링하고 싶다면 selenium을 사용해보자
        doc = Jsoup.connect(url).get();


        // 원래 css query : "ul.week_list._cnWeeklyFcastList"
        Elements elements = doc.select("ul.week_list");

        WeatherDTO pDTO = null;

        List<WeatherDTO> pList2 = new ArrayList<>();

        Iterator<Element> li = elements.select("li").iterator();

        while (li.hasNext()) {
            pDTO = new WeatherDTO();

            String list = CmmUtil.nvl(li.next().text()).trim();

            String al[] = list.split(" ");
            for(String a : al) {
                log.info(a);
            }
            pDTO.setTime(al[0].substring(2, al[0].length()));
            pDTO.setAmRain(al[2]);
            pDTO.setAmWeather(al[3]);
            pDTO.setPmRain(al[6]);
            pDTO.setPmWeather(al[4]);
            pDTO.setHighTemp(al[7]);
            pDTO.setLowTemp(al[9]);

            log.info("pDTO : " + pDTO.toString());
            pList2.add(pDTO);
        }

        for (WeatherDTO dto :
                pList2) {
            log.info("dto in List : \n" + dto.toString());
        }

        log.info(this.getClass().getName() + ".getweatherTemp End!");

        return pList2;
    }
}
