/*
*  @(#)TasklistOutputGateway.java
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
package br.com.jtech.tasklist.application.ports.output;

import br.com.jtech.tasklist.application.core.domains.Tasklist;

/**
* class TasklistOutputGateway 
* 
* user angelo.vicente 
*/
public interface CreateTasklistOutputGateway {
    Tasklist create(Tasklist tasklist);
}