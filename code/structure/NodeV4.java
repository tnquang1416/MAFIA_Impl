package structure;

import java.util.ArrayList;
import java.util.List;

import exception.DuplicatedNode;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NodeV4 implements Node {
	private static int defaultKeyLength = 0;
	private String key;
	private int support;
	private List<NodeV4> childs;

	public NodeV4() {
		key = "";
		support = 0;
		childs = new ArrayList<>();

		while (key.length() < NodeV4.defaultKeyLength)
			key += "0";
	}

	public NodeV4(String key, int support, List<NodeV4> childs) {
		this.key = key;
		this.support = support;
		this.childs = new ArrayList<>();
		this.childs.addAll(childs);
	}

	public static void setDefaultKeyLength(int i) {
		NodeV4.defaultKeyLength = i;
	}

	public String getKey() {
		return this.key;
	}

	public int getSupport() {
		int rs = 0;
		List<NodeV4> list = new ArrayList<>();
		this.getSupportedNode(list);

		for (NodeV4 node : list) {
			rs += node.support;
		}

		return support + rs;
	}

	private void getSupportedNode(List<NodeV4> rs) {
		for (NodeV4 node : childs) {
			if (node.support > 0)
				if (!rs.contains(node))
					rs.add(node);

			node.getSupportedNode(rs);
		}
	}

	@Override
	public void setSupport(int s) {
		support += s;
	}

	public void addChilds(NodeV4 node) throws DuplicatedNode {
		if (this.getAllChilds().contains(node))
			throw new DuplicatedNode();

		this.childs.add(node);
	}

	public NodeV4 getChild(String key) {
		for (NodeV4 n : this.childs)
			if (n.getKey().equals(key))
				return n;

		return null;
	}

	public List<NodeV4> getAllChilds() {
		return this.childs;
	}

	public List<String> getAllSupKey() {
		List<String> rs = new ArrayList<>();

		for (int i = 0; i < this.getKey().length(); i++)
			if (this.getKey().charAt(i) == '1') {
				StringBuilder temp = new StringBuilder(this.getKey());
				temp.setCharAt(i, '0');

				rs.add(temp.toString());
			}

		return rs;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NodeV4)
			return this.key.equals(((NodeV4) o).getKey());

		return false;
	}

	@Override
	public String toString() {
		//System.out.print("[" + this.key + "," + this.getLevel() + "]");
		System.out.print(this.key);

		for (NodeV4 node : childs) {
			System.out.print("\n-->");
			node.toString();
		}
		
		/*if (this.childs.size() > 2) {
			System.out.print("\n-->");
			this.childs.get(2).toString();
		} else if (this.childs.size() > 1) {
			System.out.print("\n-->");
			this.childs.get(1).toString();
		} else if (this.childs.size() > 0) {
			System.out.print("\n-->");
			this.childs.get(0).toString();
		}*/

		return "";
	}
	
	public int getLevel()
	{
		int lv = 0;
		
		for (int i=0; i< key.length(); i++)
			if (key.charAt(i) == '1')
				lv++;
		
		return lv;
	}
	
	public Integer[] getHead()
	{
		Integer[] rs = null;
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<this.getKey().length(); i++)
			if (this.getKey().charAt(i)=='1')
				list.add(i);
		
		list.toArray(rs);
		
		return rs;
	}
}
