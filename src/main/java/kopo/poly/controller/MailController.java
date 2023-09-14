package kopo.poly.controller;


import kopo.poly.dto.MailDTO;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.MailDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/mail")
@RequiredArgsConstructor
@Controller
public class MailController {

    private final IMailService mailService;

    @GetMapping(value = "mailForm")
    public String mailForm() throws Exception {

        log.info(this.getClass().getName() + "mailForm Start!");

        return "/mail/mailForm";
    }

    @ResponseBody
    @PostMapping(value = "sendMail")
    public MsgDTO sendMail(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + ".sendMail Start!");

        String msg = "";

        String toMail = CmmUtil.nvl(request.getParameter("toMail"));
        String title = CmmUtil.nvl(request.getParameter("title"));
        String contents = CmmUtil.nvl(request.getParameter("contents"));

        log.info("toMail : " + toMail);
        log.info("title : " + title);
        log.info("contents : " + contents);

        MailDTO pDTO = new MailDTO();

        pDTO.setToMail(toMail);
        pDTO.setTitle(title);
        pDTO.setContents(contents);

        int res = mailService.doSendMail(pDTO);

        if(res == 1) {
            msg = "메일 발송하였습니다";
        }else {
            msg = "메일 발송 실패하였습니다";
        }

        log.info(msg);

        MsgDTO dto = new MsgDTO();
        dto.setMsg(msg);

        log.info(this.getClass().getName() + ".sendMail End!");

        return dto;
    }
    @GetMapping(value = "mailList")
    public String mailList(HttpSession session, ModelMap model)
            throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mailList Start!");

        // 공지사항 리스트 조회하기
        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        List<MailDTO> rList = Optional.ofNullable(mailService.getMailList())
                .orElseGet(ArrayList::new);

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".mailList End!");

        // 함수 처리가 끝나고 보여줄 JSP 파일명
        // webapp/WEB-INF/views/mail/mailList.jsp
        return "mail/mailList";

    }
    @ResponseBody
    @PostMapping(value = "mailInsert")
    public MsgDTO mailInsert(HttpServletRequest request, HttpSession session) {

        log.info(this.getClass().getName() + ".mailInsert Start!");

        String msg = ""; // 메시지 내용

        MsgDTO dto = null; // 결과 메시지 구조

        try {
            // 로그인된 사용자 아이디를 가져오기
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String toMail = CmmUtil.nvl(request.getParameter("toMail")); // 받는 사람
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용

            /*
             * ####################################################################################
             * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
             * ####################################################################################
             */
            log.info("title : " + title);
            log.info("toMail : " + toMail);
            log.info("contents : " + contents);

            // 데이터 저장하기 위해 DTO에 저장하기
            MailDTO pDTO = new MailDTO();
            pDTO.setTitle(title);
            pDTO.setToMail(toMail);
            pDTO.setContents(contents);

            /*
             * 게시글 등록하기위한 비즈니스 로직을 호출
             */
            mailService.insertMailInfo(pDTO);

            // 저장이 완료되면 사용자에게 보여줄 메시지
            msg = "";

        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            // 결과 메시지 전달하기
            dto = new MsgDTO();
            dto.setMsg(msg);

            log.info(this.getClass().getName() + ".mailInsert End!");
        }

        return dto;
    }
}
