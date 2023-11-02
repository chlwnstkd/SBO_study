package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WDailyDTO {
    private String day;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private String dayTemp;
    private String dayTempMax;
    private String dayTempMin;
    private String icon;
}
