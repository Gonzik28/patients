package org.Gonzik28.patient.dao;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.mapper.MedicalCardMapper;
import org.Gonzik28.patient.entity.MedicalCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MedicalCardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MedicalCardEntity getMedicalCardById(String id) {
        return jdbcTemplate.queryForObject("select medical_cards.id, id_patient, last_name," +
                        " name, date_of_birth, height, weight, attending_doctor" +
                        " from medical_cards inner join patients on" +
                        " medical_cards.id_patient = patients.id where medical_cards.id=?",
                MedicalCardMapper.getInstance(), id);
    }

    public MedicalCardEntity createMedicalCardDao(MedicalCardEntity medicalCardEntity)
            throws ObjectNotFoundException {
        if (medicalCardEntity.getId() != null) {
            try {
                return updateMedicalCardDao(medicalCardEntity);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        String id = UUID.randomUUID().toString();
        jdbcTemplate.update("insert into medical_cards (id, id_patient, height," +
                        " weight, attending_doctor) values (?,?,?,?,?);", id,
                medicalCardEntity.getPatientId(),
                medicalCardEntity.getHeight(), medicalCardEntity.getWeight(),
                medicalCardEntity.getAttendingDoctor());
        medicalCardEntity.setId(id);
        return medicalCardEntity;
    }

    public MedicalCardEntity updateMedicalCardDao(MedicalCardEntity medicalCardEntity)
            throws ObjectNotFoundException {
        if (medicalCardEntity == null) {
            return null;
        }
        if (medicalCardEntity.getId() == null)
            throw new ObjectNotFoundException("Медецинской карты с указанным id не существует," +
                    " но вы можете создать новый объект");

        jdbcTemplate.update("update medical_cards set id_patient=?, height=?," +
                        "  weight=?, attending_doctor=? where id=?",
                medicalCardEntity.getPatientId(),
                medicalCardEntity.getHeight(),
                medicalCardEntity.getWeight(),
                medicalCardEntity.getAttendingDoctor(),
                medicalCardEntity.getId());
        return medicalCardEntity;
    }

    public void deleteMedicalCard(String id) {
        jdbcTemplate.update("delete from medical_cards where id = ?", id);
    }

}

