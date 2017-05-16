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
	public int getAction(String c){
		int sum = 0;
		 CharSequence a = new String("Дабл Капучино + торт «Москва»");
		if(c.contains(a)){
			sum += 141;
		}
		 CharSequence b = new String("Дабл Капучино + Дабл Капучино «стандарт»");
		if(c.contains(b)){
			sum += 265;
		}
		 CharSequence d = new String("Дабл Капучино Айс + Дабл Капучино Айс");
		if(c.contains(d)){
			sum += 275;
		}
		 CharSequence e = new String("Дабл Капучино сливочный + Дабл Капучино сливочный");
		if(c.contains(e)){
			sum += 191;
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
