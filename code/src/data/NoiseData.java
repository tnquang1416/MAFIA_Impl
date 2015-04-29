package data;

import java.io.IOException;

import exception.InputErrorException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class NoiseData extends Data {
	private float min;
	private float max;
	private int newMin = 0;
	private int newMax = 1;

	public NoiseData(String[] input) throws InputErrorException {
		super(input);
		this.updateData(this.normalizeMinMax());
		this.min = Float.MAX_VALUE;
		this.max = Float.MIN_NORMAL;
	}

	public NoiseData(String file) throws IOException, InputErrorException {
		super(file);
		this.updateData(this.normalizeMinMax());
		this.min = Float.MAX_VALUE;
		this.max = Float.MIN_NORMAL;
	}

	private float[][] convertToFloat() throws InputErrorException {
		float[][] rsDouble = new float[this.getFinalData().length][this
				.getFinalData()[0].split("\t").length];

		try {
			for (int i = 0; i < this.getFinalData().length; i++) {
				String[] temp = this.getFinalData()[i].split("\t");
				for (int j = 0; j < temp.length; j++) {
					float value = Float.parseFloat(temp[j]);
					rsDouble[i][j] = value;

					if (this.min > value)
						this.min = value;
					if (this.max < value)
						this.max = value;
				}
			}	
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InputErrorException();
		}

		return rsDouble;
	}

	public String[] normalizeMinMax() throws InputErrorException {
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
		}

		return rs;
	}

	/**
	 * x% discretization method
	 * 
	 * @return
	 * @throws InputErrorException 
	 */
	public String[] normalizeData() throws InputErrorException {
		float[][] temp = this.convertToFloat();
		double beta = 0.5 * this.max;
		String[] rs = new String[temp.length];

		for (int i = 0; i < temp.length; i++) {
			rs[i] = "";
			for (int j = 0; j < temp[i].length; j++) {
				if (temp[i][j] >= beta)
					rs[i] += 1 + "\t";
				else
					rs[i] += 0 + "\t";
			}
			// System.out.println(rs[i]);
		}

		return rs;
	}

	@Override
	protected void prepareDdata() {
		// TODO Auto-generated method stub

	}
}
