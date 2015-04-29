package structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exception.DuplicatedNode;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NodeV5 implements Node {
	private static int defaultKeyLength = 0;
	private String key;
	private int support;
	private List<NodeV5> childs;

	public NodeV5() {
		key = "";
		support = 0;
		childs = new ArrayList<>();

		while (key.length() < NodeV5.defaultKeyLength)
			key += "0";
	}

	public NodeV5(String key, int support, List<NodeV5> childs) {
		this.key = key;
		this.support = support;
		this.childs = new ArrayList<>();
		this.childs.addAll(childs);
	}

	public static void setDefaultKeyLength(int i) {
		NodeV5.defaultKeyLength = i;
	}

	public String getKey() {
		return this.key;
	}

	public int getSupport() {
		int rs = 0;
		List<NodeV5> list = new ArrayList<>();
		this.getSupportedNode(list);

		for (NodeV5 node : list) {
			rs += node.support;
		}

		return support + rs;
	}

	private void getSupportedNode(List<NodeV5> rs) {
		for (NodeV5 node : childs) {
			if (node.support > 0)
				if (!rs.contains(node))
					rs.add(node);

			node.getSupportedNode(rs);
		}
	}

	public void setSupport(int s) {
		support += s;
	}

	public void addChilds(NodeV5 node) throws DuplicatedNode {
		if (this.getAllChilds().contains(node))
			throw new DuplicatedNode();

		this.childs.add(node);
	}

	public NodeV5 getChild(String key) {
		for (NodeV5 n : this.childs)
			if (n.getKey().equals(key))
				return n;

		return null;
	}

	public List<NodeV5> getAllChilds() {
		return this.childs;
	}

	public boolean removeChild(NodeV5 node) {
		return this.getAllChilds().remove(node);
	}

	public NodeV5 removeChild(int index) {
		return this.getAllChilds().remove(index);
	}

	public Iterator<String> getAllSupKey() {
		List<String> rs = new ArrayList<>();

		for (int i = 0; i < this.getKey().length(); i++)
			if (this.getKey().charAt(i) == '1') {
				StringBuilder temp = new StringBuilder(this.getKey());
				temp.setCharAt(i, '0');

				rs.add(temp.toString());
			}

		return rs.iterator();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NodeV5)
			return this.key.equals(((NodeV5) o).getKey());

		return false;
	}

	/**
	 * Print a branch of lattice
	 */
	@Override
	public String toString() {
		// System.out.print("[" + this.key + "," + this.getLevel() + "]");
		System.out.print(this.key);

		/*
		 * for (NodeV5 node : childs) { System.out.print("\n-->");
		 * node.toString(); }
		 */

		if (this.childs.size() > 2) {
			System.out.print("\n-->");
			this.childs.get(2).toString();
		} else if (this.childs.size() > 1) {
			System.out.print("\n-->");
			this.childs.get(1).toString();
		} else if (this.childs.size() > 0) {
			System.out.print("\n-->");
			this.childs.get(0).toString();
		}

		return "";
	}

	public int getLevel() {
		int lv = 0;

		for (int i = 0; i < key.length(); i++)
			if (key.charAt(i) == '1')
				lv++;

		return lv;
	}

	public List<Integer> getHead() {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < this.getKey().length(); i++)
			if (this.getKey().charAt(i) == '1')
				list.add(i);

		return list;
	}

	public String getHUT() {
		StringBuilder rs = new StringBuilder("");
		int pos = 0;

		while (pos < NodeV5.defaultKeyLength) {
			rs.append('0');

			for (NodeV5 node : this.childs)
				if (node.getKey().charAt(pos) == '1') {
					rs.setCharAt(pos, '1');
					break;
				}

			pos++;
		}

		return rs.toString();
	}

	public int getLeftMostIndexNode() {
		int tempIndex = -1;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < this.childs.size(); i++) {
			List<Integer> temp = this.getAllChilds().get(i).getHead();
			temp.removeAll(this.getHead());

			if (min > temp.get(0)) {
				min = temp.get(0);
				tempIndex = i;
			}
		}

		return tempIndex;
	}

	/**
	 * 
	 * @return null if node does not have child
	 */
	public NodeV5 getLeftMostNode() {
		int index = this.getLeftMostIndexNode();
		
		if (index >= 0)
			return this.getAllChilds().get(index);
		
		return null;
	}
}
