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

	/* visit root */
	public void rootDFS(LatticeV5 lattice){
		doDFS(lattice.getRoot());
	}
		
	
	/* visit all childs */
	private void doDFS(NodeV4 v){
		for (int i=0; i < v.getAllChilds().size();i++){
			if(v.isChecked() == true) { 
				v.removeChild(i);
				i=i-1;
			}
			else doDFS(v);
		}	
	}
}
