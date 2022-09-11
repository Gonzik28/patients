package org.Gonzik28.patient.controller;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dto.PatientDto;
import org.Gonzik28.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient/create")
    public PatientDto createPatient(@RequestBody PatientDto request) throws ObjectNotFoundException {
        return patientService.createPatient(
                request.getLastName(),
                request.getName(),
                request.getDateOfBirth());
    }

    @PostMapping("/patient/update")
    public PatientDto updatePatient(@RequestBody PatientDto request) throws ObjectNotFoundException {
        return patientService.updatePatient(
                request.getId(),
                request.getLastName(),
                request.getName(),
                request.getDateOfBirth());
    }

    @GetMapping("/patient/{id}")
    public PatientDto searchPatientById(@PathVariable String id) {
        return patientService.findById(id);
    }

    @DeleteMapping("/patient/delete/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}
