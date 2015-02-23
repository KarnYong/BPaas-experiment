package epc;

import java.util.ArrayList;

public class EPCGraph {
	private ArrayList<EPCFunction> functions;
	private ArrayList<EPCEvent> events;
	private ArrayList<EPCConnector> connectors;
	private ArrayList<EPCArc> arcs;
	
	public EPCGraph() {
		functions = new ArrayList<EPCFunction>();
		events = new ArrayList<EPCEvent>();
		connectors = new ArrayList<EPCConnector>();
		arcs = new ArrayList<EPCArc>();	
	}
	
	public ArrayList<EPCFunction> getFunctions() {
		return functions;
	}
	public void setFunctions(ArrayList<EPCFunction> functions) {
		this.functions = functions;
	}
	public void addFunction(EPCFunction function) {
		this.functions.add(function);
	}
	public void addFunction(String id, String name) {
		this.functions.add(new EPCFunction(id, name));
	}
	
	public ArrayList<EPCEvent> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<EPCEvent> events) {
		this.events = events;
	}
	public void addEvent(EPCEvent event) {
		this.events.add(event);
	}
	public void addEvent(String id, String name) {
		this.events.add(new EPCEvent(id, name));
	}
	
	public ArrayList<EPCConnector> getConnectors() {
		return connectors;
	}
	public void setConnectors(ArrayList<EPCConnector> connectors) {
		this.connectors = connectors;
	}
	public void addConnector(EPCConnector connector) {
		this.connectors.add(connector);
	}
	public void addConnector(String id, String type) {
		this.connectors.add(new EPCConnector(id, type));
	}
	
	public ArrayList<EPCArc> getArcs() {
		return arcs;
	}
	public void setArcs(ArrayList<EPCArc> arcs) {
		this.arcs = arcs;
	}
	public void addArc(EPCArc arc) {
		this.arcs.add(arc);
	}
	public void addArc(String id, String source, String target) {
		this.arcs.add(new EPCArc(id, source, target));
	}
	public void InitialStartEndEvents() {
		for (EPCEvent event: this.getEvents()) {
			boolean isSource = false, isTarget = false;
			for (EPCArc arc: this.getArcs()) {
				if (arc.getSource().equals(event.getId()))
					isSource = true;
				if (arc.getTarget().equals(event.getId()))
					isTarget = true;
			}
			if (isSource == true && isTarget == false) {
				event.setType(EPCEventType.START_EVENT);
			} else if (isSource == false && isTarget == true) {
				event.setType(EPCEventType.END_EVENT);
			} else 	{
				event.setType(EPCEventType.NORMAL_EVENT);
			}
		}
	}
}
