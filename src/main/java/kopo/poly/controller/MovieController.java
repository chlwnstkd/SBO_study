package kopo.poly.controller;

import kopo.poly.dto.MovieDTO;
import kopo.poly.persistance.mapper.IMovieMapper;
import kopo.poly.service.IMailService;
import kopo.poly.service.IMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
@Controller
public class MovieController {

    private final IMovieService movieService;
    @GetMapping(value="collectMovieRank")
    public String collectMovieRank(ModelMap model) throws  Exception {
        log.info(this.getClass().getName() + ".collectMovieRank Start!");

        int res = movieService.collectMovieRank();

        model.addAttribute("msg", "CGV 홈페이지로부터 수집된 영화는 총 " + res + "건입니다");

        log.info(this.getClass().getName() + ".collectMovieRank End!");
        return "/movie/collectMovieRank";
    }

    @GetMapping(value="movieRank")
    public String getMovieRank(ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".getMovieRank Start!");

        List<MovieDTO> rList = Optional.ofNullable(
                movieService.getMovieInfo()
                    ).orElseGet(ArrayList::new);

        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".getMovieRank End!");
        return "/movie/movieList";
    }
}
