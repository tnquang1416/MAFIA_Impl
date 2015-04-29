package structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NodeV7 {
	private static String defaultKey = "0000000000";
	private List<Integer> head;
	private List<NodeV7> sub;

	public NodeV7() {
		this.head = new ArrayList<>();
		this.sub = new ArrayList<>();
	}

	public NodeV7(List<Integer> head) {
		this.head = new ArrayList<>();
		this.sub = new ArrayList<>();
		this.head.addAll(head);
	}

	public NodeV7(List<Integer> head, List<NodeV7> tail) {
		this.head = new ArrayList<>();
		this.sub = new ArrayList<>();
		this.head.addAll(head);
		this.sub.addAll(tail);
	}

	public NodeV7(String key) {
		this.head = new ArrayList<>();
		this.sub = new ArrayList<>();

		for (int i = 0; i < key.length(); i++)
			if (key.charAt(i) == '1')
				this.head.add(i);
	}

	public static void setDefaultKey(int i) {
		NodeV7.defaultKey = "";
		for (int j = 0; j < i; j++)
			NodeV7.defaultKey += "0";
	}

	public String getKey() {
		StringBuilder temp = new StringBuilder(NodeV7.defaultKey);

		for (int i : this.getHead())
			temp.setCharAt(i, '1');

		return temp.toString();
	}

	public void addTail(NodeV7 tail) {
		if (!this.sub.contains(tail))
			this.sub.add(tail);
	}

	public List<NodeV7> getAllSubNodes() {
		return this.sub;
	}

	public void removeTail(int index) {
		this.sub.remove(index);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NodeV7)
			return this.getKey().equals(((NodeV7) o).getKey());

		return false;
	}

	/**
	 * Print a branch of lattice
	 */
	@Override
	public String toString() {
		String rs = "";
		List<NodeV7> temp = new ArrayList<>();
		this.getAllNodesInBranch(temp);

		rs += "--------------------------------\n";
		rs += this.getKey();

		for (NodeV7 node : temp) {
			rs += "\n--------------------------------\n";
			rs += node.getKey();
		}

		return rs;
	}

	public void getAllNodesInBranch(List<NodeV7> rs) {
		for (NodeV7 node : this.sub)
			if (!rs.contains(node)) {
				rs.add(node);
				node.getAllNodesInBranch(rs);
			}
	}

	public int getLevel() {
		return this.head.size();
	}

	public List<Integer> getHead() {
		return this.head;
	}

	public String getHUT() {
		StringBuilder rs = new StringBuilder(this.getKey());
		List<Integer> temp = this.getTails();

		for (int i : temp)
			rs.setCharAt(i, '1');

		return rs.toString();
	}

	/**
	 * return key of the left most node
	 * 
	 * @return default key if do not have child
	 */
	public String getLeftMostKey() {
		int min = Integer.MAX_VALUE;

		for (int i : this.getTails())
			if (min > i)
				min = i;

		if (min != Integer.MAX_VALUE) {
			StringBuilder rs = new StringBuilder(this.getKey());
			rs.setCharAt(min, '1');
			return rs.toString();
		}

		return NodeV7.defaultKey;
	}

	public static StringBuilder getTempKey(List<Integer> head) {
		StringBuilder rs = new StringBuilder(NodeV7.defaultKey);

		for (int i : head)
			rs.setCharAt(i, '1');

		return rs;
	}

	public List<String> getAllSubKeys() {
		List<String> keys = new ArrayList<>();

		for (NodeV7 node : this.getAllSubNodes())
			keys.add(node.getKey());

		return keys;
	}

	private List<Integer> getTails() {
		List<Integer> rs = new ArrayList<>();
		List<Integer> temp;

		for (NodeV7 node : this.sub) {
			temp = node.getHead();
			temp.removeAll(this.getHead());

			rs.addAll(temp);
		}

		return rs;
	}

	/**
	 * get subnode in branch
	 * 
	 * @param subHead
	 * @return null if cannot found subnode
	 */
	public NodeV7 getNode(List<Integer> subHead) {
		// System.out.println("Compare " + this.head + "and" + subHead);
		// System.out.println("Compare " + this.head.size() + "and" +
		// subHead.size());
		if (subHead.containsAll(this.head)) {
			if (this.head.size() < subHead.size())
				for (NodeV7 node : this.sub) {
					NodeV7 temp = node.getNode(subHead);
					if (temp != null)
						return temp;
				}
			else if (this.head.size() == subHead.size())
				return this;
		}

		return null;
	}
}
