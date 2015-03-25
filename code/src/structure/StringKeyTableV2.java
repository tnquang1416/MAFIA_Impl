package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class StringKeyTableV2 {
	private String[][] itemSet;

	public StringKeyTableV2(String[] input) {
		itemSet = this.convertArrayInput(input);
	}

	public int getColumnLength() {
		try {
			return this.itemSet.length;
		} catch (Exception ex) {
			return 0;
		}
	}

	public int getRowLength() {
		try {
			return this.itemSet[0].length;
		} catch (Exception ex) {
			return 0;
		}
	}

	private String[][] convertArrayInput(String[] input) {
		String[][] output = null;
		try {
			int x = input.length;
			output = new String[x][input[0].split("\t").length];

			for (int i = 0; i < x; i++) {
				String[] temp = input[i].split("\t");
				for (int y = 0; y < temp.length; y++) {
					output[i][y] = temp[y];
					// System.out.println(output[i][y]);
				}
			}

			return output;
		} catch (Exception e) {
			throw e;
		}
	}

	public Map<String, Integer> getItemSetInString() {
		Map<String, Integer> rs = new HashMap<>();

		for (int i = 0; i < this.getItemSet().length; i++) {
			String key = "";

			for (int j = 0; j < this.getRowLength(); j++)
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

	public int getKeySize() {
		return itemSet[0].length;
	}

	public String[][] getItemSet() {
		return this.itemSet;
	}
}
