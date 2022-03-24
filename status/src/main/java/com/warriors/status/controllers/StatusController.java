package com.warriors.status.controllers;

import com.warriors.status.jparepository.StatusRepository;
import com.warriors.status.model.Status;
import com.warriors.status.services.StatusService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusRepository statusRepository;
    private final StatusService<Status> statusService;


    public StatusController(StatusRepository statusRepository, StatusService<Status> statusService) {
        this.statusRepository = statusRepository;
        this.statusService = statusService;
    }

    @PostMapping("/saveStatus")
    public Status associateStatusToWarrior(@RequestBody Status status) {
        return statusRepository.save(status);
    }

    @PostMapping("/updateStatus")
    public Status updateStatus(@RequestBody Status status){
        return statusService.update(status);
    }
}
