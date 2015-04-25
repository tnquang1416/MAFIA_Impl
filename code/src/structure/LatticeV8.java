package structure;

import helper.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bi_clustering.BiMax;
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
	private Map<String, NodeV6> list;
	private List<Cluster> clusters;

	public LatticeV8(Data input) throws NullArrayException {
		int keyLength = input.getLength();
		NodeV6.setDefaultKey(keyLength);

		this.root = new NodeV6();

		list = new HashMap<String, NodeV6>();
		list.put(root.getKey(), root);
		this.clusters = new ArrayList<>();
		this.clusters.addAll(BiMax.doBiMax(input.getFinalData()));
	}

	public void generateByGenes() {
		System.out.println(this.clusters.size() + " clusters");
		for (Cluster c : this.clusters)
			System.out.println("--> " + c.getGenes().toString());

		for (Cluster c : this.clusters) {
			Timer watch = new Timer();
			NodeV6 newNode = new NodeV6(c.getGenes(), new ArrayList<Integer>());
			this.list.put(newNode.getKey(), newNode);

			this.generateFromNode(newNode);
			System.out.println("Done cluster " + this.clusters.indexOf(c)
					+ " with " + watch.stop() + "s");
		}
	}

	private void generateFromNode(NodeV6 node) {
		List<Integer> checker = node.getHead();

		for (int i = 0; i < checker.size(); i++) {
			List<Integer> temp = new ArrayList<>();
			temp.addAll(checker);
			temp.remove(i);

			NodeV6 newNode = new NodeV6(temp, new ArrayList<Integer>());

			if (this.list.containsKey(newNode.getKey()))
				this.list.get(newNode.getKey()).addTail(checker.get(i));
			else {
				newNode.addTail(checker.get(i));
				this.list.put(newNode.getKey(), newNode);

				this.generateFromNode(newNode);
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
			System.out.println("--> " + entry.getKey() + ": "
					+ this.getSupport(entry.getValue()));
		}

		return rs;
	}

	public int getSupport(NodeV6 node) {
		int supp = 0;

		for (Cluster c : this.clusters)
			if (c.getGenes().containsAll(node.getHead()))
				supp += c.getChips().size();

		return supp;
	}

}
