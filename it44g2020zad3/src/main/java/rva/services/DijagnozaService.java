package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Dijagnoza;
import rva.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	public List<Dijagnoza> getAllDijagnoza(){
		return dijagnozaRepository.findAll();
	}
	
	public Optional<Dijagnoza> getDijagnozaById(int id){
		return dijagnozaRepository.findById(id);
	}
	
	public List<Dijagnoza> getDijagnozaByNaziv(String nazivDijagnoze){
		return dijagnozaRepository.findByNazivContainingIgnoreCase(nazivDijagnoze);
	}
}
