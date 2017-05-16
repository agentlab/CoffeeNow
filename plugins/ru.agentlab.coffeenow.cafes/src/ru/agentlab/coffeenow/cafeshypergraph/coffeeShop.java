/**
 * 
 */
package ru.agentlab.coffeenow.cafeshypergraph;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;

/**
 * @author V
 *
 */
public class coffeeShop {
	private boolean loyalityCard;
	public coffeeShop(){}
	/**
	 * @return the loyalityCard
	 */
	public boolean isLoyalityCard() {
		return loyalityCard;
	}
	/**
	 * @param loyalityCard the loyalityCard to set
	 */
	public void setLoyalityCard(boolean loyalityCard) {
		this.loyalityCard = loyalityCard;
	}
	
	public int action(String order){
		
		if(loyalityCard == true){
			int sum = 0;
			CharSequence act1 = new String("Лимонад Биттер Лемон (с собой)");
			if(order.contains(act1)){
				sum += 145;
			}
			CharSequence act2 = new String("Черный венский кофе");
			if(order.contains(act2)){
				sum += 100;
			}
			CharSequence act3 = new String("Белый венский кофе");
			if(order.contains(act3)){
				sum += 100;
			}
			CharSequence act4 = new String("Торт «Эстерхази»");
			if(order.contains(act4)){
				sum += 70;
			}
			CharSequence act5 = new String("Свежевыжатый апельсиновый сок");
			if(order.contains(act5)){
				sum += 82;
			}
			CharSequence act6 = new String("Топпинг эспрессо");
			if(order.contains(act6)){
				sum +=18;
			}
			CharSequence act7 =new String("Топпинг мороженное");
			if(order.contains(act7)){
				sum += 18;
			}
		}	
	}
	}
	
}
