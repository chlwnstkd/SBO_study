package kopo.poly.controller;

import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Controller
public class UserInfoController {
    private final IUserInfoService userInfoService;

    @GetMapping(value = "userRegForm")
    public String userRegForm() {
        log.info(this.getClass().getName() + ".user/usrRegForm");

        return "/user/userRegForm";
    }


    @ResponseBody
    @PostMapping(value = "getUserIdExists")
    public UserInfoDTO getUserIdExists(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getUserIdExists Start!");

        String userId = CmmUtil.nvl(request.getParameter("userId"));

        log.info("userId : " + userId);

        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setUserId(userId);

        UserInfoDTO rDTO = Optional.ofNullable(userInfoService.getUserIdExists(pDTO)).orElseGet(UserInfoDTO::new);

        log.info(this.getClass().getName() + ".getUserIdExists End!");

        return rDTO;
    }

    @ResponseBody
    @PostMapping(value = "getEmailExists")
    public UserInfoDTO getEmailExists(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".getEmailExists Start!");

        String email = CmmUtil.nvl(request.getParameter("email"));

        log.info("email : " + email);

        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setEmail(EncryptUtil.encAES128CBC(email));

        UserInfoDTO rDTO = Optional.ofNullable(userInfoService.getEmailExists(pDTO)).orElseGet(UserInfoDTO::new);

        log.info(this.getClass().getName() + ".getEmailExists End!");

        return rDTO;
    }
    @ResponseBody
    @PostMapping(value = "insertUserInfo")
    public MsgDTO insertUserInfo(HttpServletRequest request) throws Exception
    {
        log.info(this.getClass().getName() + ".insertUserInfo start!");
        int res = 0;
        String msg = "";
        MsgDTO dto = null;
        
        UserInfoDTO pDTO = null;

        try {
            String userId = CmmUtil.nvl(request.getParameter("userId"));
            String userName = CmmUtil.nvl(request.getParameter("userName"));
            String password = CmmUtil.nvl (request.getParameter("password"));
            String email = CmmUtil.nvl(request.getParameter("email"));
            String addr1 = CmmUtil.nvl(request.getParameter("addr1"));
            String addr2 = CmmUtil.nvl(request.getParameter("addr2"));

            log.info("userId : " + userId);
            log.info("userName : " + userName);
            log.info("password : " + password);
            log.info("email : " + email);
            log.info("addr1 :" + addr1);
            log.info("addr2 : " + addr2);

            pDTO = new UserInfoDTO();

            pDTO.setUserId(userId);
            pDTO.setUserName(userName);

            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            pDTO.setEmail(EncryptUtil.encAES128CBC(email));
            pDTO.setAddr1(addr1);
            pDTO.setAddr2(addr2);

            res = userInfoService.insertUserInfo(pDTO);

            log.info("회원가입 결과(res) : " + res);

            if (res == 1) {
                msg = "회원가입되었습니다.";
            } else if (res == 2) {
                msg = "이미 가입된 아이디입니다.";
            } else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";
            }
        } catch (Exception e) {
            msg = "실패하였습니다. : " + e;
            log.info(e.toString());
            e.printStackTrace();
        } finally {

            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);

            log.info(this.getClass().getName() + ".insertUserInfo End!");
        }

        return dto;
    }
    @GetMapping(value = "userList")
    public String userList(HttpSession session, ModelMap model) throws Exception {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".userList Start!");
        // 유저 리스트 조회하기
        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        List<UserInfoDTO> rList = Optional.ofNullable(userInfoService.getUserList())
                .orElseGet(ArrayList::new);


        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        // 로그 찍기(추후 찍은 로그를 통해 이 함수 호출이 끝났는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".userList End!");

        // 함수 처리가 끝나고 보여줄 JSP 파일명
        // webapp/WEB-INF/views/user/userList.jsp
        return "user/userList";

    }
    @GetMapping(value = "userInfo")
    public String userInfo(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + ".userInfo Start!");

        String userId = CmmUtil.nvl(request.getParameter("userId"));

        /*
         * ####################################################################################
         * 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
         * ####################################################################################
         */
        log.info("userId : " + userId);

        /*
         * 값 전달은 반드시 DTO 객체를 이용해서 처리함 전달 받은 값을 DTO 객체에 넣는다.
         */
        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setUserId(userId);

        // 공지사항 상세정보 가져오기
        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        UserInfoDTO rDTO = Optional.ofNullable(
                userInfoService.getUserInfo(pDTO)
                ).orElseGet(UserInfoDTO::new);


        rDTO.setEmail(
                EncryptUtil.decAES128CBC(
                        CmmUtil.nvl(rDTO.getEmail())
                )
        );

        log.info("rDTO : " + rDTO.toString());

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName() + ".userInfo End!");

        // 함수 처리가 끝나고 보여줄 JSP 파일명
        // webapp/WEB-INF/views/user/userInfo.jsp
        return "user/userInfo";
    }


    /**
     *
     * 로그인을 위한 입력화면으로 이동
     */
    @GetMapping(value = "login")
    public String login() {
        log.info(this.getClass().getName() + ".user/login Start!");

        log.info(this.getClass().getName() + ".user/login End!");

        return "user/login";
    }

    /**
     *
     * 로그인 처리 및 결과 알려주는 화면으로 이동
     */
    @ResponseBody
    @PostMapping(value="loginProc")
    public MsgDTO loginProc(HttpServletRequest request, HttpSession session) {
        log.info(this.getClass().getName() + ".loginProc Start!");

        int res = 0; //로그인 처리 결과를 저장할 변수 (로그인 성공 : 1, 아이디, 비밀번호 불일치로인한 실패 : 0, 시스템 에러 : 2)
        String msg = ""; //로그인 결과에 대한 메시지를 전달할 변수
        MsgDTO dto = null; //결과 메시지 구조

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO = null;

        try {

            String userId = CmmUtil.nvl(request.getParameter("userId"));
            String password = CmmUtil.nvl(request.getParameter("password"));

            log.info("userId : " + userId);
            log.info("password : " + password);

            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = new UserInfoDTO();

            pDTO.setUserId(userId);

            // 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화함
            pDTO.setPassword(EncryptUtil.encHashSHA256(password));

            //로그인을 위해 아이디오하 비밀번호가 일치하는지 확인하기 위한  userInfoService 호출하기
            UserInfoDTO rDTO = userInfoService.getLogin(pDTO);

            /*
             * 로그인을 성공했다면, 회원아이디 정보를 session에 저장함
             *
             * 세션은 톰켓(was)의 메모리에 존재하며, 웹사이트에 접속한 사람(연결된 객체)마다 메모리에 값을 올린다.
             *
             * 예) 톱켓에 100명의 사용자가 로그인했다면, 사용자 각각 회원아이디를 메모리에 저장하며,
             *     메모리에 저장된 객체의 수는 100개이다.
             *     따라서 과도한 세션은 톰켓의 메모리 부하를 발생시켜 서버가 다운되는 현상이 있을 수 있기 때문에,
             *     최소한으로 사용하는것을 권장한다.
             *
             * 스프링에서 세션을 사용하기 위해서는 함수명의 파라미터에 HttoSession session 존재해야한다.
             * 세션은 톰켓의 메모리에 저장되기 때문에 url마다 전달하는게 필요하지 않고,
             * 그냥 메모리에서 부르면 되기 때문에 jsp, controller에서 쉽게 불러서 쓸수 있다.
             */
            if (CmmUtil.nvl(rDTO.getUserId()).length() > 0) { //로그인 성공
                res = 1;
                /*
                 *세션에 회원아이디 저장하기, 추후 로그인 여부를 체크하기 위해 세션에 값이 존재하는지 체크한다.
                 * 일반적으로 세션에 저장되는 키는 대문자로 입력하며, 앞에 SS를 붙인다
                 *
                 * Session 단어에서 SS를 가져온것이다
                 */
                msg = "로그인이 성공했습니다.";

                session.setAttribute("SS_USER_ID", userId);
                session.setAttribute("SS_USER_NAME", CmmUtil.nvl(rDTO.getUserName()));
            } else {
                msg = "아이디와 비밀번호가 올바르지 않습니다.";
            }
        } catch (Exception e) {
            //저장이 실패되면 사용자에게 보여줄 메시지
            msg = "시스템 문제로 로그인이 실패했습니다";
            res = 2;
            log.info(e.toString());
             /*예외발생 당시의 호출스택에 있던 메소드의 정보와 예외 결과를 화면에 출력한다
              *하지만 성능이 중시되는 어플리케이션에서는 사용을 지양한다
              */
            e.printStackTrace();
        } finally {
            // 결과 메시지 전달하기
            dto = new MsgDTO();
            dto.setResult(res);
            dto.setMsg(msg);

            log.info(this.getClass().getName() + ".loginProc End!");
        }

        return dto;
    }
    @GetMapping(value = "loginResult")
    public String loginResult() {
        log.info(this.getClass().getName() + ".user/loginResult Start!");

        log.info(this.getClass().getName() + ".user/loginResult End!");

        return "user/loginResult";
    }
    @GetMapping(value="loginInfo")
    public String loginInfo(HttpSession session, ModelMap model) {
        log.info(this.getClass().getName() + ".user/loginInfo Start!");

        String userId =  CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));

        String userName = (String) session.getAttribute("SS_USER");



        log.info("userId : " + userId);
        log.info("userName : " + userName);

        UserInfoDTO pDTO = new UserInfoDTO();
        pDTO.setUserId(userId);
        pDTO.setUserName(userName);

        // 공지사항 상세정보 가져오기
        // Java 8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        UserInfoDTO rDTO;
        try {
            rDTO = Optional.ofNullable(userInfoService.getUserInfo(pDTO))
                    .orElseGet(UserInfoDTO::new);
        } catch (Exception e) {
            // 예외 처리 로직을 여기에 추가
            rDTO = new UserInfoDTO(); // 예외 발생 시 기본값으로 대체
        }


        try {
            rDTO.setEmail(
                    EncryptUtil.decAES128CBC(
                            CmmUtil.nvl(rDTO.getEmail())
                    )
            );
        } catch (Exception e) {
            // 예외 처리 로직을 여기에 추가
            e.printStackTrace(); // 예외 메시지 출력 또는 다른 처리 방법을 선택할 수 있습니다.
        }

        log.info("rDTO : " + rDTO.toString());

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName() + ".user/loginInfo End!");

        return "user/loginInfo";
    }
}
