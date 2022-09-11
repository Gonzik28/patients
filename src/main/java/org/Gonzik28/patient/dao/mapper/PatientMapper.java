package org.Gonzik28.patient.dao.mapper;

import org.Gonzik28.patient.entity.PatientEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PatientMapper implements RowMapper<PatientEntity> {
    private static PatientMapper INSTANCE = null;

    private PatientMapper() {
    }

    public static PatientMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PatientMapper();
        }
        return INSTANCE;
    }

    @Override
    public PatientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(rs.getString("id"));
        patientEntity.setLastName(rs.getString("last_name"));
        patientEntity.setName(rs.getString("name"));
        patientEntity.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        return patientEntity;
    }

}
