package com.warriors.points.controllers;

import com.warriors.points.jparepository.PointsRepository;
import com.warriors.points.model.Points;
import com.warriors.points.services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class to resive request related to the Points model
 */
@RestController
@RequestMapping("/points")
public class PointsController {
    private final PointService pointService;
    private final PointsRepository pointsRepository;

    @Autowired
    public PointsController(PointService pointService, PointsRepository pointsRepository) {
        this.pointService = pointService;
        this.pointsRepository = pointsRepository;
    }

    /**
     * Method that receive a request to save points
     * @param points
     * @return points saved on DataBase
     */
    @PostMapping("/savePoints")
    public Points savePoints(@RequestBody Points points) {

        return pointsRepository.save(points);
    }

    /**
     * Method that receive a request to update points
     * @param points
     * @return points updated on DataBase
     */
    @PostMapping("/updatePoints")
    public Points updatePoints(@RequestBody Points points) {
        System.out.println(points);
        return pointService.update(points);
    }

}
