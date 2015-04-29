package helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import structure.NodeV6;

public class Trace {
	private Map<String, NodeV6> map;
	
	public Trace(){
		map = new HashMap<String, NodeV6>();
	}
	
	public void put(String key, NodeV6 node){
		map.put(key, node);
	}
	
	public NodeV6 get(String key){
		return this.map.get(key);
	}
	
	public NodeV6 get(List<Integer> head){
		return this.get(NodeV6.getTempKey(head).toString());
	}
	
	public int size(){
		return this.map.size();
	}
	
	public boolean containsKey(String key){
		return this.map.containsKey(key);
	}
	
	public Set<Entry<String, NodeV6>>  entrySet(){
		return this.map.entrySet();
	}
}
