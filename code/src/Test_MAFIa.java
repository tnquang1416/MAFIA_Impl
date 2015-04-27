import java.io.IOException;
import java.util.List;

import algorithm.Algorithm;
import structure.LatticeV8;
import structure.NodeV6;
import data.NoiseData;
import exception.NullArrayException;


public class Test_MAFIa {

	public static void main(String[] args) throws IOException, NullArrayException {
		// TODO Auto-generated method stub
		
			NoiseData data = new NoiseData("em_1,n_0.05.3.txt.h");
			//NoiseData data = new NoiseData("SimpleInput.txt");
			LatticeV8 lattice = new LatticeV8(data);
		
			List<NodeV6> mfi = null;
			Algorithm mafia = new Algorithm(lattice,mfi);
			mafia.doHUTMFI();
			System.out.println(lattice);
			System.out.println(mfi);
			
		
	}
}
