package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.UIDefaults.LazyValue;

import structure.LatticeV8;
import structure.NodeV6;
import data.Data;
/**
 * 
 * @author NGUYEN Van Phat
 *
 */

public class Algorithm {
	
	private LatticeV8 lattice;
	private List<NodeV6> mfi; 
	private static final int x = 2;
	boolean isHUT;
	
	public Algorithm(LatticeV8 lattice, List<NodeV6> mfi){
		this.mfi = new ArrayList<>();
		this.lattice = lattice;
	}
	
	public LatticeV8 getLattice() {
		return lattice;
	}

	public void setLattice(LatticeV8 lattice) {
		this.lattice = lattice;
	}

	public List<NodeV6> getMfi() {
		return mfi;
	}

	public void setMfi(List<NodeV6> mfi) {
		this.mfi = mfi;
	}

	public void PEP(NodeV6 c, List<NodeV6> mfi){
		NodeV6 newNode;
		NodeV6 temp;
		for (int i=0 ; i< lattice.getAllSubNodes(c).size();i++){
			newNode = lattice.getAllSubNodes(c).get(i);          //node C U i
			if(lattice.getSupport(newNode)==lattice.getSupport(c)){
								
				//Move in from i to c.head
				temp = newNode;
			    newNode = c;
			    c = temp;
			}
			
			else 
				if (lattice.getSupport(newNode) >= x){               //is frequent
				PEP(newNode,mfi);
			}
		}
		
		/*check c is leaf and c.head not in MFI*/
		if(lattice.getSupport(c)==0 && !mfi.contains(c)){
			mfi.add(c);
		}
	}
	
	/* do with PEP*/
	public void doPEP(){
		PEP(lattice.getRoot(),mfi);
	}
	
	public void HUTMFI(NodeV6 c, List<NodeV6> mfi){

		// head union tail
		String HUTkey = c.getHUT();   // get key of Node Tail
		NodeV6 HUT = lattice.getNode(HUTkey);	//create Node HUT
		if (mfi.contains(HUT)){
			return; 
		}
		NodeV6 newNode;	
		for (int i=0 ; i< lattice.getAllSubNodes(c).size();i++){
			newNode = lattice.getAllSubNodes(c).get(i);
			if(lattice.getSupport(newNode) >= x){
				HUTMFI(newNode,mfi);
			}
		}
		if(c.getTail().size()==0 && !mfi.contains(c)){
			mfi.add(c);
		}
	}
	
	/* do with HUTMFI */
	public void doHUTMFI(){
		HUTMFI(lattice.getRoot(),mfi);
	}
	
	public void FHUT(NodeV6 c, List<NodeV6> mfi, boolean isHUT){
		NodeV6 newNode = null;
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
		
		for (NodeV6 child :lattice.getAllSubNodes(c)){
			List<Integer> temp = child.getHead();
			temp.removeAll(c.getHead());
			int index =(int) temp.get(0);
			if (index < minTemp){
				minTemp = index;
			}else 
				maxTemp.add(index);
		}
		
		NodeV6 node = new NodeV6(maxTemp);
		if (isHUT && lattice.getSupport(node) >= x){
			return;
		}
	}
	
	public void doFHUT(){
		FHUT(lattice.getRoot(),mfi,isHUT);
	}
	
	
	/*do MAFIA*/
	public void doMAFIA(){
		doPEP();
		doHUTMFI();
		doFHUT();
	 }
}
