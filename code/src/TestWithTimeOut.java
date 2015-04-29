import helper.FileOperation;
import helper.RunWithTimeOut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.Data;
import data.NoiseData;
import exception.InputErrorException;

public class TestWithTimeOut {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("result.txt");
			fw.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		File root = new File("data");
		System.out.println("Timeout: 3600s");
		
		for (File file : root.listFiles()) {
			String filePath = root.getPath() + "/" + file.getName();
			System.out.println("=========================== Read from file "
					+ file.getName());
			
			try {
				Data data = new NoiseData(filePath);

				Thread thread = new Thread(new RunWithTimeOut(data,
						file.getName()));
				thread.start();
				try {
					thread.join(3600000);
					//System.out.println("Timeout!!!");
				} catch (InterruptedException e) {
					FileOperation.saveToFile(RunWithTimeOut.output, file.getName()
							+ ": TimeOut");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InputErrorException e1) {
				FileOperation.saveToFile(RunWithTimeOut.output, file.getName()
						+ ": " + e1.getMessage());
			}

		}
	}

}
