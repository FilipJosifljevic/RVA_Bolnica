package rva.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.*;
import rva.services.DijagnozaService;
import rva.services.OdeljenjeService;
import rva.services.PacijentService;

@CrossOrigin
@RestController
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;
	@Autowired
	private DijagnozaService dijagnozaService;
	@Autowired
	private OdeljenjeService odeljenjeService;
	
	@GetMapping("pacijent")
	public ResponseEntity<?> getAllPacijent(){
		List<Pacijent> pacijenti = pacijentService.getAllPacijent();
		if(pacijenti.isEmpty())
			return new ResponseEntity<>(
				"Pacijenti - not found",
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(pacijenti, HttpStatus.OK);
	}
	
	@GetMapping("pacijent/{id}")
	public ResponseEntity<?> getPacijentById(@PathVariable("id") int id){
		if(pacijentService.existsById(id)) {
			Optional<Pacijent> pacijent = pacijentService.getPacijentById(id);
			return new ResponseEntity<>(pacijent, HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Pacijent with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("pacijentIme/{ime}")
	public ResponseEntity<?> getPacijentByIme(@PathVariable("ime") String imePacijenta){
		List<Pacijent> pacijenti = pacijentService.getPacijentByIme(imePacijenta);
		if(pacijenti.isEmpty())
			return new ResponseEntity<>(
				"Pacijenta with that ime- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(pacijenti);
	}
	
	@GetMapping("pacijentPrezime/{prezime}")
	public ResponseEntity<?> getPacijentByPrezime(@PathVariable("prezime") String prezimePacijenta){
		List<Pacijent> pacijenti = pacijentService.getPacijentByPrezime(prezimePacijenta);
		if(pacijenti.isEmpty())
			return new ResponseEntity<>(
				"Pacijenta with that ime- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(pacijenti);
	}
	
	@PostMapping("pacijent")
	public ResponseEntity<?> addPacijent(@RequestBody Pacijent pacijent){
		if(pacijentService.existsById(pacijent.getId())) {
			return new ResponseEntity<>(
					"Pacijent with that id already exists",
					HttpStatus.CONFLICT);
		}
		Pacijent savedPacijent = pacijentService.addPacijent(pacijent);
		return ResponseEntity.status(HttpStatus.OK).body(savedPacijent);
	}
	
	@PutMapping("pacijent/{id}")
	public ResponseEntity<?> updatePacijent(@RequestBody Pacijent pacijent, @PathVariable("id") int id){
		pacijent.setId(id);
		if(!pacijentService.existsById(pacijent.getId())) {
			return new ResponseEntity<>(
					"Pacijent with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		Pacijent savedPacijent = pacijentService.addPacijent(pacijent);
		return ResponseEntity.status(HttpStatus.OK).body(savedPacijent);
	}
	
	@GetMapping("pacijentiDijagnoze/{id}")
	public ResponseEntity<?> getAllForDijagnoza(@PathVariable("id") int id){
		Optional<Dijagnoza> dijagnozaOpt = dijagnozaService.getDijagnozaById(id);
		if(dijagnozaOpt.isPresent()) {
			
			List<Pacijent> pacijenti = pacijentService.getPacijentByDijagnoza(dijagnozaOpt.get());
			if(pacijenti.isEmpty()) {
				return new ResponseEntity<>("Pacijenti are not found",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(pacijenti,HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Dijagnoza with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("pacijentiOdeljenja/{id}")
	public ResponseEntity<?> getAllForOdeljenje(@PathVariable("id") int id){
		Optional<Odeljenje> odeljenjeOpt = odeljenjeService.getOdeljenjeById(id);
		if(odeljenjeOpt.isPresent()) {
			
			List<Pacijent> pacijenti = pacijentService.getPacijentByOdeljenje(odeljenjeOpt.get());
			if(pacijenti.isEmpty()) {
				return new ResponseEntity<>("Pacijenti are not found",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(pacijenti,HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Odeljenje with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("pacijent/zdravstveno")
	public ResponseEntity<List<Pacijent>> imajuZdravstveno(){
		List<Pacijent> pacijenti = pacijentService.getPacijentByZdravstvenoOsiguranjeTrue();
		return new ResponseEntity<>(pacijenti,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("pacijent/{id}")
	public ResponseEntity<String> deletePacijent(@PathVariable("id") int id){
		if(!pacijentService.existsById(id)) {
			return new ResponseEntity<>(
					"Pacijent with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		pacijentService.deletePacijent(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pacijent with that id has been deleted");
	}
}
