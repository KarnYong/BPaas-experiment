package NCFragment;

public class Edge {
	private final String uri;
	private final Vertex source;
	private final Vertex target;
	private int weight;
	private int zone;

	public Edge(String source, String target) {
		this.uri = "";
		this.source = new Vertex(source);
		this.target = new Vertex(target);
		this.weight = 0;
		this.zone = 0;
	}

	public Edge(String source, String target, int zone) {
		this.uri = "";
		this.source = new Vertex(source);
		this.target = new Vertex(target);
		this.weight = 0;
		this.zone = zone;
	}

	public Edge(Vertex source, Vertex target, int zone) {
		this.uri = "";
		this.source = source;
		this.target = target;
		this.weight = 0;
		this.zone = zone;
	}

	public Edge(String uri, Vertex source, Vertex target, int weight) {
		this.uri = uri;
		this.source = source;
		this.target = target;
		this.weight = weight;
		this.zone = 0;
	}

	public Edge(String uri, Vertex source, Vertex target) {
		this.uri = uri;
		this.source = source;
		this.target = target;
	}

	public int getZone() {
		return zone;
	}

	public String getURI() {
		return uri;
	}

	public Vertex getTarget() {
		return target;
	}

	public Vertex getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " " + target;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Edge))
			return false;
		Edge otherEdge = (Edge) other;
		if (otherEdge.getSource().getURI().equals(this.getSource().getURI()) &&
				otherEdge.getTarget().getURI().equals(this.getTarget().getURI()))
			return true;
		else
			return false;
	}
}