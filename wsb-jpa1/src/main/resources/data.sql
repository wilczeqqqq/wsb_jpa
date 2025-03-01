
-- Address table data
INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code, version)
VALUES
 (1, 'New York', '123 Main St', 'Apt 4B', '62-030', 0),
 (2, 'Los Angeles', '456 Sunset Blvd', NULL, '90028', 0),
 (3, 'Chicago', '789 Lakeshore Dr', NULL, '60611', 0),
 (4, 'San Francisco', '231 Valley', NULL, '10211', 0);

-- Doctor table data
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
 (1, 'John', 'Smith', '555-1234', 'john.smith@hospital.com', 'DOC001', 'SURGEON', 1),
 (2, 'Emily', 'Johnson', '555-5678', 'emily.johnson@hospital.com', 'DOC002', 'GP', 2);

-- Patient table data
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, active)
VALUES
 (1, 'Michael', 'Brown', '555-8765', 'michael.brown@gmail.com', 'PAT001', '1985-02-20', 1, true),
 (2, 'Sarah', 'Davis', '555-4321', 'sarah.davis@yahoo.com', 'PAT002', '1990-07-15', 3, true),
 (3, 'James', 'Wilson', '555-9876', 'james.wilson@email.com', 'PAT003', '1975-11-30', 2, true),
 (4, 'Jacob', 'Brown', '555-9826', 'jacob.brown@email.com', 'PAT004', '1970-11-30', 4, true);

-- Visit table data
INSERT INTO VISIT (id, description, time, doctor_id, patient_id)
VALUES
 (1, 'Routine check-up', '2024-12-01 10:00:00', 1, 1),
 (2, 'Skin evaluation', '2024-12-02 14:30:00', 2, 2),
 (3, 'Routine check-up', '2024-10-01 10:00:00', 2, 4),
 (4, 'Skin evaluation', '2024-10-01 12:00:00', 2, 4);

-- Medical Treatment table data
INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id)
VALUES
    (1, 'Stomach', 'USG', 1),
    (2, 'Heart', 'EKG', 1);