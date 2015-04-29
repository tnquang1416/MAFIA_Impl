import helper.ListHandle;

import java.util.ArrayList;
import java.util.List;

import structure.NodeV5;
import structure.NodeV6;


public class Testing {

	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		
		l1.add(1);
		l1.add(2);
		
		l2.add(1);
		l2.add(2);
		
		System.out.println(l1.equals(l2));
	}

}
