/**
 * 
 */
package bi_clustering;

import helper.ListHandle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import exception.NullArrayException;

/**
 * @author TRAN Nhat Quang
 * 
 */
public class BiMax {
	// input
	private static String[][] matrix;
	// list of column index
	private static List<Integer> col = new ArrayList<>();
	// list of row index
	private static List<Integer> row = new ArrayList<>();

	/**
	 * 
	 * @param input
	 * @return
	 * @throws NullArrayException
	 */
	private static void prepare(String[] input) throws NullArrayException {
		if (input.length < 1)
			throw new NullArrayException();

		BiMax.matrix = new String[input.length][input[0].split("\t").length];
		for (int i = 0; i < input.length; i++) {
			BiMax.matrix[i] = input[i].split("\t");
		}

		for (int i = 0; i < BiMax.matrix.length; i++)
			BiMax.row.add(i);

		for (int j = 0; j < BiMax.matrix[0].length; j++)
			BiMax.col.add(j);
	}

	/**
	 * input: matrix E
	 * 
	 * @return M_an inclusion-maximal bicluster
	 * @throws NullArrayException
	 */
	public static List<Cluster> doBiMax(String[] input)
			throws NullArrayException {
		BiMax.prepare(input);

		List<List<Integer>> Z = new ArrayList<>();

		return BiMax.conquer(Z, BiMax.row, BiMax.col);
	}

	/**
	 * input: matrix E
	 * 
	 * @param Z
	 *            : the set of all CV column sets in the current call stack
	 * @param row
	 *            : list of row index
	 * @param column
	 *            : list of column index
	 * @return
	 */
	private static List<Cluster> conquer(List<List<Integer>> Z,
			List<Integer> row, List<Integer> column) {
		if (BiMax.isHighBitOnly(row, column)) {
			List<Cluster> rs = new ArrayList<>();
			rs.add(new Cluster(column, row));

			return rs;
		}

		List<Cluster> rs = new ArrayList<>();
		List<Cluster> mU = new ArrayList<>();
		List<Cluster> mV = new ArrayList<>();
		TempInfo info = new TempInfo();

		info = BiMax.divide(Z, row, column);

		if (info.gU.size() > 0) {
			List<Integer> newRow = new ArrayList<>();
			newRow.addAll(info.gU);
			mU.addAll(BiMax.conquer(Z, ListHandle.doUnion(info.gU, info.gW),
					info.cU));
		}

		if (info.gV.size() > 0 && info.gW.size() == 0) {
			mV.addAll(BiMax.conquer(Z, info.gV, info.cV));
		} else if (info.gW.size() > 0) {
			List<List<Integer>> tempZ = new ArrayList<>();
			tempZ.addAll(Z);
			tempZ.add(info.cV);
			mV.addAll(BiMax.conquer(tempZ,
					ListHandle.doUnion(info.gW, info.gV),
					ListHandle.doUnion(info.cU, info.cV)));
		}

		rs.addAll(mU);
		rs.addAll(mV);

		return rs;
	}

	private static boolean isHighBitOnly(List<Integer> row, List<Integer> col) {
		for (int i : row)
			for (int j : col) {
				if (BiMax.get(i, j).equals("0"))
					return false;
			}

		return true;
	}

	/**
	 * input: matrix E, column index, row index
	 * 
	 * @param Z
	 *            : the set of all CV column sets in the current call stack *
	 * @param row
	 * @param column
	 * @return
	 */
	private static TempInfo divide(List<List<Integer>> Z, List<Integer> row,
			List<Integer> column) {
		TempInfo info = new TempInfo();
		List<Integer> rowReduced = BiMax.reduce(Z, row, column);

		if (rowReduced.size() > 0) {
			List<Integer> count = BiMax.getColOfHighBit(rowReduced.get(0),
					column);
			if (count.size() > 0 && count.size() < column.size())
				ListHandle.addAll(info.cU, count);
			else
				ListHandle.addAll(info.cU, column);
		} else
			ListHandle.addAll(info.cU, column);

		List<Integer> temp = new ArrayList<>();
		ListHandle.addAll(temp, column);
		ListHandle.removeAll(temp, info.cU);
		ListHandle.addAll(info.cV, temp);

		for (int i : rowReduced) {
			List<Integer> highCol = BiMax.getColOfHighBit(i, column);

			if (info.cU.containsAll(highCol))
				ListHandle.add(info.gU, i);
			else if (info.cV.containsAll(highCol))
				ListHandle.add(info.gV, i);
			else
				ListHandle.add(info.gW, i);
		}

		return info;
	}

	private static List<Integer> getColOfHighBit(int row, List<Integer> column) {
		List<Integer> count = new ArrayList<>();

		for (int i : column)
			if (BiMax.get(row, i).equals("1"))
				count.add(i);

		return count;
	}

	/**
	 * input: matrix E, column index, row index
	 * 
	 * @param Z
	 *            : the set of all CV column sets in the current call stack *
	 * @param row
	 * @param column
	 * @return
	 */
	private static List<Integer> reduce(List<List<Integer>> Z,
			List<Integer> row, List<Integer> column) {
		List<Integer> rowReduced = new ArrayList<>();
		Iterator<Integer> ite = row.iterator();

		while (ite.hasNext()) {
			int i = ite.next();
			Stack<Integer> temp = new Stack<>();

			for (int j : column) {
				if (BiMax.get(i, j).equals("1"))
					temp.add(j);
			}

			if (!temp.empty()) {
				if (BiMax.checkColSet(Z, temp))
					ListHandle.add(rowReduced, i);
			}
		}

		return rowReduced;
	}

	private static boolean checkColSet(List<List<Integer>> Z, List<Integer> C) {
		for (List<Integer> temp : Z) {
			if (!ListHandle.isInterNotNull(temp, C))
				return false;
		}

		return true;
	}

	/*
	 * private static boolean checkColSet(List<List<Integer>> Z, Stack<Integer>
	 * C) { for (List<Integer> temp : Z) { if (!ListHandle.isInterNotNull(temp,
	 * C)) return false; }
	 * 
	 * return true; }
	 */

	private static String get(int row, int col) {
		return BiMax.matrix[row][col];
	}
}
