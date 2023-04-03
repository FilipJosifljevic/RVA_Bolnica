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

import rva.models.Bolnica;
import rva.models.Odeljenje;
import rva.services.BolnicaService;
import rva.services.OdeljenjeService;

@CrossOrigin
@RestController
public class OdeljenjeController {

	@Autowired
	private OdeljenjeService odeljenjeService;
	@Autowired
	private BolnicaService bolnicaService;
	
	@GetMapping("odeljenje")
	public ResponseEntity<?> getAllOdeljenje(){
		List<Odeljenje> odeljenja = odeljenjeService.getAllOdeljenje();
		if(odeljenja.isEmpty())
			return new ResponseEntity<>(
				"Odeljenja - not found",
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(odeljenja, HttpStatus.OK);
	}
	
	@GetMapping("odeljenje/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable("id") int id){
		if(odeljenjeService.existsById(id)) {
			Optional<Odeljenje> odeljenje = odeljenjeService.getOdeljenjeById(id);
			return new ResponseEntity<>(odeljenje, HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Odeljenje with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("odeljenja/{id}")
	public ResponseEntity<?> getAllForBolnica(@PathVariable("id") int id){
		Optional<Bolnica> bolnicaOpt = bolnicaService.getBolnicaById(id);
		if(bolnicaOpt.isPresent()) {
			
			List<Odeljenje> odeljenja = odeljenjeService.getOdeljenjeByBolnica(bolnicaOpt.get());
			if(odeljenja.isEmpty()) {
				return new ResponseEntity<>("Odeljenja are not found",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(odeljenja,HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Odeljenje with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("odeljenjeNaziv/{naziv}")
	public ResponseEntity<?> getOdeljenjeByNaziv(@PathVariable("naziv") String nazivOdeljenja){
		List<Odeljenje> odeljenja = odeljenjeService.getOdeljenjeByNaziv(nazivOdeljenja);
		if(odeljenja.isEmpty())
			return new ResponseEntity<>(
				"Odeljenja with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(odeljenja);
	}
	@GetMapping("odeljenjeNazivPocetak/{pocetakNaziva}")
	public ResponseEntity<?> getOdeljenjeByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		List<Odeljenje> odeljenja = odeljenjeService.getOdeljenjeByPocetnoSlovo(pocetakNaziva);
		if(odeljenja.isEmpty())
			return new ResponseEntity<>(
				"Odeljenja with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(odeljenja);
	}
	
	@PostMapping("odeljenje")
	public ResponseEntity<?> addOdeljenje(@RequestBody Odeljenje odeljenje){
		if(odeljenjeService.existsById(odeljenje.getId())) {
			return new ResponseEntity<>(
					"Odeljenje with that id already exists",
					HttpStatus.CONFLICT);
		}
		Odeljenje savedOdeljenje = odeljenjeService.addOdeljenje(odeljenje);
		return ResponseEntity.status(HttpStatus.OK).body(savedOdeljenje);
	}
	
	@PutMapping("odeljenje/{id}")
	public ResponseEntity<?> updateOdeljenje(@RequestBody Odeljenje odeljenje, @PathVariable("id") int id){
		odeljenje.setId(id);
		if(!odeljenjeService.existsById(odeljenje.getId())) {
			return new ResponseEntity<>(
					"Odeljenje with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		Odeljenje savedOdeljenje = odeljenjeService.addOdeljenje(odeljenje);
		return ResponseEntity.status(HttpStatus.OK).body(savedOdeljenje);
	}
	
	@DeleteMapping("odeljenje/{id}")
	public ResponseEntity<String> deleteOdeljenje(@PathVariable("id") int id){
		if(!odeljenjeService.existsById(id)) {
			return new ResponseEntity<>(
					"Odeljenje with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		odeljenjeService.deleteOdeljenje(id);
		return ResponseEntity.status(HttpStatus.OK).body("Odeljenje with that id has been deleted");
	}
}

