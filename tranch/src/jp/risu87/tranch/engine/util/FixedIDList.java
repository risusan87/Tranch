package jp.risu87.tranch.engine.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Unsynchronized dictionary-like linked list
 * @author risusan87
 *
 * @param <ID_type>
 * @param <Value>
 */
public class FixedIDList<ID_type, Value> {
	
	private final List<FixedIDList.Element<ID_type, Value>> elements;
	
	public FixedIDList() {
		this.elements = new ArrayList<FixedIDList.Element<ID_type,Value>>();
	}
	
	public void addElement(ID_type par1word, Value par2meaning) {
		this.elements.add(new Element<ID_type, Value>(par1word, par2meaning));
	}
	
	public Value getElement(ID_type par1word) {
		for (int i = 0; i < this.elements.size(); i++) {
			FixedIDList.Element<ID_type, Value> target = this.elements.get(i);
			if (target.word.equals(par1word))
				return target.meaning;
		}
		return null;
	}
	
	private static class Element<ID_type, Value> {
		public ID_type word;
		public Value meaning;
		public Element(ID_type par1word, Value par2meaning) {
			this.word = par1word;
			this.meaning = par2meaning;
		}
	}
}
