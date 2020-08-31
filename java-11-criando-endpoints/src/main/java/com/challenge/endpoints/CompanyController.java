package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<Company>>(companyService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId
    ) {
        if (accelerationId != null) {
            return new ResponseEntity<List<Company>>(companyService.findByAccelerationId(accelerationId), HttpStatus.OK);
        }
        if (userId != null) {
            return new ResponseEntity<List<Company>>(companyService.findByUserId(userId), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
