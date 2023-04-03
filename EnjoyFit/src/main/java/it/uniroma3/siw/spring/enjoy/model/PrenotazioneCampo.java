package it.uniroma3.siw.spring.enjoy.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name = "prenotazioni")
public class PrenotazioneCampo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate data;
	@Column(nullable = false)
	private FasciaOraria orario;
	
	@Column(nullable = false)
	private int numeroPersone;
	@Column(nullable = false)
	private String commento;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	private TipologiaCampo tipoCampo;
	@ManyToOne
	@JoinColumn(name = "utenti_id")
	private Cliente cliente;
	
	/**
	@ManyToOne
	@JoinColumn(name = "slotcalendario_id")
	private Slot slot;**/
	
	/**
	 * verifica che la prenotazione non sia già passata
	 * @return boolean
	 */
	public boolean isCorrente() {
		//DateTimeFormatter formatter =DateTimeFormatter.ofPattern("HH:mm");
		LocalTime ora = LocalTime.now();
		LocalTime oraPren = LocalTime.parse(this.orario.getOraMax());
		LocalDate oggi = LocalDate.now();
		if (this.data.isEqual(oggi)) {
			return ora.isBefore(oraPren);
		}
		return this.data.isAfter(oggi);
	}
	
	// della prenotazione è possibile modificare solo data ed ora
	public void updatePrenotazione(PrenotazioneCampo p) {
		this.orario = p.getOrario();
		this.data = p.getData();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public FasciaOraria getOrario() {
		return orario;
	}

	public void setOrario(FasciaOraria orario) {
		this.orario = orario;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public TipologiaCampo getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(TipologiaCampo tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
