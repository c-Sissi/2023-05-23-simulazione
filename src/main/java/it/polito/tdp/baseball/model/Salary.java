package it.polito.tdp.baseball.model;

public class Salary {

    private int iD;
    private int year;
    private String teamCode;
    private int teamID;
    private String playerID;
    private Double salary;


    public Salary(int id, int year, String teamCode, int teamId, String playerId, double salary) {
		this.iD = id ;
		this.year = year ;
		this.teamCode = teamCode ;
		this.teamID = teamId ;
		this.playerID = playerId ;
		this.salary = salary ;
	}

	public Integer getID(){
        return iD;
    }

    public void setID(Integer iD){
        this.iD=iD;
    }

    public Integer getYear(){
        return year;
    }

    public void setYear(Integer year){
        this.year=year;
    }

    public String getTeamCode(){
        return teamCode;
    }

    public void setTeamCode(String teamCode){
        this.teamCode=teamCode;
    }

    public Integer getTeamID(){
        return teamID;
    }

    public void setTeamID(Integer teamID){
        this.teamID=teamID;
    }

    public String getPlayerID(){
        return playerID;
    }

    public void setPlayerID(String playerID){
        this.playerID=playerID;
    }

    public Double getSalary(){
        return salary;
    }

    public void setSalary(Double salary){
        this.salary=salary;
    }

}


