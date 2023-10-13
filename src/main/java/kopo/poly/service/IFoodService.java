package kopo.poly.service;

import kopo.poly.dto.FoodDTO;
import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IFoodService {
    List<FoodDTO> toDayFood() throws Exception;
}
