package kopo.poly.persistance.mapper;

import kopo.poly.dto.MovieDTO;
import kopo.poly.dto.OcrDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOcrMapper {
    int insertOcr(OcrDTO pDTO) throws Exception;
    List<OcrDTO> getOcrList() throws Exception;
}
