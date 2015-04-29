package helper;

/**
 * @author TRAN NHAT QUANG
 */

import structure.LatticeV9;
import data.Data;
import exception.NullArrayException;

public class RunWithTimeOut implements Runnable {
	Data data;
	LatticeV9 lattice;
	public static String output = "result.txt"; // path of output file
	String input = "";

	public RunWithTimeOut(Data data, String fileInput) {
		input = fileInput;
		this.data = data;
	}

	@Override
	public void run() {
		String init = this.initLattice();
		System.out.println(init);
		FileOperation.saveToFile(RunWithTimeOut.output, init + "\n");
		// do MAFIA
	}

	private String initLattice() {
		try {
			Timer watch = new Timer();

			this.lattice = new LatticeV9(data);
			//this.lattice.generateByGenes();

			return this.input + " takes " + watch.stop() + "s";
		} catch (NullArrayException e) {
			return this.input + " fails";
		}
	}

}
