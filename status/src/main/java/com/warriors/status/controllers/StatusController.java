package com.warriors.status.controllers;

import com.warriors.status.jparepository.StatusRepository;
import com.warriors.status.model.Status;
import com.warriors.status.services.StatusService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class to receive request related with status
 */
@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService<Status> statusService;


    public StatusController(StatusService<Status> statusService) {

        this.statusService = statusService;
    }

    /**
     * Class to save status on DataBase
     * @param status
     * @return status saved
     */
    @PostMapping("/saveStatus")
    public Status saveStatus(@RequestBody Status status) {
        return statusService.Save(status);
    }

    /**
     * Class to updated status on DataBase
     * @param status
     * @return status updated
     */
    @PostMapping("/updateStatus")
    public Status updateStatus(@RequestBody Status status) {
        return statusService.update(status);
    }
}
