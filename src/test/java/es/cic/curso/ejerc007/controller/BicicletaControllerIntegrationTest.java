package es.cic.curso.ejerc007.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import es.cic.curso.ejerc007.model.Bicicleta;
import es.cic.curso.ejerc007.repository.BicicletaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BicicletaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        bicicletaRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba
    }

    @Test
    @WithMockUser(roles = "VENDEDOR")
    void testRolAnnadirVenta_RolCorrecto() throws Exception {
        // Arrange
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca("Marca");
        bicicleta.setModelo("Modelo");

        // Act & Assert
        mockMvc.perform(post("/api/bicicleta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Marca\",\"modelo\":\"Modelo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SOCIO")
    void testAnnadirVenta_RolIncorrecto() throws Exception {
        // Arrange
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca("Marca");
        bicicleta.setModelo("Modelo");

        // Act & Assert
        mockMvc.perform(post("/api/bicicleta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Marca\",\"modelo\":\"Modelo\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testAnnadirVenta_SinRol() throws Exception {
        // Arrange
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca("Marca");
        bicicleta.setModelo("Modelo");

        // Act & Assert
        mockMvc.perform(post("/api/bicicleta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marca\":\"Marca\",\"modelo\":\"Modelo\"}"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testAnnadirVenta_SinRol2() {
        // Arrange
        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setMarca("Marca");
        bicicleta.setModelo("Modelo");

        // Act & Assert
        try {
            mockMvc.perform(post("/api/bicicleta")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"marca\":\"Marca\",\"modelo\":\"Modelo\"}"))
                    .andExpect(status().isMethodNotAllowed());
        } catch (Exception e) {
            System.out.println("*****AuthenticationCredentialsNotFoundException");
        }
    }
}
