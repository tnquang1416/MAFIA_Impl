import java.io.IOException;

import data.NoiseData;
import exception.WrongPositionException;
import structure.LatticeV5;
import helper.Timer;


public class TestLatticeV5 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		try {
			//String file = "input.txt";
			String file = "data_gauss_split_0.1_1.txt_h";
			LatticeV5 lattice = new LatticeV5(new NoiseData(file).getFinalData());
			lattice.initChip();
			lattice.toString();
			System.out.println("No.Node: " + lattice.getTotalNoNode());
		} catch (IOException | WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("--> It takes " + timer.stop() + "s");
	}

}
