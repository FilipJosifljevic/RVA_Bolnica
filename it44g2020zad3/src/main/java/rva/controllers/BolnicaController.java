package rva.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Bolnica;
import rva.services.BolnicaService;

@RestController
public class BolnicaController {

	@Autowired
	private BolnicaService bolnicaService;
	
	@GetMapping("bolnica")
	public List<Bolnica> getAllBolnica(){
		return bolnicaService.getAllBolnica();
	}
	@GetMapping("bolnica/{id}")
	public Optional<Bolnica> getBolnicaById(@PathVariable("id") int id){
		return bolnicaService.getBolnicaById(id);
	}
	
	@GetMapping("bolnicaNaziv/{naziv}")
	public List<Bolnica> getBolnicaByNaziv(@PathVariable("naziv") String nazivArtikla){
		return bolnicaService.getBolnicaByNaziv(nazivArtikla);
	}
	@GetMapping("bolnicaNazivPocetak/{pocetakNaziva}")
	public List<Bolnica> getBolnicaByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		return bolnicaService.getBolnicaByPocetnoSlovo(pocetakNaziva);
	}
}
