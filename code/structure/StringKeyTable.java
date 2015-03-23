package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class StringKeyTable extends CountingTable {

	public StringKeyTable(String[] input) {
		super(input);
	}

	public Map<String, Integer> getItemSetInString() {
		Map<String, Integer> rs = new HashMap<>();

		for (int i = 1; i < this.getItemSet().length; i++) {
			String key = "";

			for (int j = 1; j < this.getRowLength(); j++)
				key += this.getItemSet()[i][j];

			if (rs.containsKey(key)) {
				int temp = rs.get(key) + 1;
				rs.remove(key);
				rs.put(key, temp);
			} else
				rs.put(key, 1);
		}

		return rs;
	}
}
