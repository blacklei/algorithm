package org.dzhou.interview.treeandgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Practice of "cracking the code interview"
 * 
 * You are given a list of projects and a list of dependencies (which is a list
 * of pairs of projects, where the first project is depend on the second
 * project). All of a project's dependencies must be built before the project
 * is. Find a build order that will allow the projects to be built. If there is
 * no valid build order, return an error.
 * 
 * @author DONG ZHOU
 *
 */
public class BuildOrder {

	public class Project {
		private String name;
		private int numDependencies;
		private List<Project> children = new ArrayList<>();
		private Map<String, Project> map = new HashMap<>();

		public boolean addNeighbor(Project node) {
			if (map.containsKey(node.getName()))
				return false;
			children.add(node);
			map.put(node.getName(), node);
			numDependencies++;
			return true;
		}

		public Project(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public int getNumDependencies() {
			return numDependencies;
		}

		public List<Project> getChildren() {
			return new ArrayList<>(children);
		}
	}

	public class Graph {
		private Map<String, Project> map = new HashMap<>();
		private List<Project> nodes = new ArrayList<>();

		public Project getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				Project node = new Project(name);
				map.put(name, node);
				nodes.add(node);
			}
			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			getOrCreateNode(startName).addNeighbor(getOrCreateNode(endName));
		}

		public List<Project> getNodes() {
			return new ArrayList<>(nodes);
		}
	}

	Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		for (String project : projects) {
			graph.getOrCreateNode(project);
		}
		for (String[] dependency : dependencies) {
			graph.addEdge(dependency[0], dependency[1]);
		}
		return graph;
	}

}
