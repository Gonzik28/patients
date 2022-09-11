package org.Gonzik28.patient;


import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.PatientDao;
import org.Gonzik28.patient.dto.PatientDto;
import org.Gonzik28.patient.entity.PatientEntity;
import org.Gonzik28.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class PatientServiceTest extends TestBase {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Test
    public void createPatientTest() throws ObjectNotFoundException {
        LocalDate datePatient = LocalDate.of(1964, 04, 25);
        LocalDate datePatientEntity = LocalDate.of(1868, 03, 28);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setLastName("Горький");
        patientEntity.setName("Максим");
        patientEntity.setDateOfBirth(datePatientEntity);
        PatientDto patientDto = patientService.createPatient("Жириновский",
                "Владимир", datePatient);
        PatientEntity patientEntityOne = patientDao.createPatientDao(patientEntity);

        assertNotNull(patientEntityOne);
        assertNotNull(patientDto);
        assertNotNull(patientEntityOne.getId());
        assertNotNull(patientDto.getId());
        assertEquals(patientEntityOne.getId(), patientEntity.getId());

        PatientEntity patientFromDB = patientDao.getPatientById(patientEntityOne.getId());
        assertNotNull(patientFromDB);
    }

    @Test
    public void updatePatientTest() throws ObjectNotFoundException {
        LocalDate patientDateOfBirth = LocalDate.of(1964, 4, 25);

        PatientDto patient = patientService.createPatient("Жириновский",
                "Владимир", patientDateOfBirth);

        PatientEntity result = patientDao.getPatientById(patient.getId());
        assertEquals("Владимир", result.getName());

        result.setName("Вова");
        patientDao.updatePatientDao(result);
        result = patientDao.getPatientById(patient.getId());
        assertEquals("Вова", result.getName());

    }

}
