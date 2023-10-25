package com.innovativeintell.parallel.process.api.service;

import com.innovativeintell.parallel.process.api.model.APIResponse;
import org.springframework.stereotype.Service;

@Service
public interface ParallelDelegatorService {
    APIResponse invokeServices();
}
