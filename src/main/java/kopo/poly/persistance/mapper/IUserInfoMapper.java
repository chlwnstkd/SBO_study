package kopo.poly.persistance.mapper;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserInfoMapper {

    //회원 가입하기(회원정보 등록하기)
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;

    List<UserInfoDTO> getUserList() throws Exception;
    UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception;

    //회원 가입 전 아이디 중복체크하기(DB조회하기)
    UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception;

    // 회원 가입 전 이메일 중복체크하기(DB조회하기)
    UserInfoDTO getEmailExists(UserInfoDTO pDTO) throws Exception;
}
