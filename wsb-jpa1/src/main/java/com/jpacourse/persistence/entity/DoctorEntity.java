package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.Specialization;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

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
	private String doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	// One-way relationship with Address from parent entity
	@OneToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			optional = false,
			orphanRemoval = true)
	private AddressEntity address;

	// Bidirectional relationship with Visit
	@OneToMany(mappedBy = "doctor",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, // we don't want to remove doctor when visit is removed
			fetch = FetchType.LAZY)
	private List<VisitEntity> visits;

}
