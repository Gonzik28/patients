package org.Gonzik28.patient.dto.utils;

import org.Gonzik28.patient.dto.MedicalCardDto;
import org.Gonzik28.patient.entity.MedicalCardEntity;

public class MedicalCardMappingUtils {
    public static MedicalCardDto mapToMedicalCardDto(MedicalCardEntity medicalCardEntity) {
        MedicalCardDto medicalCardDto = new MedicalCardDto();
        medicalCardDto.setId(medicalCardEntity.getId());
        medicalCardDto.setPatientId(medicalCardEntity.getPatientId());
        medicalCardDto.setHeight(medicalCardEntity.getHeight());
        medicalCardDto.setWeight(medicalCardEntity.getWeight());
        medicalCardDto.setAttendingDoctor(medicalCardEntity.getAttendingDoctor());
        return medicalCardDto;
    }

    public static MedicalCardEntity mapToPMedicalCardEntity(MedicalCardDto medicalCardDto) {
        MedicalCardEntity medicalCardEntity = new MedicalCardEntity();
        medicalCardEntity.setId(medicalCardDto.getId());
        medicalCardEntity.setPatientId(medicalCardDto.getPatientId());
        medicalCardEntity.setHeight(medicalCardDto.getHeight());
        medicalCardEntity.setWeight(medicalCardDto.getWeight());
        medicalCardEntity.setAttendingDoctor(medicalCardDto.getAttendingDoctor());
        return medicalCardEntity;
    }
}
