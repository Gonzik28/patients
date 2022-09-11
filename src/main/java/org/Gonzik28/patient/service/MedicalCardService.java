package org.Gonzik28.patient.service;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.MedicalCardDao;
import org.Gonzik28.patient.dto.MedicalCardDto;
import org.Gonzik28.patient.dto.utils.MedicalCardMappingUtils;
import org.Gonzik28.patient.entity.MedicalCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MedicalCardService {

    @Autowired
    private MedicalCardDao medicalCardDao;

    public MedicalCardDto findById(String id) {
        MedicalCardEntity medicalCardEntity = medicalCardDao.getMedicalCardById(id);
        return MedicalCardMappingUtils.mapToMedicalCardDto(medicalCardEntity);
    }

    public MedicalCardDto createMedicalCard(String idPatient, int height,
                                            double weight, String attendingDoctor)
            throws ObjectNotFoundException {
        MedicalCardEntity medicalCardEntity = new MedicalCardEntity();
        medicalCardEntity.setPatientId(idPatient);
        medicalCardEntity.setHeight(height);
        medicalCardEntity.setWeight(weight);
        medicalCardEntity.setAttendingDoctor(attendingDoctor);
        return MedicalCardMappingUtils.mapToMedicalCardDto(
                medicalCardDao.createMedicalCardDao(medicalCardEntity));
    }

    public MedicalCardDto updateMedicalCard(String id, String idPatient,
                                            int height, double weight, String attendingDoctor)
            throws ObjectNotFoundException {
        if (id == null) {
            throw new ObjectNotFoundException("Медецинской карты с указанным id не существует");
        }
        MedicalCardDto medicalCardDto = findById(id);
        medicalCardDto.setPatientId(idPatient);
        medicalCardDto.setHeight(height);
        medicalCardDto.setWeight(weight);
        medicalCardDto.setAttendingDoctor(attendingDoctor);
        MedicalCardEntity medicalCardEntity = MedicalCardMappingUtils.mapToPMedicalCardEntity(medicalCardDto);
        medicalCardDao.updateMedicalCardDao(medicalCardEntity);
        return medicalCardDto;
    }

    public void deleteMedicalCard(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        medicalCardDao.deleteMedicalCard(id);
    }

}
