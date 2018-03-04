package br.ind.seat.util;

import java.util.Comparator;

import br.ind.seat.model.Input;

public class CustomComparator implements Comparator<Input> {

	@Override
	public int compare(Input o1, Input o2) {
		// TODO Auto-generated method stub
		return o1.getEmissao().compareTo(o2.getEmissao());
	}

}
