package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Odeljenje implements Serializable {
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ODELJENJE_ID_GENERATOR")
	@SequenceGenerator(name="ODELJENJE_ID_GENERATOR", sequenceName = "ODELJENJE_SEQ", allocationSize = 1)
	private int id;
	
	private String naziv;
	
	private String lokacija;
	
	@ManyToOne
	@JoinColumn(name="klinika")
	private Bolnica klinika;
	
	@JsonIgnore
	@OneToMany(mappedBy="odeljenje")
	private List<Pacijent> pacijenti; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public Bolnica getKlinika() {
		return klinika;
	}

	public void setKlinika(Bolnica klinika) {
		this.klinika = klinika;
	}

	public List<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(List<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}
	
	
}
