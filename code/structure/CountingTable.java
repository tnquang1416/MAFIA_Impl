package structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author TRAN Nhat Quang
 * 
 */
public class CountingTable {
	private String[][] itemSet;

	public CountingTable(String[] input) {
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

	public Map<String, Integer> getAllSet() {
		Map<String, Integer> rs = new HashMap<String, Integer>();

		for (int i = 1; i < this.getColumnLength(); i++) {
			String temp = ",";

			for (int j = 1; j < this.getRowLength(); j++)
				if (this.itemSet[i][j].equals("1"))
					temp += j + ",";

			if (temp.length() > 1)
				if (rs.containsKey(temp)) {
					int value = rs.get(temp) + 1;
					rs.remove(temp);
					rs.put(temp, value);
				} else
					rs.put(temp, 1);
		}

		return rs;
	}

	public Map<List<Integer>, Integer> getAllSetInt() {
		Map<List<Integer>, Integer> rs = new HashMap<List<Integer>, Integer>();

		for (int i = 1; i < this.getColumnLength(); i++) {
			List<Integer> temp = new ArrayList<>();

			for (int j = 1; j < this.getRowLength(); j++)
				if (this.itemSet[i][j].equals("1"))
					temp.add(j);

			if (temp.size() > 0)
				if (rs.containsKey(temp)) {
					int value = rs.get(temp) + 1;
					rs.remove(temp);
					rs.put(temp, value);
				} else
					rs.put(temp, 1);
		}

		return rs;
	}

	public List<Boolean[]> getAllItemSetBoolean() {
		List<Boolean[]> map = new ArrayList<>();

		for (int i = 1; i < this.getColumnLength(); i++) {
			Boolean[] b = new Boolean[this.getRowLength() - 1];
			for (int j = 0; j < this.getRowLength() - 1; j++)
				if (itemSet[i][j + 1].equals("0"))
					b[j] = false;
				else
					b[j] = true;

			map.add(b);
		}

		return map;
	}

	public String[][] getItemSet() {
		return this.itemSet;
	}

	private String[][] convertArrayInput(String[] input) {
		// column 0 and row 0 store header, not value
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

	public int getKeySize() {
		return itemSet[0].length - 1;
	}
}
