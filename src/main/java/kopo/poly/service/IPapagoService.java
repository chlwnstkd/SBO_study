package kopo.poly.service;

import kopo.poly.dto.PapagoDTO;

public interface IPapagoService {

    String detectLangsApiURL = "https://openapi.naver.com/v1/papago/detectLangs";
    PapagoDTO detectLangs(PapagoDTO pDTO) throws Exception;

}
