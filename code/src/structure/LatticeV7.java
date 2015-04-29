package structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.Data;
import exception.DuplicatedNode;
import exception.NullArrayException;
import exception.WrongPositionException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class LatticeV7 implements Lattice {
	private NodeV5 root;
	private Map<String, NodeV5> list;
	private Data data;

	public LatticeV7(Data input) {
		this.data = input;
		int keyLength = this.data.getInfoLength();

		NodeV5.setDefaultKeyLength(keyLength);

		this.root = new NodeV5();

		list = new HashMap<String, NodeV5>();
		list.put(root.getKey(), root);
	}

	@Override
	public void initChip() throws WrongPositionException {
		try {
			Map<String, Integer> itemSet = this.data.getChips();
			System.out.println("--> We have " + itemSet.size() + " itemset");
			for (Entry<String, Integer> entry : itemSet.entrySet())
				this.addNode(new NodeV5(entry.getKey(), entry.getValue(),
						new ArrayList<NodeV5>()));
		} catch (NullArrayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initGene() throws WrongPositionException {
		Map<String, Integer> itemSet;
		try {
			itemSet = this.data.getGenes();
			System.out.println("--> We have " + itemSet.size() + " itemset");
			for (Entry<String, Integer> entry : itemSet.entrySet())
				this.generatedNode(new NodeV5(entry.getKey(), 0, new ArrayList<NodeV5>()));
		} catch (NullArrayException | DuplicatedNode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * generate node, add child if neccessary
	 * 
	 * @param key
	 * @param supp
	 * @throws DuplicatedNode
	 */
	private void generatedNode(NodeV5 c) throws DuplicatedNode {
		this.list.put(c.getKey(), c);
		List<Integer> head = c.getHead();
		String key = c.getKey();

		for (int index : head) {
			StringBuilder builder = new StringBuilder(key);
			builder.setCharAt(index, '0');

			if (this.list.containsKey(builder.toString())) {
				//System.out.println("Node exists");
				this.list.get(builder.toString()).addChilds(c);
			} else {
				NodeV5 newNode = new NodeV5(builder.toString(), 0,
						new ArrayList<NodeV5>());
				newNode.addChilds(c);
				this.generatedNode(newNode);
			}
		}
	}

	@Override
	public void addNode(Node n) throws WrongPositionException {
		if (this.list.containsKey(((NodeV5) n).getKey()))
			return;

		this.list.put(((NodeV5) n).getKey(), (NodeV5) n);

		Iterator<String> supperKeys = ((NodeV5) n).getAllSupKey();

		while (supperKeys.hasNext()) {
			String key = supperKeys.next();
			if (this.list.containsKey(key))
				try {
					this.list.get(key).addChilds((NodeV5) n);
				} catch (DuplicatedNode e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else {
				NodeV5 temp = new NodeV5(key, 0, new ArrayList<NodeV5>());
				try {
					temp.addChilds((NodeV5) n);
				} catch (DuplicatedNode e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.addNode(temp);
			}
		}
	}

	public NodeV5 getRoot() {
		return root;
	}

	public NodeV5 getNode(String key) {
		return this.list.get(key);
	}

	public int getTotalNoNode() {
		return this.list.size();
	}

	@Override
	public String toString() {
		String rs = "";

		rs += root.toString();
		System.out.println(this.list.size());

		return rs;
	}

}
