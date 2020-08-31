package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationService accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Acceleration>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<Acceleration>>(accelerationService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam Long companyId) {
        return new ResponseEntity<List<Acceleration>>(accelerationService.findByCompanyId(companyId), HttpStatus.OK);
    }

}
