package structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author TRAN Nhat Quang
 *
 */
public class LatticeV1 {
	private Map<List<Integer>, NodeV2> nodes;
	private CountingTable table;
	private NodeV2 root;
	
	public LatticeV1(String[] input)
	{
		nodes = new HashMap<>();
		this.table = new CountingTable(input);
		
		// add node root
		root = new NodeV2();
		
		nodes.put(new ArrayList<Integer>(), root);
	}
	
	public void generateNode()
	{
		Map<List<Integer>, Integer> itemSet = new HashMap<>();
		itemSet.putAll(table.getAllSetInt());
		
		for (Entry<List<Integer>, Integer> entry: itemSet.entrySet())
			this.addNode(new NodeV2(entry.getKey(), new ArrayList<Integer>(), entry.getValue()));
	}
	
	public void addNode (NodeV2 in)
	{
		if (this.getNode(in.getHead()) == null)
			nodes.put(in.getHead(), in);
		else
		{
			this.getNode(in.getHead()).setSupport(in.getSupport());
		}
		
		for (NodeV2 sub: this.getSubNodes(in, in.getSupport()))
			this.addNode(sub);
	}
	
	private List<NodeV2> getSubNodes(NodeV2 superNode, int supp)
	{
		List<NodeV2> rsList = new ArrayList<>();
		
		if (superNode.getHead().size() == 1)
		{
			List<Integer> tail = new ArrayList<>();
			tail.addAll(superNode.getTail());
			tail.add(superNode.getHead().get(0));
			rsList.add(new NodeV2(new ArrayList<Integer>(), tail, supp));
		}
		else if(superNode.getHead().size() > 1)
		{
			List<Integer> head = superNode.getHead();
			List<Integer> tail = superNode.getTail();
			
			for (int i=0; i < head.size(); i++)
			{
				List<Integer> tempHead = new ArrayList<>();
				List<Integer> tempTail = new ArrayList<>();
				tempHead.addAll(head);
				tempHead.remove(i);
				tempTail.addAll(tail);
				tempTail.add(head.get(i));
				
				rsList.add(new NodeV2(tempHead, tempTail, supp));
			}
		}
		
		return rsList;
	}
	
	public NodeV2 getNode(List<Integer> head)
	{
		return nodes.get(head);
	}
	
	public Map<List<Integer>, NodeV2> getNodes()
	{
		return nodes;
	}
}
