package org.Gonzik28.patient.dao.mapper;

import org.Gonzik28.patient.entity.MedicalCardEntity;
import org.Gonzik28.patient.entity.PatientEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalCardMapper implements RowMapper<MedicalCardEntity> {
    private static MedicalCardMapper INSTANCE = null;

    private MedicalCardMapper() {
    }

    public static MedicalCardMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MedicalCardMapper();
        }
        return INSTANCE;
    }

    @Override
    public MedicalCardEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicalCardEntity medicalCardEntity = new MedicalCardEntity();
        medicalCardEntity.setId(rs.getString("id"));
        medicalCardEntity.setHeight(rs.getInt("height"));
        medicalCardEntity.setWeight(rs.getDouble("weight"));
        medicalCardEntity.setAttendingDoctor(rs.getString("attending_doctor"));

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(rs.getString("id_patient"));
        patientEntity.setLastName(rs.getString("last_name"));
        patientEntity.setName(rs.getString("name"));
        patientEntity.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());

        medicalCardEntity.setPatient(patientEntity);
        medicalCardEntity.setPatientId(patientEntity.getId());

        return medicalCardEntity;
    }
}
