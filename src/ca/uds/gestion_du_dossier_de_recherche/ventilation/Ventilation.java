package ca.uds.gestion_du_dossier_de_recherche.ventilation;

import java.time.LocalDate;
import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie.StrategieTrie;

public class Ventilation {


	StrategieTrie strategie;
	LocalDate date;
	
	
	public Ventilation(StrategieTrie strategie) {
		super();
		setStrategie(strategie);
		this.date = LocalDate.now();
	}
	
	public Ventilation(StrategieTrie strategie, LocalDate date) {
		super();
		setStrategie(strategie);
		this.date = date;
	}

	public final List<? extends Ventilable> ventiler(List<? extends Ventilable> ventilable)
	{
		return strategie.ventiler(ventilable);
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the strategie
	 */
	public StrategieTrie getStrategie() {
		return strategie;
	}

	/**
	 * @param strategie the strategie to set
	 */
	public void setStrategie(StrategieTrie strategie) {
		this.strategie = strategie;
		this.strategie.setContext(this);
		
	}
	
	
	
	
	
	
	
}
