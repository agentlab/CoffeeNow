/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;

/**
 * @author V
 *
 */
public class CoffeHouse {
	private Boolean mastercard;
	private Boolean aboniment;
	public CoffeHouse(){}
	public int getAction(String a, String b){
		String c = a+" + "+b;
		switch(c){
		case "Дабл Капучино + торт «Москва»":
			return 141;
		case "Дабл Капучино + Дабл Капучино «стандарт»":
			return 265;
		case "Дабл Капучино Айс + Дабл Капучино Айс":
			return 275;
		case "Дабл Капучино сливочный + Дабл Капучино сливочный":
			return 191;
		}
		return 0;
	}
	public int flag_card(){
		if(mastercard == true){
			return 10;
		}
		else{
			return 0;
		}
	}
}
