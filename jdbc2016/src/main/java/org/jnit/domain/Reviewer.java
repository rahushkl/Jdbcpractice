package org.jnit.domain;

import java.util.ArrayList;
import java.util.List;

public class Reviewer {

	private int id;
	private String name;
	
	private List<Task> tasks = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}