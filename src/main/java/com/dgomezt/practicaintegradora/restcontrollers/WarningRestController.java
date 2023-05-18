package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warning")
public class WarningRestController {
    @Autowired
    WarningService warningService;

    @GetMapping("process")
    public ResponseEntity<String> processWarning(Long id){
        warningService.processWarning(id);

        return ResponseEntity.ok("Warning processed");
    }
}
