
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
import br.com.jtech.tasklist.application.ports.input.CreateTasklistInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.tasklist.application.core.domains.Tasklist.of;

/**
* class TasklistController
* 
* user angelo.vicente
*/
@RestController
@RequestMapping("/api/v1/tasklists")
@RequiredArgsConstructor
public class CreateTasklistController {

    private final CreateTasklistInputGateway createTasklistInputGateway;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TasklistRequest request) {
        createTasklistInputGateway.create(of(request));
        return ResponseEntity.noContent().build();
     }
 }