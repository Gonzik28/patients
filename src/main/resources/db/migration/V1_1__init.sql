CREATE TABLE IF NOT EXISTS patients
(
    id text NOT NULL,
    last_name text NOT NULL,
    name text NOT NULL,
    date_of_birth date NOT NULL,
    CONSTRAINT pk_patient_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS medical_cards
(
    id text NOT NULL,
    id_patient text,
    height integer,
    weight real,
    attending_doctor text,
    CONSTRAINT id PRIMARY KEY (id),
    CONSTRAINT fk_patients_id FOREIGN KEY (id_patient)
        REFERENCES patients (id)
);