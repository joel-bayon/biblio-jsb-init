package fr.orsys.biblio.dao.memory;

public class SequenceCounter {
	static int sequence = 1000;
	
	public static int getId() {
		return ++sequence;
	}
}
