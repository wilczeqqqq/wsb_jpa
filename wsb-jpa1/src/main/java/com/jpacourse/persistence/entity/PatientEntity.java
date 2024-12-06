package com.jpacourse.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	// One-way relationship with Address from parent entity
	@OneToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			optional = false,
			orphanRemoval = true)
	private AddressEntity address;

	// Bidirectional relationship with Visit
	@OneToMany(mappedBy = "patient",
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}) // we don't want to remove doctor when visit is removed
	private List<VisitEntity> visits;

}
