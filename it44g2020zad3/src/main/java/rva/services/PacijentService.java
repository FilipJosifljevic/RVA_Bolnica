package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.*;
import rva.repository.PacijentRepository;

@Service
public class PacijentService {

	@Autowired
	PacijentRepository pacijentRepository;
	
	public List<Pacijent> getAllPacijent(){
		return pacijentRepository.findAll();
	}
	
	public Optional<Pacijent> getPacijentById(int id){
		return pacijentRepository.findById(id);
	}
	
	public List<Pacijent> getPacijentByIme(String ime){
		return pacijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	public List<Pacijent> getPacijentByPrezime(String prezime){
		return pacijentRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	public List<Pacijent> getPacijentByDijagnoza(Dijagnoza dijagnoza){
		return pacijentRepository.findByDijagnoza(dijagnoza);
	}
	
	public List<Pacijent> getPacijentByOdeljenje(Odeljenje odeljenje){
		return pacijentRepository.findByOdeljenje(odeljenje);
	}
	
	public List<Pacijent> getPacijentByZdravstvenoOsiguranjeTrue(){
		return pacijentRepository.findByzdrOsiguranjeTrue();
	}
	
	public boolean existsById(int id) {
		return getPacijentById(id).isPresent();
	}
	
	public Pacijent addPacijent(Pacijent pacijent) {
		return pacijentRepository.save(pacijent);
	}
	
	public void deletePacijent(int id) {
		pacijentRepository.deleteById(id);
	}
}
