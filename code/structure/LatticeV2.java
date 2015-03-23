package structure;

import helper.Timer;

import java.util.ArrayList;
import java.util.List;

import exception.WrongPositionException;

public class LatticeV2 implements Lattice{
	private CountingTable table;
	private NodeV3 root;

	public LatticeV2(String[] input) {
		table = new CountingTable(input);
		NodeV3.setKeySize(table.getKeySize());
		root = new NodeV3();
	}

	public void addNode(Node node) throws WrongPositionException {
		List<Boolean[]> keySets = new ArrayList<>();
		keySets.addAll(((NodeV3) node).getSupperNodeKeySet());
		
		for (Boolean[] key : keySets) {
			NodeV3 temp = root.getNode(key);

			if (temp == null) {
				NodeV3 newTemp = new NodeV3(key, 0);
				newTemp.addChild((NodeV3) node);
				this.addNode(newTemp);
			} else {
				temp.addChild((NodeV3) node);
			}
		}
	}

	public void init() throws WrongPositionException {
		// get list of key
		// create new node

		// add node to lattice
		int i=0;
		for(NodeV3 b: generateFromTable())
		{
			Timer time = new Timer();
			i++;
			System.out.println("--> Add node " + i);
			this.addNode(b);
			System.out.println("This node take " + time.stop() + "s");
		}
	}

	private List<NodeV3> generateFromTable() {
		List<NodeV3> rs = new ArrayList<>();

		for(Boolean[] array: table.getAllItemSetBoolean())
		{
			NodeV3 newNode = new NodeV3(array, 1);
			int i = rs.indexOf(newNode);
			if(i>-1)
				rs.get(i).setSupport(1);
			else
				rs.add(newNode);
		}
		
		return rs;
	}
}
