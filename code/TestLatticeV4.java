import java.io.IOException;

import exception.WrongPositionException;
import structure.LatticeV4;
import structure.NodeV4;
import helper.FileOperation;
import helper.Timer;


public class TestLatticeV4 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		try {
			String[] input = FileOperation.readFile("input.txt");
			LatticeV4 lattice = new LatticeV4(input);
			
			lattice.init();
			//System.out.println("Root:" + lattice.getRoot().getAllChilds().size());
			//for(NodeV4 n: lattice.getRoot().getAllChilds())
				//System.out.println("-->" + n.getSupport());
			//System.out.println("-->Root: " + lattice.getRoot().getSupport());
			System.out.println(lattice.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Total cost: " + timer.stop() + "s");
		System.out.println("Tocal Nodes: " + LatticeV4.count);
		
	}

}
