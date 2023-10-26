package kopo.poly.service.impl;

import kopo.poly.dto.OcrDTO;
import kopo.poly.persistance.mapper.IOcrMapper;
import kopo.poly.service.IOcrService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OcrService implements IOcrService {

    private final IOcrMapper ocrMapper;
    @Value("${ocr.model.data}")
    private String ocrModel;

    /**
     * 이미지 파일로부터 문자 읽어 오기
     * @param pDTO 이미지 파일 정보
     * @return pDTO 이미지로부터 읽은 문자열
     */
    @Override
    public OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getReadforImageText Start!");

        File imageFile = new File(CmmUtil.nvl(pDTO.getFilePath()) + "//" + CmmUtil.nvl(pDTO.getFileName()));

        //OCR 기술 사용을 위한 Tesseract 플랫폼 객체 생성
        ITesseract instance = new Tesseract();

        //OCR 분석에 필요한 기준 데이터(이미 각 나라의 언어별로 학습시킨 데이터 위치 폴더
        //저장 경로는 물리경로를 사용함(전체 경로)
        instance.setDatapath(ocrModel);

        //한국어 학습 데이터 선택(기본 값은 영어)
        instance.setLanguage("kor");
        //instance.setLanguage("eng");

        String result = instance.doOCR(imageFile);

        pDTO.setTextFormImage(result);

        log.info(pDTO.toString());

        int insert = ocrMapper.insertOcr(pDTO);

        if(insert == 1) {
            log.info("DB 저장 성공");
        }

        log.info("result : " + result);

        log.info(this.getClass().getName() + ".getReadforImageText End!");
        return pDTO;
    }

    @Override
    public List<OcrDTO> getOcrList() throws Exception {
        log.info(this.getClass().getName() + ".getOcrList Start!");
        return ocrMapper.getOcrList();
    }
}
