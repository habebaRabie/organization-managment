package com.ntg.organization.organization.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "emp_name", length = 100, nullable = false)
	private String name;
	@Column(name = "email", unique = true)
	private String email;
	@JoinColumn(name = "dept_id")
	@ManyToOne
	private Department department;
}
