package structure;

import helper.Timer;
import helper.Trace;

import java.util.ArrayList;
import java.util.List;

import bi_clustering.BiMax;
import bi_clustering.Cluster;
import data.Data;
import exception.NullArrayException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class LatticeV9 {
	private NodeV7 root;
	private List<Cluster> clusters;
	//private Trace list;

	public LatticeV9(Data input) throws NullArrayException {
		int keyLength = input.getLength();
		NodeV7.setDefaultKey(keyLength);

		this.root = new NodeV7();
		this.clusters = new ArrayList<>();
		this.clusters.addAll(BiMax.doBiMax(input.getFinalData()));
		//list = new Trace();
	}

	public void generateByGenes() {
		System.out.println(this.clusters.size() + " clusters");
		//for (Cluster c : this.clusters)
			//System.out.println("--> " + c.getGenes().toString());

		for (Cluster c : this.clusters) {
			Timer watch = new Timer();

			this.generateFromNode(c.getGenes());

			System.out.println("Done cluster " + this.clusters.indexOf(c)
					+ " with " + watch.stop() + "s");
		}
	}

	private void generateFromNode(List<Integer> head) {
		 NodeV7 node = this.root.getNode(head);
		//NodeV7 node = this.list.get(head);
		if (node == null) {
			// System.out.println("--> create" + head);
			node = new NodeV7(head);
			//this.list.put(node.getKey(), node);

			List<Integer> temp = new ArrayList<>();

			for (int i = 0; i < head.size(); i++) {
				temp.addAll(head);
				temp.remove(i);

				this.generateFromNode(temp, node);
				temp.clear();
			}
		}
	}

	private void generateFromNode(List<Integer> head, NodeV7 subNode) {
		//System.out.println("Get " + head);
		 NodeV7 node = this.root.getNode(head);
		//NodeV7 node = this.list.get(NodeV7.getTempKey(head));
		
		if (node == null) {
			// System.out.println("--> create" + head);
			node = new NodeV7(head);
			node.addTail(subNode);
			//this.list.put(node.getKey(), node);

			List<Integer> temp = new ArrayList<>();

			for (int i = 0; i < head.size(); i++) {
				temp.addAll(head);
				temp.remove(i);

				this.generateFromNode(temp, node);
				temp.clear();
			}
		} else {
			// System.out.println("--> update" + head);
			node.addTail(subNode);
			return;
		}
	}

	public NodeV7 getRoot() {
		return root;
	}

	public NodeV7 getNode(List<Integer> subHead) {
		return root.getNode(subHead);
	}

	@Override
	public String toString() {
		String rs = "";

		return rs;
	}

	public int getSupport(NodeV6 node) {
		int supp = 0;

		for (Cluster c : this.clusters)
			if (c.getGenes().containsAll(node.getHead()))
				supp += c.getChips().size();

		return supp;
	}

	/**
	 * 
	 * @param node
	 * @return list of subnode of node, list is empty if we have no subnode
	 */
	public List<NodeV7> getAllSubNodes(NodeV7 node) {
		return node.getAllSubNodes();
	}
}
