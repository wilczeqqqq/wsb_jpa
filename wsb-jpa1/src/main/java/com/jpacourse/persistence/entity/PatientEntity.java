package com.jpacourse.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

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

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Column(nullable = false)
	private boolean active = true;

	// One-way relationship with Address from parent entity
	@OneToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			optional = false,
			orphanRemoval = true)
	private AddressEntity address;

	// Bidirectional relationship with Visit
	@OneToMany(mappedBy = "patient",
			fetch = FetchType.LAZY,
			cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}) // we don't want to remove doctor when visit is removed
	private List<VisitEntity> visits;

}
