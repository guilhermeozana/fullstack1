package br.com.jtech.tasklist.config.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenAPI30ConfigurationTest {

    @Test
    void shouldCreateOpenAPIWithBearerAuthSecurityScheme() {
        // Arrange
        OpenAPI30Configuration configuration = new OpenAPI30Configuration();

        // Act
        OpenAPI openAPI = configuration.customizeOpenAPI();

        // Assert
        assertNotNull(openAPI, "OpenAPI bean should not be null");

        SecurityScheme securityScheme = openAPI.getComponents()
                .getSecuritySchemes()
                .get("bearerAuth");

        assertNotNull(securityScheme, "Security scheme 'bearerAuth' should be present");
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());

        assertTrue(openAPI.getSecurity().stream()
                        .anyMatch(req -> req.containsKey("bearerAuth")),
                "Security requirement should include 'bearerAuth'");
    }
}
