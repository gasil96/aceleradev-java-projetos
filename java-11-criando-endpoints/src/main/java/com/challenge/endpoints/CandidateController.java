package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.entity.Company;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByAll(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long accelerationId
    ) {
        if (companyId != null) {
            List<Candidate> candidates = candidateService.findByCompanyId(companyId);
            return new ResponseEntity<List<CandidateDTO>>(candidateMapper.map(candidates),
                    HttpStatus.OK);
        }
        if (accelerationId != null) {
            List<Candidate> candidates = candidateService.findByAccelerationId(accelerationId);
            return new ResponseEntity<List<CandidateDTO>>(candidateMapper.map(candidates),
                    HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long companyId,
            @PathVariable Long accelerationId
    ) {
        Candidate response = candidateService.findById(userId, companyId, accelerationId).orElseThrow(() -> new RuntimeException());
        return ResponseEntity.ok(candidateMapper.map(response));
    }

}
    
    
    
