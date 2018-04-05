package com.tutors.accounts.domain.guardian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutors.accounts.api.GuardianController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GuardianController.class)
public class GuardianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GuardianService guardianService;

    @Test
    public void getGurdianReturnGuardian() throws Exception {
        given(guardianService.getGuardian(anyString())).willReturn(new Guardian("Madhu", "Sambangi"));
        
        mockMvc.perform(get("/api/v1/guardians/Madhu")).andExpect(status().isOk())
        .andExpect(jsonPath("firstName").value("Madhu"));
    }

    @Test
    public void postGuardianTakeGuardianReturn() throws Exception {
        Guardian guardianIn = new Guardian("Madhu", "Sambangi");
        given(guardianService.saveGuardian(guardianIn)).willReturn(guardianIn);

        mockMvc.perform(post("/api/v1/guardians")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(guardianIn))
        .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Madhu"));
    }

    @Test
    public void getGuardianNotFound() throws Exception {
        given(guardianService.getGuardian(anyString())).willThrow(new GuardianNotFoundException());

        mockMvc.perform(get("/api/v1/guardians/Madhu")).andExpect(status().isNotFound());
    }
}
