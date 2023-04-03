package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.models.*;

public interface PacijentRepository extends JpaRepository<Pacijent, Integer>{

	List<Pacijent> findByImeContainingIgnoreCase(String ime);
	List<Pacijent> findByPrezimeContainingIgnoreCase(String prezime);
	
	List<Pacijent> findByzdrOsiguranjeTrue();
	
	List<Pacijent> findByOdeljenje(Odeljenje odeljenje);
	
	List<Pacijent> findByDijagnoza(Dijagnoza dijagnoza);
	
	@Query(value = "select * from pacijent where odeljenje = ?1", nativeQuery = true)
    Integer nextOdeljenje(int odeljenjeId);
	
	@Query(value = "select * from pacijent where dijagnoza = ?1", nativeQuery = true)
    Integer nextDijagnoza(int dijagnozaId);
}
