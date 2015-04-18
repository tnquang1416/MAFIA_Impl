package bi_clustering;

import java.util.ArrayList;
import java.util.List;

public class TempInfo {
	List<Integer> gU;
	List<Integer> gV;
	List<Integer> gW;
	List<Integer> cU;
	List<Integer> cV;
	
	public TempInfo()
	{
		this.gU = new ArrayList<Integer>();
		this.gV = new ArrayList<>();
		this.gW = new ArrayList<>();
		this.cU = new ArrayList<>();
		this.cV = new ArrayList<>();
	}
	
	@Override
	public String toString(){
		String rs = "";
		
		rs += "gU: " + gU.toString() + "\n";
		rs += "gV: " + gV.toString() + "\n";
		rs += "gW: " + gW.toString() + "\n";
		rs += "cU: " + cU.toString() + "\n";
		rs += "cV: " + cV.toString();
		
		return rs;
	}
}
