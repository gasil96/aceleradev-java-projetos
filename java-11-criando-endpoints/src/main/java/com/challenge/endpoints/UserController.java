package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<User>>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationName(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId
    ) {
        if (accelerationName != null) {
            return new ResponseEntity<List<User>>(userService.findByAccelerationName(accelerationName), HttpStatus.OK);
        }
        if (companyId != null) {
            return new ResponseEntity<List<User>>(userService.findByCompanyId(companyId), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
