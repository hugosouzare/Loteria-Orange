package com.loteriaorange.loteriaorange.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Apostador {

	@Id
	private String email;
	
	@OneToMany(mappedBy = "apostador")
	@JsonManagedReference
	private List<Aposta> apostas = new ArrayList<>();
}
