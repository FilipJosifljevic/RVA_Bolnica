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

import rva.models.Dijagnoza;
import rva.services.DijagnozaService;

@RestController
public class DijagnozaController {
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
	@GetMapping("dijagnoza")
	public ResponseEntity<?> getAllDijagnoza(){
		List<Dijagnoza> dijagnoze = dijagnozaService.getAllDijagnoza();
		if(dijagnoze.isEmpty())
			return new ResponseEntity<>(
					"Dijagnoze - not found",
					HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(dijagnoze,HttpStatus.OK);
		
	}

	@GetMapping("dijagnoza/{id}")
	public ResponseEntity<?> getDijagnozaById(@PathVariable("id") int id){
		if(dijagnozaService.existsById(id)) {
			Optional<Dijagnoza> dijagnoza = dijagnozaService.getDijagnozaById(id);
			return new ResponseEntity<>(dijagnoza, HttpStatus.OK);
		}
		return new ResponseEntity<>(
				"Dijagnoza with requested id "+ id +" not found",
				HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("dijagnozaNaziv/{naziv}")
	public ResponseEntity<?> getDijagnozaByNaziv(@PathVariable("naziv") String nazivDijagnoze){
		List<Dijagnoza> dijagnoze = dijagnozaService.getDijagnozaByNaziv(nazivDijagnoze);
		if(dijagnoze.isEmpty())
			return new ResponseEntity<>(
				"Dijagnoze with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(dijagnoze);
	}
	
	@GetMapping("dijagnozaNazivPocetak/{pocetakNaziva}")
	public ResponseEntity<?> getDijagnozaByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		List<Dijagnoza> dijagnoze = dijagnozaService.getDijagnozaByPocetnoSlovo(pocetakNaziva);
		if(dijagnoze.isEmpty())
			return new ResponseEntity<>(
				"Dijagnoze with that naziv- not found",
				HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(dijagnoze);
	}
	
	@PostMapping("dijagnoza")
	public ResponseEntity<?> addDijagnoza(@RequestBody Dijagnoza dijagnoza){
		if(dijagnozaService.existsById(dijagnoza.getId())) {
			return new ResponseEntity<>(
					"Dijagnoza with that id already exists",
					HttpStatus.CONFLICT);
		}
		Dijagnoza savedDijagnoza = dijagnozaService.addDijagnoza(dijagnoza);
		return ResponseEntity.status(HttpStatus.OK).body(savedDijagnoza);
	}
	
	@PutMapping("dijagnoza/{id}")
	public ResponseEntity<?> updateDijagnoza(@RequestBody Dijagnoza dijagnoza, @PathVariable("id") int id){
		dijagnoza.setId(id);
		if(!dijagnozaService.existsById(dijagnoza.getId())) {
			return new ResponseEntity<>(
					"dijagnoza with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		Dijagnoza savedDijagnoza = dijagnozaService.addDijagnoza(dijagnoza);
		return ResponseEntity.status(HttpStatus.OK).body(savedDijagnoza);
	}
	
	@DeleteMapping("dijagnoza/{id}")
	public ResponseEntity<String> deleteDijagnoza(@PathVariable("id") int id){
		if(!dijagnozaService.existsById(id)) {
			return new ResponseEntity<>(
					"Dijagnoza with that id doesn't exist",
					HttpStatus.CONFLICT);
		}
		dijagnozaService.deleteDijagnoza(id);
		return ResponseEntity.status(HttpStatus.OK).body("Dijagnoza with that id has been deleted");
	}
}
