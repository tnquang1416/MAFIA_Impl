package structure;

import java.util.ArrayList;
import java.util.HashMap;
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
public class LatticeV6 implements Lattice {
	private NodeV4 root;
	private Map<String, NodeV4> list;
	private Data data;

	public LatticeV6(Data input) {
		this.data = input;

		NodeV4.setDefaultKeyLength(this.data.getInfoLength());
		this.root = new NodeV4();

		list = new HashMap<String, NodeV4>();
		list.put(root.getKey(), root);
	}

	@Override
	public void initChip() throws WrongPositionException {
		try {
			Map<String, Integer> itemSet = this.data.getChips();
			System.out.println("--> We have " + itemSet.size() + " itemset");
			for (Entry<String, Integer> entry : itemSet.entrySet())
				this.addNode(new NodeV4(entry.getKey(), entry.getValue(),
						new ArrayList<NodeV4>()));
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
				this.addNode(new NodeV4(entry.getKey(), entry.getValue(),
						new ArrayList<NodeV4>()));
		} catch (NullArrayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addNode(Node n) throws WrongPositionException {
		if (this.list.containsKey(((NodeV4) n).getKey()))
			return;

		this.list.put(((NodeV4) n).getKey(), (NodeV4) n);

		List<String> supperKeys = new ArrayList<>();
		supperKeys.addAll(((NodeV4) n).getAllSupKey());

		for (String key : supperKeys) {
			if (this.list.containsKey(key))
				try {
					// System.out.print("--"
					// + this.list.get(key).getAllChilds().size());
					this.list.get(key).addChilds((NodeV4) n);
					// System.out.println("--"
					// + this.list.get(key).getAllChilds().size());
				} catch (DuplicatedNode e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else {
				NodeV4 temp = new NodeV4(key, 0, new ArrayList<NodeV4>());
				try {
					temp.addChilds((NodeV4) n);
				} catch (DuplicatedNode e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.addNode(temp);
			}
		}
	}

	public NodeV4 getRoot() {
		return root;
	}
	
	public NodeV4 getNode(String key){
		return this.list.get(key);
	}

	public int getTotalNoNode() {
		return this.list.size();
	}

	@Override
	public String toString() {
		String rs = "";

		rs += root.toString();

		return rs;
	}

}
