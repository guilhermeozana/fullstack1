/*
 *  @(#)TasklistAdapter.java
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
package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.config.infra.exceptions.TasklistNotFoundException;
import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.output.TasklistOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * class TasklistAdapter
 *
 * user angelo.vicente
 */
@Component
@RequiredArgsConstructor
public class TasklistAdapter implements TasklistOutputGateway {

    private final TasklistRepository repository;

    @Override
    public Tasklist create(Tasklist tasklist) {
        var entity = tasklist.toEntity();
        var saved = repository.save(entity);
        return Tasklist.of(saved);
    }

    @Override
    public Tasklist findById(String id) {
        return repository.findById(UUID.fromString(id))
                .map(Tasklist::of)
                .orElseThrow(() -> new TasklistNotFoundException(id));
    }

    @Override
    public List<Tasklist> findAll() {
        return Tasklist.of(repository.findAll());
    }

    @Override
    public Tasklist update(String id, Tasklist tasklist) {
        repository.findById(UUID.fromString(id)).orElseThrow(() ->
            new TasklistNotFoundException(id));

        var entityToUpdate = tasklist.toEntity();
        entityToUpdate.setId(UUID.fromString(id));

        var updated = repository.save(entityToUpdate);
        return Tasklist.of(updated);
    }


    @Override
    public void delete(String id) {
        repository.findById(UUID.fromString(id)).orElseThrow(() ->
            new TasklistNotFoundException(id));

        repository.deleteById(UUID.fromString(id));
    }
}

