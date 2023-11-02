package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class WDTO {
    private String lat;
    private String lon;
    private double currentTemp;

    private List<WDailyDTO> dailyList;
}
