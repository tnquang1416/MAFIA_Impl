package data;

import java.io.IOException;

public interface Data {
	public void readFromFile(String file) throws IOException;
	public String[] getFinalData();
}
