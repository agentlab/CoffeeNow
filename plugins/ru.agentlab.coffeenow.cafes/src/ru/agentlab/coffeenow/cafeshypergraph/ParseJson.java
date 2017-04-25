/**
 *
 */
package ru.agentlab.coffeenow.cafeshypergraph;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author V
 *
 */

@Component
public class ParseJson {

	public ParseJson(){}

	public List<Cafes> makerOfCafeList(){
		ObjectMapper mapper = new ObjectMapper();
		List<Cafes> list = new ArrayList<Cafes>();

		try{
			list = mapper.readValue(new File("data-2883-2016-11-23.json") ,new TypeReference<List<Cafes>>(){});

		}catch(IOException e){
			e.printStackTrace();
		}
		return list;
	}
}
