package it.uniroma3.siw.spring.enjoy.model;

public enum FasciaOraria {
	
	DIECI("10-12", "10:00"),
	DODICI("12-14", "12:00"),
	QUATTORDICI("14-16", "14:00"),
	DICIOTTO("18-20", "18:00"),
	VENTI("20-22", "20:00"),
	VENTIDUE("22-24", "22:00");
	
	private String displayValue;
	private String oraMax;

	private FasciaOraria(String string, String oraMax) {
		this.displayValue = string;
		this.oraMax = oraMax;
	}
	
	public String getValue() {
		return this.displayValue;
	}
	
	public String getOraMax() {
		return this.oraMax;
	}

}
