package com.jpacourse.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// Bidirectional relationship with Doctor
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private DoctorEntity doctor;

	// Bidirectional relationship with Patient
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private PatientEntity patient;

	// One-way relationship with MedicalTreatment from parent entity
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER, // we always want to have treatments when we have visit
			orphanRemoval = true)
	@JoinColumn(name = "VISIT_ID", nullable = false)
	private List<MedicalTreatmentEntity> medicalTreatment;

}
