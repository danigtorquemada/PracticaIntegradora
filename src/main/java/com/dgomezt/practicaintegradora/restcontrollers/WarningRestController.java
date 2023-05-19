package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.WarningService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    ConfProperties confProperties;

    @GetMapping("process")
    public ResponseEntity<String> processWarning(Long id, HttpSession httpSession) {
        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);

        warningService.processWarning(id, userAdmin);

        return ResponseEntity.ok("Warning processed");
    }
}
