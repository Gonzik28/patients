package org.Gonzik28.patient.service;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.PatientDao;
import org.Gonzik28.patient.dto.PatientDto;
import org.Gonzik28.patient.dto.utils.PatientMappingUtils;
import org.Gonzik28.patient.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.Gonzik28.patient.dto.utils.PatientMappingUtils.mapToPatientDto;


@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientDao patientDao;

    public PatientDto findById(String id) {
        PatientEntity patientEntity = patientDao.getPatientById(id);
        return mapToPatientDto(patientEntity);
    }

    public PatientDto createPatient(String lastName, String name, LocalDate dateOfBirth) throws ObjectNotFoundException {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setLastName(lastName);
        patientEntity.setName(name);
        patientEntity.setDateOfBirth(dateOfBirth);
        patientEntity = patientDao.createPatientDao(patientEntity);

        return mapToPatientDto(patientEntity);
    }

    public PatientDto updatePatient(String id, String lastName, String name,
                                    LocalDate dateOfBirth) throws ObjectNotFoundException {
        if (id == null) {
            throw new ObjectNotFoundException("Пациента с указанным id не существует");
        }
        PatientDto patientDto = findById(id);
        patientDto.setLastName(lastName);
        patientDto.setName(name);
        patientDto.setDateOfBirth(dateOfBirth);
        PatientEntity patientEntity = PatientMappingUtils.mapToPatientEntity(patientDto);
        patientDao.updatePatientDao(patientEntity);
        return patientDto;
    }

    public void deletePatient(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        patientDao.deletePatient(id);
    }

}
