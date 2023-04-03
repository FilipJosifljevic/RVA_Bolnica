package rva.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Bolnica;
import rva.services.BolnicaService;

@RestController
public class BolnicaController {

	@Autowired
	private BolnicaService bolnicaService;
	
	@GetMapping("bolnica")
	public ResponseEntity<?> getAllBolnica(){
		List<Bolnica> bolnice = bolnicaService.getAllBolnica();
		if(bolnice.isEmpty())
			return new ResponseEntity<>(
				"Bolnice - not found",
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
	}
	@GetMapping("bolnica/{id}")
	public ResponseEntity<?> getBolnicaById(@PathVariable("id") int id){
		if(bolnicaService.existsById(id)) {
			Optional<Bolnica> bolnica = bolnicaService.getBolnicaById(id);
			return new ResponseEntity<>(bolnica, HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Bolnica with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("bolnicaNaziv/{naziv}")
	public ResponseEntity<?> getBolnicaByNaziv(@PathVariable("naziv") String nazivBolnice){
		List<Bolnica> bolnice = bolnicaService.getBolnicaByNaziv(nazivBolnice);
		if(bolnice.isEmpty())
			return new ResponseEntity<>(
				"Bolnice with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(bolnice);
	}
	@GetMapping("bolnicaNazivPocetak/{pocetakNaziva}")
	public ResponseEntity<?> getBolnicaByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		List<Bolnica> bolnice = bolnicaService.getBolnicaByPocetnoSlovo(pocetakNaziva);
		if(bolnice.isEmpty())
			return new ResponseEntity<>(
				"Bolnice with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(bolnice);
	}
	
	@GetMapping("bolnicabudzet/{budzet}")
		public ResponseEntity<?> getBolnicaByBudzet(@PathVariable("budzet") int budzet){
			List<Bolnica> bolnice = bolnicaService.getBolnicaByBudzet(budzet);
			if(bolnice.isEmpty())
				return new ResponseEntity<>(
						"Bolnice with budzet lower than that - not found",
						HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.OK).body(bolnice);
		}
	
	@PostMapping("bolnica")
	public ResponseEntity<?> addBolnica(@RequestBody Bolnica bolnica){
		if(bolnicaService.existsById(bolnica.getId())) {
			return new ResponseEntity<>(
					"Bolnica with that id already exists",
					HttpStatus.CONFLICT);
		}
		Bolnica savedBolnica = bolnicaService.addBolnica(bolnica);
		return ResponseEntity.status(HttpStatus.OK).body(savedBolnica);
	}
	
	@PutMapping("bolnica/{id}")
	public ResponseEntity<?> updateBolnica(@RequestBody Bolnica bolnica, @PathVariable("id") int id){
		bolnica.setId(id);
		if(!bolnicaService.existsById(bolnica.getId())) {
			return new ResponseEntity<>(
					"Bolnica with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		Bolnica savedBolnica = bolnicaService.addBolnica(bolnica);
		return ResponseEntity.status(HttpStatus.OK).body(savedBolnica);
	}
	
	@DeleteMapping("bolnica/{id}")
	public ResponseEntity<String> deleteBolnica(@PathVariable("id") int id){
		if(!bolnicaService.existsById(id)) {
			return new ResponseEntity<>(
					"Bolnica with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		bolnicaService.deleteBolnica(id);
		return ResponseEntity.status(HttpStatus.OK).body("Bolnica with that id has been deleted");
	}
}
