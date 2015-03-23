package structure;

import exception.WrongPositionException;

public interface Lattice {
	public void init() throws WrongPositionException;
	public void addNode(Node n) throws WrongPositionException;
}
