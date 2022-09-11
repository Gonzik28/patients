package org.Gonzik28.patient;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.MedicalCardDao;
import org.Gonzik28.patient.dto.MedicalCardDto;
import org.Gonzik28.patient.dto.PatientDto;
import org.Gonzik28.patient.entity.MedicalCardEntity;
import org.Gonzik28.patient.entity.PatientEntity;
import org.Gonzik28.patient.service.MedicalCardService;
import org.Gonzik28.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
public class MedicalCardServiceTests extends TestBase {

    @Autowired
    private MedicalCardService medicalCardService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalCardDao medicalCardDao;


    @Test
    public void createMedicalCardTest() throws ObjectNotFoundException {
        LocalDate datePatient = LocalDate.of(1964, 04, 25);
        LocalDate datePatientEntity = LocalDate.of(1868, 03, 28);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setLastName("Горький");
        patientEntity.setName("Максим");
        patientEntity.setDateOfBirth(datePatientEntity);

        PatientDto patientDto = patientService.createPatient("Жириновский",
                "Владимир", datePatient);

        MedicalCardDto medicalCardDto = medicalCardService.createMedicalCard(patientDto.getId(),
                180, 120, "Патологоанатом");

        assertNotNull(medicalCardDto);
        assertNotNull(medicalCardDto.getId());
        assertNotNull(medicalCardDto.getPatientId());
        assertEquals(medicalCardDto.getPatientId(), patientDto.getId());

    }

    @Test
    public void updateMedicalCardTest() throws ObjectNotFoundException {

        LocalDate patientDateOfBirth = LocalDate.of(1964, 4, 25);

        PatientDto patient = patientService.createPatient("Жириновский",
                "Владимир", patientDateOfBirth);

        MedicalCardDto medicalCard = medicalCardService.createMedicalCard(patient.getId(),
                180, 120.00, "Патологоанатом");

        MedicalCardEntity result = medicalCardDao.getMedicalCardById(medicalCard.getId());
        assertEquals(medicalCard.getPatientId(), result.getPatientId());
        assertEquals(180, result.getHeight());

        result.setHeight(190);

        medicalCardDao.updateMedicalCardDao(result);
        result = medicalCardDao.getMedicalCardById(medicalCard.getId());
        assertEquals(190, result.getHeight());
    }
    
}
