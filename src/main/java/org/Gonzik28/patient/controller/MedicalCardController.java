package org.Gonzik28.patient.controller;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dto.MedicalCardDto;
import org.Gonzik28.patient.service.MedicalCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalCardController {

    @Autowired
    private MedicalCardService medicalCardService;

    @GetMapping("/medicalCard/{id}")
    public MedicalCardDto searchMedCardById(@PathVariable String id) {
        return medicalCardService.findById(id);
    }

    @PostMapping("/medicalCard/create")
    public MedicalCardDto createCard(@RequestBody MedicalCardDto request) throws ObjectNotFoundException {
        return medicalCardService.createMedicalCard(
                request.getPatientId(),
                request.getHeight(),
                request.getWeight(),
                request.getAttendingDoctor()
        );
    }

    @PostMapping("/medicalCard/update")
    public MedicalCardDto updateCard(@RequestBody MedicalCardDto request) throws ObjectNotFoundException {
        return medicalCardService.updateMedicalCard(
                request.getId(),
                request.getPatientId(),
                request.getHeight(),
                request.getWeight(),
                request.getAttendingDoctor());
    }

    @DeleteMapping("/medicalCard/delete/{id}")
    public void deleteMedCard(@PathVariable String id) {
        medicalCardService.deleteMedicalCard(id);
    }

}
