import java.io.IOException;

import bi_clustering.BiMax;
import bi_clustering.Cluster;
import data.NoiseData;
import exception.NullArrayException;


public class TestBiMax {

	public static void main(String[] args) {
		try {
			NoiseData data = new NoiseData("input1.txt");
			//NoiseData data = new NoiseData("data_gauss_split_0.1_1.txt_h");
			
			for (String s: data.getFinalData())
				System.out.println(s);
			System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
			for (Cluster c: BiMax.doBiMax(data.getFinalData()))
			{
				System.out.println("---------------------------------------------------");
				System.out.println(c.toString());
			}
			//BiMax.doBiMax(data.getFinalData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullArrayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
