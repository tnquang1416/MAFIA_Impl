package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.UIDefaults.LazyValue;

import structure.LatticeV9;
import structure.NodeV7;
import data.Data;
import exception.NullArrayException;
/**
 * 
 * @author NGUYEN Van Phat
 *
 */

public class AlgorithmV2 {
	
	private LatticeV9 lattice;
	private List<NodeV7> mfi; 
	private static final int x = 2;
	boolean isHUT;
	
	public AlgorithmV2(LatticeV9 lattice, List<NodeV7> mfi){
		this.mfi = new ArrayList<>();
		this.lattice = lattice;
	}
	
	public LatticeV9 getLattice() {
		return lattice;
	}

	public void setLattice(LatticeV9 lattice) {
		this.lattice = lattice;
	}

	public List<NodeV7> getMfi() {
		return mfi;
	}

	public void setMfi(List<NodeV7> mfi) {
		this.mfi = mfi;
	}

	public void PEP(NodeV7 c, List<NodeV7> mfi) throws NullArrayException{
		NodeV7 newNode;
		NodeV7 temp;
		for (int i=0 ; i< lattice.getAllSubNodes(c).size();i++){
			newNode = lattice.getAllSubNodes(c).get(i);          //node C U i
			if(lattice.getSupport(newNode)==lattice.getSupport(c)){
				c = newNode;                         //Move in from i to c.head
			}
			else 
				if (lattice.getSupport(newNode) >= x){               //is frequent
				PEP(newNode,mfi);
			}
		}
		
		/*check c is leaf and c.head not in MFI*/
		if(lattice.getAllSubNodes(c).size()==0 && !mfi.contains(c)){
			mfi.add(c);
		}
	}
	
	/* do with PEP*/
	public void doPEP() throws NullArrayException{
		PEP(lattice.getRoot(),mfi);
	}
	
	public void HUTMFI(NodeV7 c, List<NodeV7> mfi) throws NullArrayException{

		// head union tail
		String HUTkey = c.getHUT();   // get key of Node Tail
		NodeV7 HUT = lattice.getNode(HUTkey); //create Node HUT
		if (mfi.contains(HUT)){
			return; 
		}
		NodeV7 newNode;	
		for (int i=0 ; i< lattice.getAllSubNodes(c).size();i++){
			newNode = lattice.getAllSubNodes(c).get(i);
			if(lattice.getSupport(newNode) >= x){
				HUTMFI(newNode,mfi);
			}
		}
		if(!mfi.contains(c)){
			mfi.add(c);
		}
	}
	
	/* do with HUTMFI */
	public void doHUTMFI() throws NullArrayException{
		HUTMFI(lattice.getRoot(),mfi);
	}
	
	public void FHUT(NodeV7 c, List<NodeV7> mfi, boolean isHUT) throws NullArrayException{
		NodeV7 newNode = null;
		for (int i=0 ; i< lattice.getAllSubNodes(c).size();i++){
			newNode = lattice.getAllSubNodes(c).get(i);
			
			// the leftmost child in the tail
			if(newNode.getKey().equals(c.getLeftMostKey())){
				isHUT = newNode.getKey().equals(c.getLeftMostKey());
			}
			if (lattice.getSupport(newNode) >= x){
				FHUT(newNode,mfi,isHUT);
			}
		}
		
		/*check c is leaf and c.head not in MFI*/
		if(lattice.getAllSubNodes(c).size()==0 && !mfi.contains(c)){
			mfi.add(c);
		}
		
		//isHUT and all extensions are frequent
		int minTemp = Integer.MAX_VALUE;
		List <Integer> maxTemp = null;
		
		for (NodeV7 child :lattice.getAllSubNodes(c)){
			List<Integer> temp = child.getHead();
			temp.removeAll(c.getHead());
			int index =(int) temp.get(0);
			if (index < minTemp){
				minTemp = index;
			}else 
				maxTemp.add(index);
		}
		
		NodeV7 node = lattice.getNode(maxTemp);
		if (isHUT && lattice.getSupport(node) >= x){
			return;
		}
	}
	
	public void doFHUT() throws NullArrayException{
		FHUT(lattice.getRoot(),mfi,isHUT);
	}
	
	
	/*do MAFIA*/
	public void doMAFIA() throws NullArrayException{
		doPEP();
		doHUTMFI();
		doFHUT();
	 }
}
