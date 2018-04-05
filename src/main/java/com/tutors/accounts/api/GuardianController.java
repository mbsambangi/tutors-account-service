package com.tutors.accounts.api;

import com.tutors.accounts.domain.guardian.Guardian;
import com.tutors.accounts.domain.guardian.GuardianService;
import com.tutors.accounts.domain.guardian.GuardianNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guardians")
public class GuardianController {

    private GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Guardian> getGuardian(@PathVariable String name) throws GuardianNotFoundException {
        return ResponseEntity.ok(guardianService.getGuardian(name));
    }

    @PostMapping()
    public ResponseEntity<Guardian> postGuardian(@RequestBody  Guardian guardian) throws GuardianNotFoundException {
        return ResponseEntity.ok(guardianService.saveGuardian(guardian));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void guardianNotFoundHandler(GuardianNotFoundException ex) {}
}
