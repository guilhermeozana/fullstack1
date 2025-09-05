/*
 *  @(#)TasklistController.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.jtech.tasklist.application.core.domains.Tasklist.of;

/**
 * class TasklistController
 *
 * user angelo.vicente
 */
@RestController
@RequestMapping("/api/v1/tasklists")
@RequiredArgsConstructor
public class TasklistController {

    private final TasklistInputGateway tasklistInputGateway;

    @PostMapping
    public ResponseEntity<TasklistResponse> create(@RequestBody TasklistRequest request) {
        Tasklist tasklist = tasklistInputGateway.create(of(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(TasklistResponse.of(tasklist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasklistResponse> getById(@PathVariable String id) {
        Tasklist task = tasklistInputGateway.findById(id);
        return ResponseEntity.ok(TasklistResponse.of(task));
    }

    @GetMapping
    public ResponseEntity<List<TasklistResponse>> listAll() {
        List<Tasklist> tasks = tasklistInputGateway.findAll();
        List<TasklistResponse> responses = tasks.stream()
                .map(TasklistResponse::of)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasklistResponse> update(@PathVariable String id, @RequestBody TasklistRequest request) {
        Tasklist updated = tasklistInputGateway.update(id, of(request));
        return ResponseEntity.ok(TasklistResponse.of(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tasklistInputGateway.delete(id);
        return ResponseEntity.noContent().build();
    }
}
