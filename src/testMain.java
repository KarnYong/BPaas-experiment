import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import levenshtien.Levenshtein;

import org.xml.sax.SAXException;

import NCFragment.Edge;
import NCFragment.Vertex;
import breadthfirst.BreadthFirstPaths;
import breadthfirst.Graph;
import breadthfirst.StdOut;
import epc.EPCArc;
import epc.EPCConnector;
import epc.EPCEvent;
import epc.EPCEventType;
import epc.EPCFunction;
import epc.EPCGraph;
import epc.EPCHandler;


public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			Graph g = new Graph("My Graph");
//			g.addVertex(1, "B", "");
//			g.addVertex(2, "C", "");
//			g.addVertex(3, "D", "");
//			g.addVertex(4, "E", "");
//			g.addVertex(5, "F", "");
//			g.addVertex(6, "G", "");
//			g.addVertex(7, "H", "");
//			g.addVertex(8, "I", "");
//			g.addVertex(9, "J", "");
//			
//			g.addEdge(new Edge("e1", g.getVertex("A"), g.getVertex("B")));
//			g.addEdge(new Edge("e2", g.getVertex("A"), g.getVertex("C")));
//			g.addEdge(new Edge("e3", g.getVertex("B"), g.getVertex("D")));
//			g.addEdge(new Edge("e4", g.getVertex("C"), g.getVertex("E")));
//			g.addEdge(new Edge("e5", g.getVertex("D"), g.getVertex("F")));
//			g.addEdge(new Edge("e6", g.getVertex("E"), g.getVertex("F")));
//			g.addEdge(new Edge("e7", g.getVertex("F"), g.getVertex("G")));
//			g.addEdge(new Edge("e8", g.getVertex("F"), g.getVertex("H")));
//			g.addEdge(new Edge("e9", g.getVertex("G"), g.getVertex("I")));
//			g.addEdge(new Edge("e10", g.getVertex("H"), g.getVertex("J")));

			SAXParser saxParser = factory.newSAXParser();
			EPCHandler epcHandler = new EPCHandler();
			saxParser.parse("C:\\dataset\\SAP\\epml\\1An_khe0_1An_lbl5_merged.epml", epcHandler);
			EPCGraph epc = epcHandler.getEpc();
			int index = 0;
			for (EPCFunction function: epc.getFunctions()) {
				g.addVertex(index, function.getId(), function.getName(), "Task", true);
				++index;
			}
			for (EPCEvent event: epc.getEvents()) {
				if (event.getType() == EPCEventType.NORMAL_EVENT) {
					g.addVertex(index, event.getId(), event.getName(), "Event", false);
				}
				else if (event.getType() == EPCEventType.START_EVENT) {
					g.addVertex(index, event.getId(), event.getName(), "StartEvent", true);
				}
				else if (event.getType() == EPCEventType.END_EVENT) {
					g.addVertex(index, event.getId(), event.getName(), "EndEvent", true);
					
				}
				++index;
			}
			for (EPCConnector connector: epc.getConnectors()) {
				String connectorType = "";
				int incoming = 0, outgoing = 0;
				for (EPCArc arc: epc.getArcs()) {
					if (arc.getSource().equals(connector.getId()))
						++outgoing;
					else if (arc.getTarget().equals(connector.getId()))
						++incoming;
				}
				if (outgoing > 1) {
					if (connector.getType().equals("and")) {
						connectorType = "ParallelSplit";
					} else if (connector.getType().equals("or")) {
						connectorType = "MultipleChoice";
					} else if (connector.getType().equals("xor")) {
						connectorType = "ExclusiveChoice";
					}
				} 
				if (incoming > 1) {
					if (connector.getType().equals("and")) {
						connectorType = "Synchronisation";
					} else if (connector.getType().equals("or")) {
						connectorType = "MultiMerge";
					} else if (connector.getType().equals("xor")) {
						connectorType = "SimpleMerge";
					}
				}
				g.addVertex(index, connector.getId(), connectorType, connectorType, false);
				++index;
			}
			index = 0;
			for (EPCArc arc: epc.getArcs()) {
				g.addEdge(new Edge("e" + index, g.getVertex(arc.getSource()), g.getVertex(arc.getTarget())));
				++index;
			}
			
			for (Vertex vertex: g.getVertices()) {
				String type = vertex.getType();
				if (type.equals("Task"))
					System.out.println("task: " + vertex.getURI());
			}
			for (Vertex vertex: g.getVertices()) {
				String type = vertex.getType();
				if (type.equals("StartEvent"))
					System.out.println("StartEvent: " + vertex.getURI());
			}
			for (Vertex vertex: g.getVertices()) {
				String type = vertex.getType();
				if (type.equals("EndEvent"))
					System.out.println("EndEvent: " + vertex.getURI());
			}
			
			int startVertexId = g.getVertex("2").getId();   
			
			ArrayList<String>[] pathZone = (ArrayList<String>[])new ArrayList[5];
			pathZone[0] = new ArrayList<String>();
			pathZone[1] = new ArrayList<String>();
			pathZone[2] = new ArrayList<String>();
			pathZone[3] = new ArrayList<String>();
			pathZone[4] = new ArrayList<String>();
			
			BreadthFirstPaths bfs = new BreadthFirstPaths(g, startVertexId);
			for (int v = 0; v < g.V(); v++) {
				String path = "";
				int zone = 1;
//				if (!g.getVertex(v).isTask())
//					break;
				
	            if (bfs.hasPathTo(v) && g.getVertex(v).isTask() && zone <= 5) {
	            	StdOut.print(g.getVertex(startVertexId).getURI() + " to " + g.getVertex(v).getURI() + "(" +bfs.distTo(v)+ "): ");
	                int source = 0;
	                for (int target : bfs.pathTo(v)) {
	                    if (target == startVertexId) {
	                    	source = target;
	                    	path = g.getVertex(target).getURI();
	                    	StdOut.print(g.getVertex(target).getURI()); 
	                    } else {
							for (int v1 = 0; v1 < g.V(); v1++) {
							    for (int w : g.directedAdj(source)) {
							    	if (w == target) {
				                    	path += "->" + g.getVertex(w).getURI();
										StdOut.print("->" + g.getVertex(w).getURI());
										if (g.getVertex(w).isTask()) {
											StdOut.print("("+zone+")");
											if (!pathZone[zone-1].contains(path))
												pathZone[zone-1].add(path);
											path = g.getVertex(w).getURI();
											++zone;
										}
							    		source = target;
							    		break;
							    	}
							    }
							    for (int w : g.directedAdj(target)) {
							    	if (w == source) {
				                    	path += "<-" + g.getVertex(target).getURI();
							    		StdOut.print("<-" + g.getVertex(target).getURI());
							    		source = target;
										if (g.getVertex(target).isTask()) {
											if (!pathZone[zone-1].contains(path))
												pathZone[zone-1].add(path);
											path = g.getVertex(target).getURI();
											StdOut.print("("+zone+")");
											++zone;
										}
							    		break;
							    	}
							    }
							}
	                    }
	                }
	                StdOut.println();
	            }
	            
	            else {
//	                StdOut.printf("%d to %d (-):  not connected\n", startVertexId, v);
	            }
			}
			
			index = 1;
			for (ArrayList<String> paths: pathZone) {
				System.out.println("zone " + index);
				for (String path: paths) {
					int sourceIndex = path.indexOf("->");
					int targetIndex = path.indexOf("<-");
					String direction = "";
					if (sourceIndex == -1) {
						direction = "target";
					} else if (targetIndex == -1) {
						direction = "source";
					} else {
						if (sourceIndex < targetIndex)
							direction = "source";
						else
							direction = "target";
					}
					String test = "";
					String[] pathSplit = path.split("->|<-");
					for (String modelElement: pathSplit) {
						if (g.getVertex(modelElement).getType().equals("Task"))
							test += g.getVertex(modelElement).getName() + " ";
						else
							test += g.getVertex(modelElement).getType() + " ";
					}
					System.out.println(path);
					System.out.println(direction + " " + test);
				}
				++index;
			}
			System.out.println("====");

			System.out.println(Levenshtein.getLevenshteinDistance("ABC", "ACC"));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
