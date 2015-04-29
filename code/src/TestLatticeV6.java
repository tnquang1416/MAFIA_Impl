import helper.Timer;

import java.io.IOException;

import structure.Lattice;
import structure.LatticeV6;
import data.NoiseData;
import exception.WrongPositionException;

public class TestLatticeV6 {

	public static void main(String[] args) {
		Timer watch = new Timer();

		try {
			NoiseData data = new NoiseData("input.txt");
			// NoiseData data = new NoiseData("data_gauss_split_0.1_1.txt_h");
			Lattice lattice = new LatticeV6(data);

			lattice.initGene();
			System.out.println(lattice);
			System.out.println(((LatticeV6) lattice).getRoot().getSupport());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("It takes " + watch.stop() + "s");
	}

}
