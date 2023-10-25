package com.innovativeintell.parallel.process.api.controller;

import com.innovativeintell.parallel.process.api.model.APIResponse;
import com.innovativeintell.parallel.process.api.service.ParallelDelegatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parallel")
public class ParallelProcessingController {

    @Autowired
    public ParallelDelegatorService parallelDelegatorService;

    @GetMapping("/api")
    ResponseEntity<APIResponse> invokeAPI() {
        APIResponse apiResponse = parallelDelegatorService.invokeServices();
        if (apiResponse == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
