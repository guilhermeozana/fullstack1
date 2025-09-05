package br.com.jtech.tasklist.application.ports.output;

import br.com.jtech.tasklist.application.core.domains.Tasklist;

import java.util.List;

public interface TasklistOutputGateway {

    Tasklist create(Tasklist tasklist);

    Tasklist findById(String id);

    List<Tasklist> findAll();

    Tasklist update(String id, Tasklist tasklist);

    void delete(String id);
}
