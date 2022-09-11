package org.Gonzik28.patient.dao;

import javassist.tools.rmi.ObjectNotFoundException;
import org.Gonzik28.patient.dao.mapper.PatientMapper;
import org.Gonzik28.patient.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PatientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PatientEntity getPatientById(String id) {
        return jdbcTemplate.queryForObject("select id, last_name, name," +
                        " date_of_birth from patients where id=?",
                PatientMapper.getInstance(), id);
    }

    public PatientEntity createPatientDao(PatientEntity patient) throws ObjectNotFoundException {
        if (patient.getId() != null) {
            try {
                return updatePatientDao(patient);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        String id = UUID.randomUUID().toString();
        jdbcTemplate.update("insert into patients (id, last_name, name, date_of_birth) " +
                        " values (?,?,?,?);", id,
                patient.getLastName(),
                patient.getName(),
                patient.getDateOfBirth());
        patient.setId(id);
        return patient;
    }


    public PatientEntity updatePatientDao(PatientEntity patient)
            throws ObjectNotFoundException {
        if (patient == null) {
            return null;
        }
        if (patient.getId() == null) {
            throw new ObjectNotFoundException("Неверный id");
        }
        jdbcTemplate.update("update patients set last_name=?, name=?, date_of_birth=? " +
                        " where id=?",
                patient.getLastName(),
                patient.getName(),
                patient.getDateOfBirth(),
                patient.getId());
        return patient;
    }

    public void deletePatient(String id) {
        jdbcTemplate.update("delete from medical_cards where id_patient = ?", id);
        jdbcTemplate.update("delete from patients where id = ?", id);
    }

}
