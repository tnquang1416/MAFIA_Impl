package data;

import helper.FileOperation;

import java.io.IOException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class OverlapData implements Data {
	private String[] input;

	public OverlapData(String file) throws IOException {
		this.readFromFile(file);
	}

	public OverlapData(String[] input) {
		this.input = input;
	}

	@Override
	public void readFromFile(String file) throws IOException {
		this.input = FileOperation.readFile(file);
	}

	@Override
	public String[] getFinalData() {
		return input;
	}

}
