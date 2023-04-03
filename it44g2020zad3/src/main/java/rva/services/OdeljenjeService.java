package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Bolnica;
import rva.models.Odeljenje;
import rva.repository.OdeljenjeRepository;

@Service
public class OdeljenjeService {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	public List<Odeljenje> getAllOdeljenje(){
		return odeljenjeRepository.findAll();
	}
	
	public Optional<Odeljenje> getOdeljenjeById(int id){
		return odeljenjeRepository.findById(id);
	}
	
	public List<Odeljenje> getOdeljenjeByNaziv(String nazivOdeljenja) {
		return odeljenjeRepository.findByNazivContainingIgnoreCase(nazivOdeljenja);
	}
	
	public List<Odeljenje> getOdeljenjeByPocetnoSlovo(String pocetakNaziva){
		String pocetakNazivaMalimSlovom = pocetakNaziva.toLowerCase();
		return odeljenjeRepository.getByPocetak(pocetakNazivaMalimSlovom);
	}
	
	public List<Odeljenje> getOdeljenjeByBolnica(Bolnica klinika){
		return odeljenjeRepository.findByKlinika(klinika);
	}
	
	public boolean existsById(int id) {
		return getOdeljenjeById(id).isPresent();
	}
	
	public Odeljenje addOdeljenje(Odeljenje odeljenje) {
		return odeljenjeRepository.save(odeljenje);
	}
	
	public void deleteOdeljenje(int id) {
		odeljenjeRepository.deleteById(id);
	}
	
	public Integer nextBolnica(int bolnicaId) { 
		return odeljenjeRepository.nextBolnica(bolnicaId);
	}
}
