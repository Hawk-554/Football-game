package edu.century.football;
/****************************************************************************************
****************************************************************************************
Programmer: Adam (AJ) Kiheri
Class: Data Structures and Algorithms 2082 
PA: Project
Program: Teams class object
*****************************************************************************************
****************************************************************************************/


/* Teams Class Object
 * Object creates a team with team name, a few player names, and ratings 1-10 for
 * and Offense. 
 */
public class Teams {
	
	private String name,
				   qbName,
				   rbName,
				   wr1Name,
				   wr2Name,
				   wr3Name,
				   teName,
				   kickName,
				   puntName,
				   kickRetName;
	
	private int	   dpRating,
				   drRating,
				   opRating,
				   orRating,
				   soRating,
				   sdRating;
	
	/* No argument constructor that sets all values to null
	 * or zero.
	 */
	public Teams(){}
	
	
	/*Constructor gets passed values for all instance variables and assigns them to 
	 * the object.
	 */
	public Teams(String name, String qbName, String rbName, String wr1Name, String wr2Name, String wr3Name,
			String teName, String kickName, String puntName, String kickRetName, int dpRating, int drRating, 
				int opRating, int orRating, int soRating, int sdRating) {
	
		this.name = name;
		this.qbName = qbName;
		this.rbName = rbName;
		this.wr1Name = wr1Name;
		this.wr2Name = wr2Name;
		this.wr3Name = wr3Name;
		this.teName = teName;
		this.kickName = kickName;
		this.puntName = puntName;
		this.kickRetName = kickRetName;
		this.dpRating = dpRating;
		this.drRating = drRating;
		this.opRating = opRating;
		this.orRating = orRating;
		this.soRating = soRating;
		this.sdRating = sdRating;
	
	}

	
	/* Getters and setters for instance variables
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQbName() {
		return qbName;
	}

	public void setQbName(String qbName) {
		this.qbName = qbName;
	}

	public String getRbName() {
		return rbName;
	}

	public void setRbName(String rbName) {
		this.rbName = rbName;
	}

	public String getWr1Name() {
		return wr1Name;
	}

	public void setWr1Name(String wr1Name) {
		this.wr1Name = wr1Name;
	}

	public String getWr2Name() {
		return wr2Name;
	}

	public void setWr2Name(String wr2Name) {
		this.wr2Name = wr2Name;
	}

	public String getWr3Name() {
		return wr3Name;
	}

	public void setWr3Name(String wr3Name) {
		this.wr3Name = wr3Name;
	}

	public String getTeName() {
		return teName;
	}

	public void setTeName(String teName) {
		this.teName = teName;
	}

	public String getKickName() {
		return kickName;
	}

	public void setKickName(String kickName) {
		this.kickName = kickName;
	}

	public String getPuntName() {
		return puntName;
	}

	public void setPuntName(String puntName) {
		this.puntName = puntName;
	}

	public String getKickRetName() {
		return kickRetName;
	}

	public void setKickRetName(String kickRetName) {
		this.kickRetName = kickRetName;
	}
	
	public int getDpRating() {
		return dpRating;
	}

	public void setDpRating(int dpRating) {
		this.dpRating = dpRating;
	}

	public int getDrRating() {
		return drRating;
	}

	public void setDrRating(int drRating) {
		this.drRating = drRating;
	}

	public int getOpRating() {
		return opRating;
	}

	public void setOpRating(int opRating) {
		this.opRating = opRating;
	}

	public int getOrRating() {
		return orRating;
	}

	public void setOrRating(int orRating) {
		this.orRating = orRating;
	}

	public int getSoRating() {
		return soRating;
	}

	public void setSoRating(int soRating) {
		this.soRating = soRating;
	}

	public int getSdRating() {
		return sdRating;
	}

	public void setSdRating(int sdRating) {
		this.sdRating = sdRating;
	}
	
}
