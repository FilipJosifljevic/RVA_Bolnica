package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Bolnica;

public interface BolnicaRepository extends JpaRepository<Bolnica, Integer>{

	List<Bolnica> findByNazivContainingIgnoreCase(String naziv);
	
	@Query(value="SELECT * FROM bolnica WHERE LOWER(naziv) LIKE :pocetak%", nativeQuery = true)
	List<Bolnica> getByPocetak(@Param("pocetak") String pocetakNaziva);
}
