package it.uniroma3.siw.spring.enjoy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipoCampo")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @NoArgsConstructor
public class TipologiaCampo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(length=5096)
	private String descrizione;
	@Column (nullable=false)
	private int prezzo;
	@Column(nullable = false)
	private int numeroPersone;
	@Column(nullable = true)
	private String foto;
	
	@OneToMany(mappedBy = "tipoCampo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PrenotazioneCampo> campi;
	
	public TipologiaCampo(String n, String d) {
		this.nome = n;
		this.descrizione = d;
	}
	
	 @Transient
	    public String getPhotosImagePath() {
	        if (foto.equals(null) || id.equals(null)) return null;
	         
	        return "/uploadable/tipoCampi/" + id + "/" + foto;
	    }
	
	

}
