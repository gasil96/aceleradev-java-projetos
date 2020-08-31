package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    private ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(
            @RequestParam Long accelerationId,
            @RequestParam Long userId
    ) {
        return new ResponseEntity<List<Challenge>>(challengeService.
                findByAccelerationIdAndUserId(accelerationId, userId), HttpStatus.OK);
    }
}

