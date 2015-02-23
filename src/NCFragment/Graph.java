//package NCFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Graph {
//	private String uri;
//	private List<Workflow> workflows;
//	private List<Vertex> vertexes;
//	private List<Vertex> activityVertexes;
//	private List<Edge> edges;
//	
//	public Graph(String uri) {
//		this.uri = uri;
//		this.workflows = new ArrayList<Workflow>();
//		this.vertexes = new ArrayList<Vertex>();
//		this.activityVertexes = new ArrayList<Vertex>();
//		this.edges = new ArrayList<Edge>();
//	}
//
//	public Graph(List<Vertex> vertexes, List<Edge> edges) {
//		this.vertexes = vertexes;
//		this.edges = edges;
//	}
//	
//	public void setURI(String uri) {
//		this.uri = uri;
//	}
//	
//	public String getURI() {
//		return uri;
//	}
//	
//	public void setWorkflows(List<Workflow> workflows) {
//		this.workflows = workflows;
//	}
//	
//	public List<Workflow> getWorkflows() {
//		return workflows;
//	}
//	
//	public String getUri() {
//		return uri;
//	}
//
//	public void setUri(String uri) {
//		this.uri = uri;
//	}
//
//	public List<Vertex> getActivityVertexes() {
//		return activityVertexes;
//	}
//
//	public void setActivityVertexes(List<Vertex> activityVertexes) {
//		this.activityVertexes = activityVertexes;
//	}
//
//	public void setVertexes(List<Vertex> vertexes) {
//		this.vertexes = vertexes;
//	}
//
//	public void setEdges(List<Edge> edges) {
//		this.edges = edges;
//	}
//
//	public void setVertexesAndEdges(List<Vertex> vertexes, List<Edge> edges) {
//		this.vertexes = vertexes;
//		this.edges = edges;
//	}
//
//	public List<Vertex> getVertexes() {
//		return vertexes;
//	}
//
//	public List<Edge> getEdges() {
//		return edges;
//	}
//	
//	public Vertex getVertex(String uri) {
//		for (Vertex vertex: vertexes) {
//			if (vertex.getURI().equals(uri))
//				return vertex;
//		}
//		return null;
//	}
//	
//	public void addEdge(Edge edge) {
//		edges.add(edge);
//	}
//
//	public Vertex getActivityVertex(String uri) {
//		for (Vertex vertex: activityVertexes) {
//			if (vertex.getURI().equals(uri))
//				return vertex;
//		}
//		return null;
//	}
//	public void addActivityVertex(String uri, String type) {
//		if (getActivityVertex(uri) == null)
//			activityVertexes.add(new Vertex(uri, type));
//	}
//
//}