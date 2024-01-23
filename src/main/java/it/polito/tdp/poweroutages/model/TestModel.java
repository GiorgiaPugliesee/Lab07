package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Nerc> nerc = model.getNercList();
		System.out.println(model.trovaSequenza(nerc.get(1), 4, 100));

	}

}
