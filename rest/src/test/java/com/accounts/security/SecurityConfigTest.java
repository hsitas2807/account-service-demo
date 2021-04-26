package com.accounts.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

    TestRestTemplate restTemplate;
    URL base;

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "user");
        base = new URL("http://localhost:" + port);
    }

    @Test
    public void whenLoggedUserRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {

        ResponseEntity<String> result = restTemplate.withBasicAuth("user", "user")
                .getForEntity(base.toString()+"/api/accounts", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());



    }
    @Test
    public void whenAdminRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {

        ResponseEntity<String> result = restTemplate.withBasicAuth("admin", "admin")
                .getForEntity(base.toString()+"/api/accounts", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());



    }

    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage() throws IllegalStateException, IOException {
        ResponseEntity<String> result = restTemplate.withBasicAuth("user", "wrong")
                .getForEntity(base.toString()+"/api/accounts", String.class);


        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());

    }
}
