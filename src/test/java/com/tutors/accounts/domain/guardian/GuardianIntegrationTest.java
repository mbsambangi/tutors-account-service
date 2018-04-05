package com.tutors.accounts.domain.guardian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuardianIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void createGuardianAndReturn() throws Exception {
        // Arrange
        Guardian guardianObj = new Guardian("Madhu", "Sambangi");
        // Act
        ResponseEntity<Guardian> guardian = restTemplate.postForEntity("/api/v1/guardians", guardianObj, Guardian.class);
        // Assert
        assertThat(guardian.getBody()).hasNoNullFieldsOrProperties();

    }
}
