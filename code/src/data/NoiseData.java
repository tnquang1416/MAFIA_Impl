package data;

import java.io.IOException;

import helper.FileOperation;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NoiseData implements Data {
	private String[] noiseInput;
	private float min;
	private float max;
	private int newMin;
	private int newMax;

	public NoiseData(String[] input) {
		this.noiseInput = input;
		newMin = 0;
		newMax = 1;
		min = Float.MAX_VALUE;
		max = Float.MIN_NORMAL;
	}

	public NoiseData(String file) throws IOException {
		this.readFromFile(file);
		newMin = 0;
		newMax = 1;
		min = Float.MAX_VALUE;
		max = Float.MIN_NORMAL;
	}

	private float[][] convertToFloat() {
		float[][] rsDouble = new float[noiseInput.length - 1][noiseInput[0]
				.split("\t").length - 1];

		for (int i = 1; i < noiseInput.length; i++) {
			String[] temp = noiseInput[i].split("\t");
			for (int j = 1; j < temp.length; j++) {
				float value = Float.parseFloat(temp[j]);
				rsDouble[i - 1][j - 1] = value;

				if (min > value)
					min = value;
				if (max < value)
					max = value;
			}
		}

		return rsDouble;
	}

	public int[][] normalizeDataInt() {
		float[][] temp = this.convertToFloat();
		int[][] rs = new int[temp.length][temp[0].length];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				float tempValue = temp[i][j];
				float newValue = ((tempValue - this.min)
						/ (this.max - this.min) * (this.newMax - this.newMin))
						+ this.newMin;
				rs[i][j] = Math.round(newValue);
			}
		}

		return rs;
	}

	public String[] normalizeData() {
		float[][] temp = this.convertToFloat();
		String[] rs = new String[temp.length];

		for (int i = 0; i < temp.length; i++) {
			rs[i] = "";
			for (int j = 0; j < temp[i].length; j++) {
				float tempValue = temp[i][j];
				float newValue = ((tempValue - this.min)
						/ (this.max - this.min) * (this.newMax - this.newMin))
						+ this.newMin;
				rs[i] += Math.round(newValue) + "\t";
			}
			System.out.println(rs[i]);
		}

		return rs;
	}

	@Override
	public String[] getFinalData() {
		return this.normalizeData();
	}

	@Override
	public void readFromFile(String file) throws IOException {
		this.noiseInput = FileOperation.readFile(file);
	}
}
