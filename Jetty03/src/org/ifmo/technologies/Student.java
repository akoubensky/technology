package org.ifmo.technologies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	private String id;
	private String name;
	private String group;
	private Map<String, List<Integer>> marks = new HashMap<>();
	
	public Student(String id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}
	
	public void addMark(String subject, int mark) {
		List<Integer> marks = this.marks.get(subject);
		if (marks == null) {
			marks = new ArrayList<Integer>();
			this.marks.put(subject, marks);
		}
		marks.add(mark);
	}
	
	public String getId() { return id; }
	public String getName() { return name; }
	public String getGroup() { return group; }
	public void setName(String name) { this.name = name; }
	public void setGroup(String group) { this.group = group; }
	public Map<String,List<Integer>> getMarks() { return marks; }
	public List<Integer> getMarks(String subject) {
		List<Integer> marks = this.marks.get(subject);
		return (marks == null ? new ArrayList<Integer>() : marks);
	}
}
