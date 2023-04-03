package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rva.models.Bolnica;
import rva.models.Odeljenje;

@Repository
public interface OdeljenjeRepository extends JpaRepository<Odeljenje, Integer>{
	
	List<Odeljenje> findByNazivContainingIgnoreCase(String naziv);
	List<Odeljenje> findByKlinika(Bolnica klinika);
	
	
	@Query(value="SELECT * FROM odeljenje WHERE LOWER(naziv) LIKE :pocetak%", nativeQuery = true)
	List<Odeljenje> getByPocetak(@Param("pocetak") String pocetakNaziva);
	
	
	@Query(value = "select * from odeljenje where klinika = ?1", nativeQuery = true)
    Integer nextBolnica(int bolnicaId);
	
}
