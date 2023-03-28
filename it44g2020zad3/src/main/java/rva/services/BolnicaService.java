package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Bolnica;
import rva.repository.BolnicaRepository;

@Service
public class BolnicaService {

	@Autowired
	private BolnicaRepository bolnicaRepository;
	
	public List<Bolnica> getAllBolnica(){
		return bolnicaRepository.findAll();
	}
	/*public Bolnica getBolnica(int id){
		return bolnicaRepository.getById(id);
	}*/
	
	public Optional<Bolnica> getBolnicaById(int id){
		return bolnicaRepository.findById(id);
	}

	public List<Bolnica> getBolnicaByNaziv(String nazivBolnice) {
	return bolnicaRepository.findByNazivContainingIgnoreCase(nazivBolnice);
	}
	public List<Bolnica> getBolnicaByPocetnoSlovo(String pocetakNaziva){
		String pocetakNazivaMalimSlovom = pocetakNaziva.toLowerCase();
		return bolnicaRepository.getByPocetak(pocetakNazivaMalimSlovom);
	}
	
	public boolean existsById(int id) {
		return getBolnicaById(id).isPresent();
	}
	
	public Bolnica addBolnica(Bolnica bolnica) {
		return bolnicaRepository.save(bolnica);
	}
	
	public void deleteBolnica(int id) {
		bolnicaRepository.deleteById(id);
	}
}
