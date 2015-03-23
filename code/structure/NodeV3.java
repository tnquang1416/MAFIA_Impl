package structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.WrongPositionException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NodeV3 implements Node{
	private static int maxKeyLength = 10000;
	private Boolean[] key = new Boolean[maxKeyLength];
	private int support;
	private List<NodeV3> child;

	public NodeV3() {
		Arrays.fill(key, false);
		support = 0;
		child = new ArrayList<>();
	}

	public NodeV3(Boolean[] key, int supp) {
		this.key = key;
		this.support = supp;
		child = new ArrayList<>();
	}

	public Boolean[] getKey() {
		return this.key;
	}

	public void updateKey(int pos) {
		key[pos] = true;
	}

	public int getSupport() {
		int rs = 0;

		for (NodeV3 node : child)
			rs += node.getSupport();

		return rs + this.support;
	}
	
	public void setSupport(int i)
	{
		this.support += i;
	}

	public List<NodeV3> getChild() {
		return this.child;
	}

	public void addChild(NodeV3 child) throws WrongPositionException {
		if (this.getLevel(child.getKey()) - this.getLevel(this.getKey()) != 1)
			throw new WrongPositionException(this);
		
		this.child.add(child);
	}

	public List<NodeV3> trace(Boolean[] key) {
		int level = this.getLevel(key);
		List<NodeV3> rs = new ArrayList<>();

		try {
			rs.addAll(trace(key, level, 0));
			rs.add(this);
		} catch (Exception ex) {
			return null;
		}

		return rs;
	}

	private List<NodeV3> trace(Boolean[] key, int level, int count) {
		if (count > level)
			return null;

		List<NodeV3> list = new ArrayList<>();

		for (NodeV3 node : this.getChild()) {
			if (node.getKey().equals(key)) {
				list.add(node);
				return list;
			}

			List<NodeV3> temp = node.trace(key, level, count + 1);

			if (temp != null) {
				temp.add(this);
				return temp;
			}
		}

		return null;
	}

	private int getLevel(Boolean[] key) {
		int count = 0;

		for (Boolean value : key)
			if (value)
				count++;

		return count;
	}

	public static void setKeySize(int size) {
		NodeV3.maxKeyLength = size;
	}

	public List<Boolean[]> getSupperNodeKeySet() {
		List<Boolean[]> list = new ArrayList<>();

		for (int i = 0; i < this.key.length; i++) {
			if (this.key[i]) {
				Boolean[] temp = this.key.clone();
				temp[i] = false;

				list.add(temp);
			}
		}
		return list;
	}

	public NodeV3 getNode(Boolean[] key) {
		if (this.key.equals(key))
			return this;
		
		return this.getNode(key, this.getLevel(key), 0);
	}
	
	private NodeV3 getNode(Boolean[] key, int level, int count)
	{
		return null;
	}
	
	@Override
	public boolean equals(Object node)
	{
		if (node instanceof NodeV3)
			return Arrays.deepEquals(this.getKey(), ((NodeV3) node).getKey());
		
		return false;
	}
}
