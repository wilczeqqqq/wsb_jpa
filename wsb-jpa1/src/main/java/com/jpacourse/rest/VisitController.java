package com.jpacourse.rest;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitController
{
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visits/patient/{patientId}")
    List<VisitTO> findVisitsByPatientId(@PathVariable final Long patientId) {
        return visitService.findVisitsByPatientId(patientId);
    }
}
