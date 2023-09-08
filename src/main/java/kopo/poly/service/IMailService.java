package kopo.poly.service;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface IMailService {

    //메일 발송
    int doSendMail(MailDTO pDTO);

    List<MailDTO> getMailList() throws Exception;

    void insertMailInfo(MailDTO pDTO) throws Exception;
}
