package it.uniroma3.siw.spring.enjoy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @ToString @NoArgsConstructor
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
	
	public void updatePrenotazione(PrenotazioneCampo p) {
		this.orario = p.getOrario();
		this.commento = p.getCommento();
		this.data = p.getData();
		this.numeroPersone = p.getNumeroPersone();
	}

}
