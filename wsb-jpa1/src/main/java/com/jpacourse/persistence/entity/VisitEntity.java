package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	//Relacja dwukierunkowa z encja Doctor.
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor;

	//Relacja dwukierunkowa z encja Patient.
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient;

	//Relacja jednokierunkowa do encji Visit.
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
			fetch = FetchType.LAZY
	)
	@JoinColumn(name = "MEDICAL_TREATMENT_ID")
	private List<MedicalTreatmentEntity> medicalTreatment;

	public List<MedicalTreatmentEntity> getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setMedicalTreatment(List<MedicalTreatmentEntity> medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
