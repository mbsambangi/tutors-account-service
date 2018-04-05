package com.tutors.accounts.domain.guardian;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GuardianServiceTest {

    @Mock
    private GuardianRepository guardianRepository;

    private GuardianService guardianService;

    @Before
    public void setup() throws Exception {
        guardianService = new GuardianService(guardianRepository);
    }

    @Test
    public void getGaurdianReturnGaurdian() throws Exception {
        given(guardianRepository.findByFirstName("Madhu")).willReturn(new Guardian("Madhu", "Babu"));

        Guardian guardian = guardianService.getGuardian("Madhu");

        assertThat(guardian.getFirstName()).isEqualTo("Madhu");
    }

    @Test
    public void saveGuardianReturnGuardian() throws Exception {
        Guardian guardianIn = new Guardian("Madhu", "Sambangi");
        given(guardianRepository.save(guardianIn)).willReturn(guardianIn);

        Guardian guardianOut = guardianService.saveGuardian(guardianIn);

        assertThat(guardianOut.getFirstName()).isEqualTo("Madhu");
    }

    @Test(expected = GuardianNotFoundException.class)
    public void getGuardianReturnException() throws Exception {
        given(guardianRepository.findByFirstName("Madhu")).willThrow(new GuardianNotFoundException());

        guardianService.getGuardian("Madhu");
    }

}
