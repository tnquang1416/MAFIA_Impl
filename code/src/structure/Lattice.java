package structure;

import exception.WrongPositionException;

public interface Lattice {
	public void initGene() throws WrongPositionException;
	public void addNode(Node n) throws WrongPositionException;
	void initChip() throws WrongPositionException;
}
