package ru.agentlab.maia.planner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ru.agentlab.maia.dsl.Variable;

/**
 * @author <a href="mailto:erickpassos@gmail.com">Erick Passos</a>
 * @author <a href="mailto:saviod2@gmail.com">Sávio Mota</a>
 *
 */
public abstract class Parametized {

	public String name;
	public List<Variable> params = new ArrayList<Variable>();
	public Map<String, Collection<Variable>> typeMap = new HashMap<String, Collection<Variable>>();
	public Map<String, Variable> paramsMap = new HashMap<String, Variable>();

	public void replaceParams(List<Variable> oldNames, List<Variable> newNames) {
		List<Variable> newParams = new ArrayList<Variable>();
		for (Variable p : params) {
			int index = oldNames.indexOf(p);
			Variable newValue = newNames.get(index);
			newParams.add(newValue);
		}
		params = newParams;
	}

	/**
	 * Método criado para possibilitar o suporte a tipos
	 * e para facilitar a substituição dos parâmetros pelos
	 * respectivos objetos
	 *
	 * @author Sávio Mota
	 *
	 */
	public void buildTypeMap(){
		for (Variable parameter : params) {
			//Criação do mapa de tipos
			Collection<Variable> parameters = null;
			if (!typeMap.containsKey(parameter.getType().getName())){
				parameters = new HashSet<Variable>();
			}else{
				parameters = typeMap.get(parameter.getType().getName());
			}

			parameters.add(parameter);
			typeMap.put(parameter.getType().getName(), parameters);

			//Criação do mapa de parâmetros
			paramsMap.put(parameter.getName(), parameter);
		}
	}

}