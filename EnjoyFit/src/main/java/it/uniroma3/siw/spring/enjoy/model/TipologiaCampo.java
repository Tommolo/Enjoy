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


@Entity
@Table(name = "tipoCampo")

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
	public TipologiaCampo() {
		
	}
	 @Transient
	    public String getPhotosImagePath() {
	        if (foto.equals(null) || id.equals(null)) return null;
	         
	        return "/uploadable/tipoCampi/" + id + "/" + foto;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<PrenotazioneCampo> getCampi() {
		return campi;
	}

	public void setCampi(List<PrenotazioneCampo> campi) {
		this.campi = campi;
	}
	
	

}
