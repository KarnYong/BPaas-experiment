package NCFragment;


public class Workflow {
	final private String uri;
	final Vertex startVertex;
	
	public Workflow(String uri, Vertex startVertex) {
		super();
		this.uri = uri;
		this.startVertex = startVertex;
	}

	public String getURI() {
		return uri;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}
}
