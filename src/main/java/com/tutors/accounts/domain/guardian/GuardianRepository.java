package com.tutors.accounts.domain.guardian;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<Guardian, String> {

    Guardian findByFirstName(String s) throws GuardianNotFoundException;
}
