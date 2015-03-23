package structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author TRAN Nhat Quang
 *
 */
public class NodeV2 implements Node{
	// this value is used to determine frequent node, default is 2
	public static int generalSupport = 2;
	// head: index of all item on the path to the root: ,1,2,3,
	private List<Integer> head;
	// tail: all possible extension: ,4,5,
	private List<Integer> tail;
	// Support value of a node
	private int support = 0;
	
	public NodeV2()
	{
		head = new ArrayList<>();
		tail = new ArrayList<>();
	}
	
	public NodeV2(List<Integer> head, List<Integer> tail, int supp)
	{
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();
		
		this.head.addAll(head);
		this.tail.addAll(tail);
		this.setSupport(supp);
	}
	
	public NodeV2(int[] heads, int[] tails, int supp)
	{
		this.head = new ArrayList<>();
		this.tail = new ArrayList<>();
		
		this.setHead(heads);
		this.setTail(tails);
		this.setSupport(supp);
	}
	
	// Get and Set information
	public List<Integer> getHead() {
		return head;
	}
	private void setHead(int... head) {
		for (int i: head)
			this.head.add(i);
	}
	public List<Integer> getTail() {
		return tail;
	}
	public void setTail(int... tails) {
		for (int i: tails)
			this.tail.add(i);
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support += support;
	}
	
	public boolean isFrequent()
	{
		return this.getSupport() >= NodeV2.generalSupport;
	}
}
