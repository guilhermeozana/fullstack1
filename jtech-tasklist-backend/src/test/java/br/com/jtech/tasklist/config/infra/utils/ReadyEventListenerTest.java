package br.com.jtech.tasklist.config.infra.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ReadyEventListenerTest {

    @Test
    void shouldLogEnvironmentOnApplicationReadyEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ReadyEventListener.class);
        context.refresh();

        SpringApplication application = new SpringApplication(ReadyEventListener.class);

        ApplicationReadyEvent event = new ApplicationReadyEvent(
                application,
                new String[]{},
                context,
                Duration.ofHours(1)
        );

        ReadyEventListener listener = context.getBean(ReadyEventListener.class);

        assertDoesNotThrow(() -> listener.start(event));

        context.close();
    }
}
