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
	private String aboniment_type;
	public Shoko(){}

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
	public int flagAboniment(String order){
		if(aboniment){
			if(aboniment_type  == "BLACK10"){
				if(order.contains("Эспрессо")){
					return 39;
				}
				else if(order.contains("Американо")){
					return 69;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 39;
				}
			}
			if(aboniment_type  == "BLACK15"){
				if(order.contains("Эспрессо")){
					return 47;
				}
				else if(order.contains("Американо")){
					return 77;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 47;
				}
			}
			if(aboniment_type  == "BLACK30"){
				if(order.contains("Эспрессо")){
					return 60;
				}
				else if(order.contains("Американо")){
					return 90;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 60;
				}
			}
			if(aboniment_type  == "WHITE10"){
				if(order.contains("Эспрессо")){
					return 19;
				}
				else if(order.contains("Американо")){
					return 49;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 19;
				}
				if(order.contains("Капучино")){
					return 99;
				}
				else if(order.contains("Капучино Light")){
					return 99;
				}
				else if(order.contains("Капучино Strong")){
					return 129;
				}
				else if(order.contains("Латте классический")){
					return 99;
				}
			}
			if(aboniment_type  == "WHITE15"){
				if(order.contains("Эспрессо")){
					return 24;
				}
				else if(order.contains("Американо")){
					return 54;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 24;
				}
				if(order.contains("Капучино")){
					return 104;
				}
				else if(order.contains("Капучино Light")){
					return 104;
				}
				else if(order.contains("Капучино Strong")){
					return 134;
				}
				else if(order.contains("Латте классический")){
					return 104;
				}
			}
			if(aboniment_type  == "WHITE30"){
				if(order.contains("Эспрессо")){
					return 45;
				}
				else if(order.contains("Американо")){
					return 75;
				}
				else if(order.contains("Свежезаваренный кофе")){
					return 45;
				}
				if(order.contains("Капучино")){
					return 125;
				}
				else if(order.contains("Капучино Light")){
					return 125;
				}
				else if(order.contains("Капучино Strong")){
					return 155;
				}
				else if(order.contains("Латте классический")){
					return 155;
				}
			}
		}
	}

}
