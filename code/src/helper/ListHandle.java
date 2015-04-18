package helper;

import java.util.ArrayList;
import java.util.List;

public class ListHandle {
	public static boolean isInterNotNull(List<Integer> l1, List<Integer> l2) {
		for (int i : l1)
			if (l2.contains(i))
				return true;

		return false;
	}

	public static List<Integer> doUnion(List<Integer> l1, List<Integer> l2) {
		List<Integer> rs = new ArrayList<>();

		rs.addAll(l1);
		ListHandle.addAll(rs, l2);

		return rs;
	}

	public static void addAll(List<Integer> l1, List<Integer> l2) {
		for (int i : l2)
			ListHandle.add(l1, i);
	}

	public static void add(List<Integer> l, int i) {
		if (!l.contains(i))
			l.add(i);
	}

	public static void remove(List<Integer> l, int t) {
		for (int i = 0; i < l.size(); i++)
			if (l.get(i).equals(t)) {
				l.remove(i);
				i--;
			}
	}
	
	public static void removeAll(List<Integer> l1, List<Integer> l2) {
		for (int i = 0; i < l1.size(); i++)
			if (l2.contains(l1.get(i))) {
				l1.remove(i);
				i--;
			}
	}
}
