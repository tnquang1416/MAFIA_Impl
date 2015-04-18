import java.io.IOException;

import structure.Lattice;
import structure.LatticeV6;
import data.NoiseData;
import exception.WrongPositionException;


public class TestLatticeV6 {

	public static void main(String[] args) {
		try {
			NoiseData data = new NoiseData("input.txt");
			Lattice lattice = new LatticeV6(data);
			
			lattice.initGene();
			System.out.println(lattice);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
