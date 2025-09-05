/*
 *  @(#)Tasklist.java
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
package br.com.jtech.tasklist.application.core.domains;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import lombok.*;

import java.util.UUID;
import java.util.List;

/**
 * class Tasklist
 *
 * user angelo.vicente
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tasklist {

    private String id;
    private String title;
    private String description;
    private TaskStatus status;

    public static List<Tasklist> of(List<TasklistEntity> entities) {
        return entities.stream().map(Tasklist::of).toList();
    }

    public TasklistEntity toEntity() {
        return TasklistEntity.builder()
                .id(getId() != null ? UUID.fromString(getId()) : null)
                .title(getTitle())
                .description(getDescription())
                .status(getStatus().name())
                .build();
    }

    public static Tasklist of(TasklistEntity entity) {
        return Tasklist.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(TaskStatus.fromString(entity.getStatus()))
                .build();
    }

    public static Tasklist of(TasklistRequest request) {
        return Tasklist.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.fromString(request.getStatus()))
                .build();
    }
}
