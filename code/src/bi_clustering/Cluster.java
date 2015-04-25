package bi_clustering;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Cluster {
	List<Integer> col;
	List<Integer> row;

	public Cluster(List<Integer> c, List<Integer> r) {
		col = new ArrayList<>();
		row = new ArrayList<>();
		this.col.addAll(c);
		this.row.addAll(r);
	}

	@Override
	public String toString() {
		String rs = "";

		rs += "Col ";
		for (int i : col)
			rs += i + " ";
		rs += "\n";

		rs += "Row ";
		for (int i : row)
			rs += i + " ";

		return rs;
	}

	public Entry<String, Integer> getGenes(int maxRowLength) {
		String key = "";

		for (int i = 0; i < maxRowLength; i++)
			if (this.col.contains(i))
				key += "1";
			else
				key += "0";

		return new AbstractMap.SimpleEntry<String, Integer>(key, this.row.size());
	}
	
	public Entry<String, Integer> getChips(int maxColLength) {
		String key = "";

		for (int i = 0; i < maxColLength; i++)
			if (this.row.contains(i))
				key += "1";
			else
				key += "0";

		return new AbstractMap.SimpleEntry<String, Integer>(key, this.col.size());
	}
	
	public List<Integer> getGenes(){
		return this.row;
	}
	
	public List<Integer> getChips(){
		return this.col;
	}
}
