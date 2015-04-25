import helper.Timer;

import java.io.IOException;

import structure.LatticeV8;
import data.NoiseData;

public class TestLatticeV8 {

	public static void main(String[] args) {
		Timer watch = new Timer();

		try {
			NoiseData data = new NoiseData("SimpleInput.txt");
			// NoiseData data = new NoiseData("data_gauss_split_0.1_1.txt_h");
			LatticeV8 lattice = new LatticeV8(data);

			lattice.generateByGenes();
			System.out.println(lattice);
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
