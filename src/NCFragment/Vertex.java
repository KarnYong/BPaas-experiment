package NCFragment;

public class Vertex {
	private int id;
	final private String uri;
	final private String name;
	final private String type;
	final private int layer;
	boolean isTask = false;

	public Vertex(int id, String uri, String name, String type, boolean isTask) {
		this.id = id;
		this.uri = uri;
		this.name = name;
		this.type = type;
		this.isTask  = isTask;
		this.layer = 0;
	}

	public Vertex(String uri) {
		this.uri = uri;
		this.name = "";
		this.type = "";
		this.layer = 0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLayer() {
		return layer;
	}

	public Vertex(String uri, int layer) {
		this.uri = uri;
		this.name = "";
		this.type = "";
		this.layer = layer;
	}

	public Vertex(String uri, String type) {
		this.uri = uri;
		this.name = "";
		this.type = type;
		this.layer = 0;
	}

	public Vertex(String uri, String type, int layer) {
		this.uri = uri;
		this.name = "";
		this.type = type;
		this.layer = layer;
	}

	public String getURI() {
		return uri;
	}

	public String getType() {
		return type;
	}

	public boolean isTask() {
		return isTask;
	}

	public void setTask(boolean isTask) {
		this.isTask = isTask;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return uri;
	}

}