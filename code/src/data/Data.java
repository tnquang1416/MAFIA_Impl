package data;

import helper.FileOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import bi_clustering.BiMax;
import bi_clustering.Cluster;
import exception.NullArrayException;

public abstract class Data {
	private String[] data;
	private int length;
	private int lengthInfo;
	private List<Cluster> clusters;

	public Data(String[] input) {
		this.data = input;
		this.length = this.data.length;
		this.lengthInfo = this.data[0].length();
		this.clusters = new ArrayList<Cluster>();
	}

	public Data(String file) throws IOException {
		this.readFromFile(file);
		this.length = this.data.length;
		this.lengthInfo = this.data[0].split("\t").length;
		this.clusters = new ArrayList<Cluster>();
	}

	public void readFromFile(String file) throws IOException {
		this.data = FileOperation.readFile(file);
	};

	public String[] getFinalData() {
		return data;
	}

	public Map<String, Integer> getGenes() throws NullArrayException {
		if (this.clusters.size() < 1)
			this.clusters.addAll(new BiMax().doBiMax(this.getFinalData()));

		Map<String, Integer> rs = new HashMap<String, Integer>();

		for (Cluster c : this.clusters) {
			Entry<String, Integer> entry = c.getGenes(this.getInfoLength());
			rs.put(entry.getKey(), entry.getValue());
		}

		return rs;
	}

	public List<Cluster> getCluster() throws NullArrayException {
		if (this.clusters.size() < 1)
			this.clusters.addAll(new BiMax().doBiMax(this.getFinalData()));

		return this.clusters;
	}

	public Map<String, Integer> getChips() throws NullArrayException {
		if (this.clusters.size() < 1)
			this.clusters.addAll(new BiMax().doBiMax(this.getFinalData()));

		Map<String, Integer> rs = new HashMap<String, Integer>();

		for (Cluster c : this.clusters) {
			Entry<String, Integer> entry = c.getChips(this.getLength());
			rs.put(entry.getKey(), entry.getValue());
		}

		return rs;
	};

	public int getLength() {
		return this.length;
	}

	public int getInfoLength() {
		return this.lengthInfo;
	}

	protected abstract void prepareDdata();

	protected void updateData(String[] input) {
		data = input;
	}
}
