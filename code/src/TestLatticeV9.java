import helper.Timer;

import java.io.IOException;

import structure.LatticeV9;
import data.Data;
import data.NoiseData;

public class TestLatticeV9 {

	public static void main(String[] args) {
		Timer watch = new Timer();

		try {
			//Data data = new NoiseData("SimpleInput.txt");
			//NoiseData data = new NoiseData("input.txt");
			//NoiseData data = new NoiseData("data_gauss_split_0.1_1.txt_h");
			Data data  = new NoiseData("data/data_gauss_split_0_9.txt_h");
			LatticeV9 lattice = new LatticeV9(data);

			lattice.generateByGenes();
			//System.out.println(lattice.getRoot());
			//System.out.println(((LatticeV7) lattice).getRoot().getSupport());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Opps, run out after " + watch.stop() + "s");
			e.printStackTrace();
		}

		System.out.println("It takes " + watch.stop() + "s");
	}

}
