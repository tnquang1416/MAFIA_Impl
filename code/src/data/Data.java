package data;

import helper.FileOperation;

import java.io.IOException;
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

	public Data(String[] input) {
		this.data = input;
		this.length = this.data.length;
		this.lengthInfo = this.data[0].length();
	}

	public Data(String file) throws IOException {
		this.readFromFile(file);
		this.length = this.data.length;
		this.lengthInfo = this.data[0].split("\t").length;
	}

	public void readFromFile(String file) throws IOException {
		this.data = FileOperation.readFile(file);
	};

	public String[] getFinalData() {
		return data;
	};

	public Map<String, Integer> getGenes() throws NullArrayException {
		Map<String, Integer> rs = new HashMap<String, Integer>();

		for (Cluster c : BiMax.doBiMax(this.getFinalData())) {
			Entry<String, Integer> entry = c.getGenes(this.getInfoLength());
			rs.put(entry.getKey(), entry.getValue());
		}

		return rs;
	}

	public List<Cluster> getCluster() throws NullArrayException {
		return BiMax.doBiMax(this.getFinalData());
	}

	public Map<String, Integer> getChips() throws NullArrayException {
		Map<String, Integer> rs = new HashMap<String, Integer>();

		for (Cluster c : BiMax.doBiMax(this.getFinalData())) {
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
