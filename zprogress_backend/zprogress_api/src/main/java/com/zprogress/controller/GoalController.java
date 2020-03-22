package com.zprogress.controller;

import com.zprogress.domain.objects.GoalDTO;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoalController extends AbstractController<GoalDTO> {

    private static final String PATH = "/goal";

    @Autowired
    private GoalService goalService;

    @Override
    @PostMapping(value = PATH, consumes = JSON)
    ResponseEntity create(@RequestBody GoalDTO goal) {
        goalService.create(goal);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
