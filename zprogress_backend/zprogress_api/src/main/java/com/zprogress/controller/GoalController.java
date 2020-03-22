package com.zprogress.controller;

import com.zprogress.domain.GoalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
public class GoalController extends AbstractController<GoalDTO> {

    private static final String PATH = "/goal";

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalController.class);

    @Override
    @PostMapping(value = PATH, consumes = JSON)
    ResponseEntity create(@RequestBody GoalDTO goal) {
        LOGGER.info("saving goal, " + goal.getDescription() + " xx ");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
