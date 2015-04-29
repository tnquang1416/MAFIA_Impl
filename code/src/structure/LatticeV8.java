package structure;

import helper.Timer;
import helper.Trace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import bi_clustering.Cluster;
import data.Data;
import exception.NullArrayException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class LatticeV8 {
	private NodeV6 root;
	//private Map<String, NodeV6> list;
	private Data data;
	private Trace list;

	public LatticeV8(Data input) throws NullArrayException {
		int keyLength = input.getLength();
		NodeV6.setDefaultKey(keyLength);

		this.root = new NodeV6();

		//list = new HashMap<String, NodeV6>();
		list = new Trace();
		list.put(root.getKey(), root);
		this.data = input;
	}

	/*
	 * public void generateByGenes() { System.out.println(this.clusters.size() +
	 * " clusters"); for (Cluster c : this.clusters) System.out.println("--> " +
	 * c.getGenes().toString());
	 * 
	 * for (Cluster c : this.clusters) { Timer watch = new Timer(); NodeV6
	 * newNode = new NodeV6(c.getGenes(), new ArrayList<Integer>());
	 * 
	 * if (!this.list.containsKey(newNode.getKey())) {
	 * this.list.put(newNode.getKey(), newNode);
	 * 
	 * // this.generateFromNode(newNode); this.generateFromNode(new
	 * StringBuilder(newNode.getKey())); } System.out.println("Done cluster " +
	 * this.clusters.indexOf(c) + " with " + watch.stop() + "s"); } }
	 */

	public void generateByGenes() throws NullArrayException {
		//System.out.println(this.data.getCluster().size() + " clusters");

		for (Cluster c : this.data.getCluster()) {
			Timer watch = new Timer();

			this.generateFromNode(c.getGenes());

			System.out.println("Done cluster " + this.data.getCluster().indexOf(c)
					+ " with " + watch.stop() + "s");
		}
	}

	/*
	 * private void generateFromNode(NodeV6 node) { List<Integer> checker =
	 * node.getHead();
	 * 
	 * for (int i = 0; i < checker.size(); i++) { List<Integer> temp = new
	 * ArrayList<>(); temp.addAll(checker); temp.remove(i);
	 * 
	 * NodeV6 newNode = new NodeV6(temp, new ArrayList<Integer>());
	 * 
	 * if (this.list.containsKey(newNode.getKey()))
	 * this.list.get(newNode.getKey()).addTail(checker.get(i)); else {
	 * newNode.addTail(checker.get(i)); this.list.put(newNode.getKey(),
	 * newNode);
	 * 
	 * this.generateFromNode(newNode); } } }
	 */

	private void generateFromNode(StringBuilder key) {
		NodeV6 tempNode = null;
		// List<Integer> temp = new ArrayList<>();

		for (int i : this.list.get(key.toString()).getHead()) {
			key.setCharAt(i, '0');

			// NodeV6 newNode = new NodeV6(temp, new ArrayList<Integer>());

			tempNode = this.list.get(key.toString());
			if (tempNode != null)
				// System.out.println("I have one more tail in node " +
				// tempNode.toString());
				tempNode.addTail(i);
			// tempNode.getTail();
			// return;
			else {
				NodeV6 newNode = new NodeV6(key.toString());
				newNode.addTail(i);
				this.list.put(key.toString(), newNode);

				System.out.println("--> Size: " + this.list.size());

				this.generateFromNode(key);
			}

			key.setCharAt(i, '1');
		}
	}

	private void generateFromNode(List<Integer> head) {
		// System.out.println(head + " and ");
		StringBuilder key = NodeV6.getTempKey(head);

		if (this.list.containsKey(key.toString()))
			return;
		else {
			// System.out.println("--> store");
			NodeV6 node = new NodeV6(head);
			List<Integer> temp = new ArrayList<>();

			this.storeNode(node);

			for (int i = 0; i < head.size(); i++) {
				temp.clear();
				temp.addAll(head);
				temp.remove(i);

				this.generateFromNode(temp, head.get(i));
			}
		}
	}

	private void generateFromNode(List<Integer> head, int tail) {
		// System.out.println(head + " and " + tail);
		StringBuilder key = NodeV6.getTempKey(head);
		NodeV6 tempNode = this.list.get(key.toString());
		if (tempNode != null) {
			// System.out.println("--> add" + tail + " to " + head);
			tempNode.addTail(tail);
		} else {
			// System.out.println("--> store");
			NodeV6 node = new NodeV6(head);
			node.addTail(tail);
			List<Integer> temp = new ArrayList<>();

			this.storeNode(node);

			for (int i = 0; i < head.size(); i++) {
				temp.clear();
				temp.addAll(head);
				temp.remove(i);

				this.generateFromNode(temp, head.get(i));
			}
		}
	}

	public NodeV6 getRoot() {
		return root;
	}

	public NodeV6 getNode(String key) {
		return this.list.get(key);
	}

	public int getTotalNoNode() {
		return this.list.size();
	}

	@Override
	public String toString() {
		String rs = "";

		System.out.println("Total nodes: " + this.list.size());
		/*
		 * rs += root.getKey(); NodeV6 checkPoint = root; String sub =
		 * checkPoint.getLeftMostKey();
		 * 
		 * do { rs += "\n--> " + sub;
		 * 
		 * checkPoint = this.list.get(sub); sub = checkPoint.getLeftMostKey(); }
		 * while (!sub.equals(root.getKey()));
		 */

		for (Entry<String, NodeV6> entry : this.list.entrySet()) {
			System.out.println(entry.getValue().toString());
			try {
				System.out.println("--> " + entry.getKey() + ": "
						+ this.getSupport(entry.getValue()));
			} catch (NullArrayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Total nodes: " + this.list.size());

		return rs;
	}

	public int getSupport(NodeV6 node) throws NullArrayException {
		int supp = 0;

		for (Cluster c : this.data.getCluster())
			if (c.getGenes().containsAll(node.getHead()))
				supp += c.getChips().size();

		return supp;
	}

	private void storeNode(NodeV6 node) {
		this.list.put(node.getKey(), node);
	}

	/**
	 * 
	 * @param node
	 * @return list of subnode of node, list is empty if we have no subnode
	 */
	public List<NodeV6> getAllSubNodes(NodeV6 node) {
		List<NodeV6> nodes = new ArrayList<>();

		for (String s : node.getAllSubKeys())
			nodes.add(this.list.get(s));

		return nodes;
	}
}
