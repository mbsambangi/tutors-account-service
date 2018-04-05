package com.tutors.accounts.domain.guardian;

import org.springframework.stereotype.Service;

@Service
public class GuardianService {

    private GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }

    public Guardian getGuardian(String name) throws GuardianNotFoundException {
        return guardianRepository.findByFirstName(name);
    }

    public Guardian saveGuardian(Guardian guardianIn) {
        return guardianRepository.save(guardianIn);
    }
}
