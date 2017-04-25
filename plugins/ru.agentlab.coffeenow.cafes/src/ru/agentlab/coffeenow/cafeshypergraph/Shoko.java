/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;

/**
 * @author V
 *
 */
public class Shoko {
	private Boolean mastercard;
	private Boolean maestro;
	private Boolean aboniment;
	Shoko(){}

	/**
	 * @param mastercard the mastercard to set
	 */
	public void setMastercard(Boolean mastercard) {
		this.mastercard = mastercard;
	}

	/**
	 * @param maestro the maestro to set
	 */
	public void setMaestro(Boolean maestro) {
		this.maestro = maestro;
	}



	/**
	 * @param aboniment the aboniment to set
	 */
	public void setAboniment(Boolean aboniment) {
		this.aboniment = aboniment;
	}

	public int flag_card(){
		if((mastercard == true) || (maestro == true)){
			return 10;
		}
		else{
			return 0;
		}
	}

}
