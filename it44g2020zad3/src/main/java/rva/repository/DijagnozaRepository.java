package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Integer>{

	List<Dijagnoza> findByNazivContainingIgnoreCase(String naziv);
	@Query(value="SELECT * FROM dijagnoza WHERE LOWER(naziv) LIKE :pocetak%", nativeQuery = true)
	List<Dijagnoza> getByPocetak(@Param("pocetak") String pocetakNaziva);
}
