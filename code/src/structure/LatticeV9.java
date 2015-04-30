package structure;

import helper.Timer;

import java.util.ArrayList;
import java.util.List;

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
	private Data data;

	public LatticeV9(Data input) throws NullArrayException {
		int keyLength = input.getLength();
		NodeV7.setDefaultKey(keyLength);

		this.root = new NodeV7();
		this.data = input;
		// list = new Trace();
	}

	public void generateByGenes() throws NullArrayException {
		System.out.println(this.data.getCluster().size() + " clusters");
		// for (Cluster c : this.clusters)
		// System.out.println("--> " + c.getGenes().toString());

		for (Cluster c : this.data.getCluster()) {
			Timer watch = new Timer();

			this.generateFromNode(c.getGenes());

			System.out.println("Done cluster "
					+ this.data.getCluster().indexOf(c) + " with "
					+ watch.stop() + "s");
		}
	}

	private void generateFromNode(List<Integer> head) {
		NodeV7 node = this.root.getNode(head);
		// NodeV7 node = this.list.get(head);
		if (node == null) {
			// System.out.println("--> create" + head);
			node = new NodeV7(head);
			// this.list.put(node.getKey(), node);

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
		// System.out.println("Get " + head);
		NodeV7 node = this.root.getNode(head);
		// NodeV7 node = this.list.get(NodeV7.getTempKey(head));

		if (node == null) {
			// System.out.println("--> create" + head);
			node = new NodeV7(head);
			node.addTail(subNode);
			// this.list.put(node.getKey(), node);

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
	
	public NodeV7 getNode(String subKey) {
		return root.getNode(subKey);
	}

	@Override
	public String toString() {
		String rs = "";

		return rs;
	}

	public int getSupport(NodeV7 node) throws NullArrayException {
		int supp = 0;

		for (Cluster c : this.data.getCluster())
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
