package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 *
 * @Getter => getter 함수를 작성하지 않았지만, 자동 생성
 * @Setter => setter 함수를 작성하지 않았지만, 자동 생성
 */
@Getter
@Setter
@ToString
public class MovieDTO {
    private String collectTime; //수집시간

    private String seq; // 수집된 데이터 순번
    private String movieRank; // 영화 순위
    private String movieNm; // 영화 제목
    private String movieReserve; // 예매율
    private String score; // 평점
    private String openDay; // 개봉일
    private String regId; // 등록자 아이디
    private String regDt; // 등록일
    private String chgId; // 수정자 아이디
    private String chgDt; // 수정일

}

