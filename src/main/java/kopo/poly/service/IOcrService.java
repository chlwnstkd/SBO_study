package kopo.poly.service;

import kopo.poly.dto.OcrDTO;

import java.util.List;

public interface IOcrService {
    // 이미지 파일로부터 문자 읽어 오기
    OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;
    List<OcrDTO> getOcrList() throws Exception;
}
