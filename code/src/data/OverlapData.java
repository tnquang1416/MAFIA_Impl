package data;

import java.io.IOException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class OverlapData extends Data {
	public OverlapData(String file) throws IOException {
		super(file);
	}

	public OverlapData(String[] input) {
		super(input);
	}

	@Override
	protected void prepareDdata() {
		// TODO Auto-generated method stub
		
	}
}
