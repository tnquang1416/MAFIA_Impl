package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author TRAN Nhat Quang
 *
 */
public class FileOperation {
	public static String[] readFile(String path) throws IOException
	{
		String rs = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		
		while((line = reader.readLine())!= null)
		{
			rs += line + "\n";
		}
		
		return rs.split("\n");
	}
}
