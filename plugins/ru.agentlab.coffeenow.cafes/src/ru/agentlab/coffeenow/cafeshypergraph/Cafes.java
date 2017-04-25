/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;

import org.osgi.service.component.annotations.Component;

/**
 * @author V
 *
 */
@Component
public class Cafes {
	protected String Name;
	protected String Address;

	public Cafes() {
	}

	public Cafes(String cafeName, String adress){
		this.Name = cafeName;
		this.Address = adress;

	}

	public void setcafeName(String cafeName){
		this.Name = cafeName;
	}

	public String getcafeName(){
		return this.Name;
	}

	public void setadress(String adress){
		this.Address = adress;
	}

	public String getadress(){
		return this.Address;
	}
}

