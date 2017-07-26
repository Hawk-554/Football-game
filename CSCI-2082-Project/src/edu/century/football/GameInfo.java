package edu.century.football;
/****************************************************************************************
****************************************************************************************
Programmer: Adam (AJ) Kiheri
Class: Data Structures and Algorithms 2082 
PA: Project
Program: GameInfo class object
*****************************************************************************************
****************************************************************************************/
import java.security.SecureRandom;

/* GameInfo class object
 * Object creates object with variables with values used for a football game 
 * and methods to adjust those values, and calculate the value of plays 
 */
public class GameInfo {
	
	private final int GOALYARD=100;
	private int homeScore;
	private int awayScore;
	private int down;
	private int dist;
	private int time;
	private int qtr;
	private int curYard;
	private boolean possesion;
	private boolean scoreTd;
	
	
	/* No Argument Constructor used to initialize a game
	 * Assigns beginning values for the start of a football game
	 */
	public GameInfo(){
		this.down=1;
		this.dist=10;
		this.time=900;
		this.qtr=1;
		this.curYard = 35;
		this.possesion = true;
		this.scoreTd = false;
		
		
	}
	
	/* Getter and Setters for instance variables
	 */
	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getQtr() {
		return qtr;
	}

	public void setQtr(int qtr) {
		this.qtr = qtr;
	}
	
	public int getCurYard(){
		return this.curYard;
	}
	public void setCurYard(int cy){
		this.curYard = cy;
	}
	
	public boolean getPossesion(){
		return this.possesion;
	}
	
	public void setPossesion(boolean v){
		this.possesion = v;
	}
	
	public boolean getScoreTd(){
		return this.scoreTd;
	}
	
	public void setScoreTd(boolean v){
		this.scoreTd = v;
	}
	
	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	
	/* calcYardRun method
	 * Passed: 2 integers
	 * Returned: integer
	 * Method takes two integer values for offense and defense 
	 * rankings, applies a luck value determined by luckNum method
	 * and returns a  run yard value.
	 */
	public int calcYardRun(int def, int off){
		int oLuck = luckNum();
		int dLuck = luckNum();
		
		if (dLuck==0&&oLuck==10){
			return 100;
		}else if (dLuck==10&&oLuck==0){
			return -101;
		}else{
			return (off+luckNum())-(def+luckNum());
		}
	}
	
	/* calcYardPass method
	 * Passed: 2 integers
	 * Returned: integer
	 * Method takes two integer values for offense and defense 
	 * rankings, applies a luck value determined by luckNum method
	 * and returns a pass yard value.
	 */
	public int calcYardpass(int off, int def){
		int oLuck = luckNum();
		int dLuck = luckNum();
		
		if (dLuck==0&&oLuck==10){
			return 100;
		}else if (dLuck==10&&oLuck==0){
			return -101;
		}else if (dLuck == 9&&oLuck ==2){
			return -102;
		}else{
		return ((off*luckNum())-(def));
		}
	}
	
	/* calcYardRun method
	 * Passed: 2 integers
	 * Returned: integer
	 * Method takes two integer values for offense and defense 
	 * rankings, applies a luck value determined by luckNum method
	 * and returns special teams yard value.
	 */
	public int calcSpecial(int def, int off){
		int oLuck = luckNum();
		int dLuck = luckNum();
		
		if(dLuck==10&&oLuck==0){
			return -102;
		}else if(dLuck==0&&oLuck==10){
			return -101;
		}else{
			return -103;
		}
	}
	
	
	
	/* checkPos method
	 * Passed: integer
	 * Returned: boolean
	 * Method takes an integer for yard value and returns true if
	 * the current team maintain possession, otherwise if return false.
	 */
	public boolean checkPos(int yard){
		
		if(getDown()==4 && yard < getDist()){
			return false;
				
		}else if(yard==-101){
			return false;
			
		}else{
			return true;
		}
		
	}
	
	/* setGameDown method
	 * Passed: None
	 * Returned: None
	 * Method changes the down the team is on 
	 * when called
	 */
	public void setGameDown(){
		
		switch(getDown()){
		
		case 1:
			setDown(2);
			break;
		case 2:
			setDown(3);
			break;
		case 3:
			setDown(4);
			break;
		case 4:
			setDown(5);
		case 5:
			setDown(1);
			
		}
	
	}
	
	/* score Method
	 * Passed: None
	 * Returned: None
	 * Method checks if play results in a score
	 * and assigns setScoreTd to true
	 */
	public void score(){
		if(possesion){
			if(getCurYard()>=100){
				setScoreTd(true);
			}
		}else{
			if(getCurYard()<=0){
				setScoreTd(true);
			}
		}
	}
	
	/* setGameDist method
	 * Passed: integer
	 * Returned: None
	 * Method determines the amount of yards left before a first down
	 * if yards ate met for a first down it changes the down to 1
	 * or it changes the distance needed
	 */
	public void setGameDist(int oPlay){
		if(possesion){
			if(oPlay>=getDist()){
				if(GOALYARD-curYard < 10){
					setDist(GOALYARD-curYard);
					setDown(1);
				}else{
					setDist(10);
					setDown(1);
				}
			}else{
				setDist(getDist()-oPlay);
			}
		}else{
			if(oPlay>=getDist()){
				if(curYard < 10){
					setDist(curYard);
					setDown(1);
				}else{
					setDist(10);
					setDown(1);
				}
			}else{
				setDist(getDist()-oPlay);
			}
		}
	}
	
	/* setGameClock method
	 * Passed: None
	 * Returned: None
	 * Method reduces the game clock by 40 seconds
	 * for each play
	 */
	public void setGameClock(){
		setTime(getTime()-40);
	}
	
	
	/* setGameQtr method
	 * Passed: None
	 * Returned: None
	 * Method changes the game quarter and resets the time for each quarter
	 * when called
	 */
	public void setGameQtr(){
		
		if(getTime()<=0){
			switch(getQtr()){
		
			case 1:
				setQtr(2);
				setTime(900);
				break;
			case 2:
				setQtr(3);
				setTime(900);
				break;
			case 3:
				setQtr(4);
				setTime(900);
				break;
			case 4:
				setQtr(4);
				setTime(0);
				break;
			}
		}
	}
	
	/* luckNum method
	 * Passed: None
	 * Returned: integer
	 * Method creates a random number 0-10 for a luck value
	 * and returns that value
	 */
	private int luckNum(){
		SecureRandom luck = new SecureRandom();
		return luck.nextInt(11);
	}
}
