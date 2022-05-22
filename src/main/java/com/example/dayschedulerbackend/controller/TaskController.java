package com.example.dayschedulerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskController {

    @GetMapping("/by-id/{taskId}")
    public void getTaskById(@PathVariable("taskId") UUID taskId) {

    }


}
