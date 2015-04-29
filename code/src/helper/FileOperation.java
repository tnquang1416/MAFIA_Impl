package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class FileOperation {
	public static String[] readFile(String path) throws IOException {
		String rs = "";

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(
				new File(path)));
		String line = null;

		while ((line = reader.readLine()) != null) {
			rs += line + "\n";
		}

		return FileOperation.convert(rs.split("\n"));
	}

	private static String[] convert(String[] input) {
		String[] rs = new String[input.length - 1];

		for (int i = 1; i < input.length; i++) {
			rs[i - 1] = "";
			String[] temp = input[i].split("\t");

			for (int j = 1; j < temp.length; j++)
				rs[i - 1] += (temp[j] + "\t");
		}

		return rs;
	}
	
	public static void saveToFile(String path, String content){
		try {
			FileWriter fw = new FileWriter(path, true);
			
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
