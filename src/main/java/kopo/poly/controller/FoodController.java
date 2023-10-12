package kopo.poly.controller;

import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.MovieDTO;
import kopo.poly.service.IFoodService;
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
@RequestMapping(value = "/food")
@RequiredArgsConstructor
@Controller
public class FoodController {

    private final IFoodService foodService;
    @GetMapping(value="toDayFood")
    public String getToDayFood(ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".getToDayFood Start!");

        List<FoodDTO> rList = Optional.ofNullable(foodService.toDayFood()).orElseGet(ArrayList::new);


        model.addAttribute("rList", rList);

        log.info(this.getClass().getName() + ".geTtoDayFood End!");
        return "/food/toDayFood";
    }
}
