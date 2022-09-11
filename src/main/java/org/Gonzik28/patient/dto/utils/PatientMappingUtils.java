package org.Gonzik28.patient.dto.utils;

import org.Gonzik28.patient.dto.PatientDto;
import org.Gonzik28.patient.entity.PatientEntity;

public class PatientMappingUtils {
    public static PatientDto mapToPatientDto(PatientEntity patientEntity) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientEntity.getId());
        patientDto.setName(patientEntity.getName());
        patientDto.setLastName(patientEntity.getLastName());
        patientDto.setDateOfBirth(patientEntity.getDateOfBirth());
        return patientDto;
    }

    public static PatientEntity mapToPatientEntity(PatientDto patientDto) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientDto.getId());
        patientEntity.setName(patientDto.getName());
        patientEntity.setLastName(patientDto.getLastName());
        patientEntity.setDateOfBirth(patientDto.getDateOfBirth());
        return patientEntity;
    }
}
