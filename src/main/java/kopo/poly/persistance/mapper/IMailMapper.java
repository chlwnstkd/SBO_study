package kopo.poly.persistance.mapper;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMailMapper {

	//메일 리스트
	List<MailDTO> getMailList() throws Exception;
	
	//메일 데이터에 넣기
	void insertMailInfo(MailDTO pDTO) throws Exception;
	
}
