package com.loteriaorange.loteriaorange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loteriaorange.loteriaorange.models.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

}
