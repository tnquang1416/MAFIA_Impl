package structure;

import helper.ListHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NodeV6 {
	private static String defaultKey = "0000000000";
	private List<Integer> head;
	private List<Integer> tail;

	public NodeV6() {
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();
	}
	
	public NodeV6(List<Integer> head) {
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();
		this.head.addAll(head);
	}

	public NodeV6(List<Integer> head, List<Integer> tail) {
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();
		this.head.addAll(head);
		this.tail.addAll(tail);
	}

	public NodeV6(String key) {
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();

		for (int i = 0; i < key.length(); i++)
			if (key.charAt(i) == '1')
				this.head.add(i);
	}

	public static void setDefaultKey(int i) {
		NodeV6.defaultKey = "";
		for (int j = 0; j < i; j++)
			NodeV6.defaultKey += "0";
	}

	public String getKey() {
		StringBuilder temp = new StringBuilder(NodeV6.defaultKey);

		for (int i : this.getHead())
			temp.setCharAt(i, '1');

		return temp.toString();
	}

	public void addTail(int tail) {
		ListHandle.add(this.tail, tail);
	}

	public List<Integer> getTail() {
		return this.tail;
	}

	public void removeTail(int tail) {
		this.tail.remove(tail);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NodeV6)
			return this.getKey().equals(((NodeV6) o).getKey());

		return false;
	}

	/**
	 * Print a branch of lattice
	 */
	@Override
	public String toString() {
		String rs = "";

		rs += "--------------------------------";
		rs += "\nHead: " + this.getHead().toString();
		rs += "\nTail: " + this.getTail().toString();

		return rs;
	}

	public int getLevel() {
		return this.head.size();
	}

	public List<Integer> getHead() {
		return this.head;
	}

	public String getHUT() {
		StringBuilder rs = new StringBuilder(NodeV6.defaultKey);

		for (int i : this.head)
			rs.setCharAt(i, '1');

		for (int j : this.tail)
			rs.setCharAt(j, '1');

		return rs.toString();
	}

	/**
	 * return key of the left most node
	 * 
	 * @return default key if do not have child
	 */
	public String getLeftMostKey() {
		int min = Integer.MAX_VALUE;

		for (int i : this.tail)
			if (min > i)
				min = i;

		if (min != Integer.MAX_VALUE) {
			StringBuilder rs = new StringBuilder(this.getKey());
			rs.setCharAt(min, '1');
			return rs.toString();
		}

		return NodeV6.defaultKey;
	}

	public static StringBuilder getTempKey(List<Integer> head) {
		StringBuilder rs = new StringBuilder(NodeV6.defaultKey);

		for (int i : head)
			rs.setCharAt(i, '1');

		return rs;
	}
	
	public List<String> getAllSubKeys(){
		List<String> keys = new ArrayList<>();
		StringBuilder temp = new StringBuilder(this.getKey());
		
		for (int i : tail){
			temp.setCharAt(i, '1');
			keys.add(temp.toString());
			temp.setCharAt(i, '0');
		}
		
		return keys;
	}
}
