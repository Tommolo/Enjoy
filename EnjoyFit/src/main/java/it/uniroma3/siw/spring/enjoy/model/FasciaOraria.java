package it.uniroma3.siw.spring.enjoy.model;

public enum FasciaOraria {
	
	DIECI("10-12", "11:30"),
	DODICI("12-14", "13:30"),
	QUATTORDICI("14-16", "16:30"),
	DICIOTTO("18-20", "19:30"),
	VENTI("20-22", "21:30"),
	VENTIDUE("22-24", "23:30");
	
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
