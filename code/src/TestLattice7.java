import helper.Timer;

import java.io.IOException;

import structure.Lattice;
import structure.LatticeV7;
import data.NoiseData;
import exception.WrongPositionException;

public class TestLattice7 {

	public static void main(String[] args) {
		Timer watch = new Timer();

		try {
			NoiseData data = new NoiseData("input1.txt");
			// NoiseData data = new NoiseData("data_gauss_split_0.1_1.txt_h");
			Lattice lattice = new LatticeV7(data);

			lattice.initGene();
			System.out.println(lattice);
			//System.out.println(((LatticeV7) lattice).getRoot().getSupport());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Opps, run out after " + watch.stop() + "s");
		}

		System.out.println("It takes " + watch.stop() + "s");
	}

}
