package ca.uds.gestion_du_dossier_de_recherche.ventilation.strategie;

import java.util.List;

import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilable;
import ca.uds.gestion_du_dossier_de_recherche.ventilation.Ventilation;

public abstract class StrategieTrie {

	private Ventilation ventilation;

	public abstract List<Ventilable> ventiler(List<? extends Ventilable> ventilables);

	public final void setContext(Ventilation ventilation)
	{
		this.setVentilation(ventilation);
	}

	public Ventilation getVentilation() {
		return ventilation;
	}

	public void setVentilation(Ventilation ventilation) {
		this.ventilation = ventilation;
	}

	
}
