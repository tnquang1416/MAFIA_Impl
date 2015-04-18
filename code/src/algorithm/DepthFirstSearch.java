package algorithm;



import structure.LatticeV5;
import structure.NodeV4;

/**
 * 
 * @author NGUYEN Van Phat
 *
 */

public class DepthFirstSearch {
	private LatticeV5 lattice;

	
	public DepthFirstSearch(LatticeV5 lattice){
		this.lattice = lattice;
	}
	
	public LatticeV5 getLattice() {
		return lattice;
	}

	public void setLattice(LatticeV5 lattice) {
		this.lattice = lattice;
	}

	/* duyet tu root */
	public void rootDFS(LatticeV5 lattice){
		doDFS(lattice.getRoot());
	}
		
	
	/* duyet cac dinh con   */
	private void doDFS(NodeV4 V){
		
		for (NodeV4 i : V.getAllChilds()){
			if(i.isChecked() == true) { 
				V.removeChild(i);
			}
			doDFS(i);
		}	
	}
		

}
