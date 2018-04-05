package com.tutors.accounts.domain.guardian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GuardianRepositoryTest {

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getGuardianReturnGuardian() throws Exception {

        Guardian savedGuardian = entityManager.persistFlushFind(new Guardian("Madhu", "Sambangi"));

        Guardian guardian = guardianRepository.findByFirstName("Madhu");

        assertThat(guardian.getFirstName()).isEqualTo("Madhu");
    }
}