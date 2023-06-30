package it.polito.tdp.baseball;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.baseball.model.Model;
import it.polito.tdp.baseball.model.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnesse;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnDreamTeam;

    @FXML
    private Button btnGradoMassimo;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtYear;

    
    
    @FXML
    void doCalcolaConnesse(ActionEvent event) {
    	
    	txtResult.appendText("\nCI SONO " + this.model.getComponenteConnessa() + " COMPONENTI CONNESSE");
    	
    }

    
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	int anno ;
    	double salario ;
    	
    	try {
    		anno = Integer.parseInt(txtYear.getText()) ;
    		salario = Double.parseDouble(txtSalary.getText()) ;
    		
    	}
    	catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("INSERIRE UN VALORE NUMERICO" ) ;
    		return ;
    	}
    	
    	
    	
    	if(this.model.getListaAnni(anno).isEmpty()) {
    		txtResult.setText("L'ANNO INSERITO NON E' PRESENTE NEL DATABASE") ;
    	}
    	else {
    		this.model.creaGrafo(anno, salario);
    		this.model.addEdge(anno);
    		txtResult.setText("GRAFO CREATO CORRETTAMENTE: \n") ;
    		txtResult.appendText("# VERTICI: " + this.model.getVertexset() + "\n");
    		txtResult.appendText("# ARCHI: " + this.model.getEdgeSet());
    	}
    	
    	this.btnGradoMassimo.setDisable(false);
    	this.btnConnesse.setDisable(false);
    	this.btnDreamTeam.setDisable(false);
    }

    
    @FXML
    void doDreamTeam(ActionEvent event) {
    	this.model.dreamTeam();
    	txtResult.setText("DREAM TEAM CREATO ");
    	txtResult.appendText(this.model.getDreamTeam().toString());
    }

    
    @FXML
    void doGradoMassimo(ActionEvent event) {
    	txtResult.setText("GRADO MASSIMO : \n" );
    	txtResult.appendText(this.model.getGradoMassimo());
    }

    
    @FXML
    void initialize() {
        assert btnConnesse != null : "fx:id=\"btnConnesse\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGradoMassimo != null : "fx:id=\"btnGradoMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSalary != null : "fx:id=\"txtSalary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
