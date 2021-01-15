package com.loteriaorange.loteriaorange.services;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loteriaorange.loteriaorange.exceptions.ApostadorException;
import com.loteriaorange.loteriaorange.models.Aposta;
import com.loteriaorange.loteriaorange.models.Apostador;
import com.loteriaorange.loteriaorange.repository.ApostaRepository;
import com.loteriaorange.loteriaorange.repository.ApostadorRepository;



@Service
public class LoteriaService {

	@Autowired
	private ApostadorRepository repo;

	@Autowired
	private ApostaRepository repapo;

	public List<Aposta> buscar(String email) {

		Optional<Apostador> apostador = repo.findById(email);

		if (apostador.isPresent()) {
			List<Aposta> apostas = apostador.get().getApostas();
			return apostas;
		}

		else {
			throw new ApostadorException("Usuário " + email + " não encontrado em nossa base de dados!");
		}

	}

	public Aposta create(String email) {
		Optional<Apostador> apostador = repo.findById(email);
		if (apostador.isPresent()) {
			Aposta bet = new Aposta();
			bet.setNumbers(generateNumbers());
			
			for (Aposta a : apostador.get().getApostas()) {
				if (a.getNumbers().contentEquals(bet.getNumbers())) {
					System.out.println("APOSTA REPETIDA");
					return create(email);
				}
			}
			
			
            bet.setApostador(apostador.get());
			repapo.save(bet);
			apostador.get().getApostas().add(bet);

			return bet;
		}

		else {
			Apostador apost = new Apostador();
			apost.setEmail(email);

			repo.save(apost);
            return create(email);
		}
	}

	private static String generateNumbers() {
		Random ran = new Random();

		String straight = null;
		int num = ran.nextInt(60) + 1;
		int numa = 0;
		int[] numlist = new int[6];
		for (int i = 0; i < 6; i++) {
			numlist[i] = 0;
		}

		for (int i = 0; i < 6; i++) {
			numa = ran.nextInt(60) + 1;
			if (numa == num || haveNumber(numlist, numa)) {
				while (numa == num || haveNumber(numlist, numa)) {
					numa = ran.nextInt(60) + 1;
				}

			}

			numlist[i] = numa;
			num = numa;

			if (straight == null) {
				straight = Integer.toString(numlist[i]);
			} else {

				straight = straight + " - " + Integer.toString(numlist[i]);
			}
		}

		return straight;
	}

	public static boolean haveNumber(int[] l, int n) {
		int cont = 0;
		for (int i = 0; i < 6; i++) {
			if (l[i] == n) {
				cont++;
			}
		}

		if (cont > 0) {
			return true;
		}

		return false;
	}

}
