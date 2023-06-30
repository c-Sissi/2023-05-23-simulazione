package it.polito.tdp.baseball.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.baseball.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao ;
	
	private Map<String, Double> mappaPlayerSalary;
	private Graph<People, DefaultEdge> graph ;
	private Map<String, People> mappaIdPlayer ;
	
	List<People> dreamTeam ;
	double dreamTeamSalary ;
	
	public Model() {
		this.dao = new BaseballDAO () ;
		this.mappaIdPlayer = new HashMap<> () ;
		this.mappaPlayerSalary = new HashMap<> ();
		this.dao.readAllPlayers(mappaIdPlayer);
		

	}
	
	public List<Salary> getListaAnni(int year) {
		
		return this.dao.getYear(year) ;
	}
	
	public void creaGrafo(int anno, double salary) {
		this.graph = new SimpleGraph<People, DefaultEdge>(DefaultEdge.class) ;
		
		for(Salary s: this.getListaAnni(anno)) {
			if(s.getSalary() > (salary * 1000000)) {
				mappaPlayerSalary.put(s.getPlayerID(), s.getSalary());
				this.graph.addVertex(mappaIdPlayer.get(s.getPlayerID())) ;
			}
		}
		
	}
	public int getVertexset() {
		return this.graph.vertexSet().size() ;
	}
	
	public int getEdgeSet() {
		return this.graph.edgeSet().size() ;
	}
	
	public void addEdge(int anno) {
		for(Edge e: this.dao.getAppearances(anno, mappaIdPlayer)){
			People p1 = e.getPlayer1() ;
			People p2 = e.getPlayer2();
			if(this.graph.containsVertex(p1) && this.graph.containsVertex(p2)) {
				this.graph.addEdge(p1, p2);
			}
		}
	}
	
	public String getGradoMassimo() {
		int maxDegree = 0;
		People vertexMax = null ;
		for(People p: this.graph.vertexSet()) {
			if(this.graph.degreeOf(p) > maxDegree) {
				vertexMax = p ;
				maxDegree = this.graph.degreeOf(p) ;
			}
		}
		return vertexMax.toString() + " \n" + "GRADO MASSIMO: "+ maxDegree  ;
	}
	
	public int getComponenteConnessa() {
		ConnectivityInspector <People,DefaultEdge>  connInsp= new ConnectivityInspector <> (this.graph);
		return connInsp.connectedSets().size() ;
	}
	
	public void dreamTeam() {
		this.dreamTeamSalary = 0.0 ;
		this.dreamTeam = new LinkedList<> ();
		List<People> parziale = new LinkedList<>() ;
		List<People> rimanenti = new LinkedList<>(this.graph.vertexSet());
		ricorsione(parziale, rimanenti) ;
		
		
	}
	
	public void ricorsione(List<People> parziale, List<People> rimanenti) {
// 		CONDIZIONE DI TERMINAZIONE
		if(rimanenti.isEmpty()) {
			double salario = getSalarioDreamTeam(parziale) ;
			if(salario > this.dreamTeamSalary) {
				this.dreamTeamSalary = salario ;
				this.dreamTeam = new LinkedList<> ();
			}
			return ;
		}
		
//		RICORSIVE CODE
		List<People> squadra =  Graphs.neighborListOf(this.graph, rimanenti.get(0));
		squadra.add( rimanenti.get(0));
		People start = minDegreeVertex(squadra);
		List<People> squadraMin =  Graphs.neighborListOf(this.graph, rimanenti.get(0));
		squadraMin.add( rimanenti.get(0));
		
		for (People p : squadraMin) {
			List<People> currentRimanenti = new ArrayList<>(rimanenti);
			parziale.add(p);
			currentRimanenti.removeAll(squadraMin);
			ricorsione(parziale, currentRimanenti);
			parziale.remove(parziale.size()-1);
		}
	}

	private People minDegreeVertex(List<People> team) {
		People res = null;
		int gradoMin = -1;
		for (People p : team) {
			int grado = Graphs.neighborListOf(this.graph, p).size();
			if (gradoMin==-1 || grado<gradoMin) {
				res = p;
			}
		}		
		return res;
	}

	private double getSalarioDreamTeam(List<People> team) {
		double salario = 0.0 ;
		for(People p: team ) {
			salario += this.mappaPlayerSalary.get(p.getPlayerID());
		}
		return salario;
	}
	public List<People> getDreamTeam() {
		return this.dreamTeam;
	}
}