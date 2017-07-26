package edu.century.football;
/****************************************************************************************
****************************************************************************************
Programmer: Adam (AJ) Kiheri
Class: Data Structures and Algorithms 2082 
PA: Project
Program: GameGUI class object
*****************************************************************************************
****************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
	
/* GameGUI class object
 * Creates a GUI of a basic football simulator
 */
public class GameGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	 private Teams bears = new Teams("Bears","Jay Cutler", "Jeremy Langford"
			, "Alshon Jeffery", "Eddie Royal", "Kevin White","Zach Miller"
			, "Connor Barth", "Pat O'Donnell","Eddie Royal",6,4,7,6,6,5);
	
	 private  Teams vikings = new Teams("Vikings","Sam BradFord", "Jerrick MacKinnon"
			, "Stephon Diggs","Adam Thielan", "Corderelle Patterson", "Kyle Rudolf"
			, "Blair Walsh", "Jeffery Locke","Corderelle Patterson",9,8,6,3,8,8);
	
     private Teams lions = new Teams("Lions", "Matthew Stafford", "Theo Riddick"
			, "Golden Tate", "Marvin Jones","Anquan Boldin", "Eric Ebron"
			, "Matt Prater", "Sam Martin", "Golden Tate",7,7,8,4,6,6 );
	
     private Teams packers = new Teams("Packers", "Aaron Rogers", "Eddie Lacy"
			, "Jordy Nelson", "Randall Cobb", "Davante Adams", "Richard Rogers"
			, "Mason Crosby", "Jacob Schum", "Golden Tate", 6,4,8,5,5,6);
	
    private TeamCollection teamList = new TeamCollection();
	
   
	private int gameYard = 0;
    private int yardValue = 50;
    private int ballPos;
    private Teams ht = new Teams();
    private Teams at = new Teams();
    
	private GameInfo gInfo;
	
	private MainGui mainGui;
	
	private JPanel main,
				   scoreBoard,
				   info,
				   info2,
				   gamePlay2,
				   gamePlay;
	
	private JButton run,
					pass, 
					kick,
					start,
					punt;
	
	private JLabel down,
				   distance,
				   qtr,
				   yard,
				   home,
				   away,
				   time;
	
	private JMenuBar bar,
					 bar2;
	
	private JMenu homeTeam,
	     		  awayTeam;
	
	private JMenuItem vikes,
					  vikesA,
					  pack,
					  packA,
					  cats,
					  catsA,
					  cBear,
					  cBearA;
	
	
	private JTextField downDis,
					   distanceDis,
					   yardDis,
					   homeDis,
					   awayDis,
					   qtrDis,
					   timeDis;

	private JTextArea pBP;
	
	private JScrollPane scrollBar;
	
	private Image ballField = new ImageIcon("footballField.png").getImage();	
	private Image ball = new ImageIcon("football.gif").getImage();
	private Image vikeP = new ImageIcon("vikings.png").getImage();
	private Image packP = new ImageIcon("packers.png").getImage();
	private Image lionsP = new ImageIcon("lions.png").getImage();
	private Image bearsP = new ImageIcon("bears.png").getImage();
	private Image homeP = new ImageIcon("vikings.png").getImage();
	private Image awayP = new ImageIcon("packers.png").getImage();
	
		   
	/* GameGUI constructor
	 * Passed: String
	 * Constructor creates the GUI for the football simulator and 
	 * teamList ArrayList used for the simulator
	 */
	public GameGUI (String title){		
		
		super(title);
		teamList.addTeam(vikings);	
		teamList.addTeam(bears);
		teamList.addTeam(packers);
		teamList.addTeam(lions);
		ht = vikings;
		at = packers;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,1000);
		setMaximumSize(getSize());
		setMinimumSize(getMaximumSize());
		guiComp();	
		
	}
	
	/* guiComp method
	 * Passed: None
	 * Returned: None
	 * Method initializes on the containers, panels, labels, and fields 
	 * for the GUI
	 */
	private void guiComp(){
		
		mainGui = new MainGui();
		setBackground(Color.BLACK);
		main = new JPanel(new BorderLayout());
		
		setInfo();
		setScoreBoard();
		setGamePlay();
		
		main.add(scoreBoard, BorderLayout.NORTH);
		main.add(mainGui, BorderLayout.CENTER);
		main.add(info, BorderLayout.WEST);
		main.add(gamePlay, BorderLayout.EAST);
		add(main);
	}
	
	/* setScoreBoard method
	 * Passed: None
	 * Returned: None
	 * Method sets the scoreBoard panel for the GUI
	 */
	private void setScoreBoard(){
		
		scoreBoard = new JPanel(new GridLayout(2,4));
		scoreBoard.setBackground(Color.BLACK);
		
		home = new JLabel("Home", SwingConstants.CENTER);
		home.setForeground(Color.GREEN);
		away = new JLabel("Away", SwingConstants.CENTER);
		away.setForeground(Color.RED);
		time = new JLabel("Time", SwingConstants.CENTER);
		time.setForeground(Color.YELLOW);
		qtr = new JLabel("Quarter", SwingConstants.CENTER);
		qtr.setForeground(Color.YELLOW);
		
		homeDis = new JTextField(25);
		homeDis.setHorizontalAlignment(SwingConstants.CENTER);
		homeDis.setEditable(false);
		homeDis.setBackground(Color.BLACK);
		homeDis.setForeground(Color.YELLOW);
		
		
		awayDis = new JTextField(25);
		awayDis.setHorizontalAlignment(SwingConstants.CENTER);
		awayDis.setEditable(false);
		awayDis.setBackground(Color.BLACK);
		awayDis.setForeground(Color.YELLOW);
		
		timeDis = new JTextField(25);
		timeDis.setHorizontalAlignment(SwingConstants.CENTER);
		timeDis.setEditable(false);
		timeDis.setBackground(Color.BLACK);
		timeDis.setForeground(Color.YELLOW);
		
		qtrDis = new JTextField(25);
		qtrDis.setHorizontalAlignment(SwingConstants.CENTER);
		qtrDis.setEditable(false);
		qtrDis.setBackground(Color.BLACK);
		qtrDis.setForeground(Color.YELLOW);
		
		scoreBoard.add(home);
		scoreBoard.add(time);
		scoreBoard.add(qtr);
		scoreBoard.add(away);
		
		scoreBoard.add(homeDis);
		scoreBoard.add(timeDis);
		scoreBoard.add(qtrDis);
		scoreBoard.add(awayDis);
		
	}
	
	/* setGamePlay Method
	 * Passed: None
	 * Returned: None
	 * Method set the gamePlay and gamePlay2 panels of the GUI
	 */
	private void setGamePlay(){
		
		gamePlay = new JPanel(new BorderLayout());
		gamePlay.setBackground(Color.BLACK);
		gamePlay2 = new JPanel(new GridLayout(5,2));
		gamePlay2.setBackground(Color.BLACK);
		yard = new JLabel(" YardLine");	
		yard.setForeground(Color.YELLOW);
		down = new JLabel(" Down");
		down.setForeground(Color.YELLOW);
		distance = new JLabel(" Distance");
		distance.setForeground(Color.YELLOW);
	
		yardDis = new JTextField(25);
		yardDis.setBackground(Color.BLACK);
		yardDis.setForeground(Color.YELLOW);
		yardDis.setHorizontalAlignment(SwingConstants.CENTER);
		yardDis.setEditable(false);
		
		downDis = new JTextField(25);
		downDis.setBackground(Color.BLACK);
		downDis.setForeground(Color.YELLOW);
		downDis.setHorizontalAlignment(SwingConstants.CENTER);
		downDis.setEditable(false);
		
		distanceDis = new JTextField(25);
		distanceDis.setBackground(Color.BLACK);
		distanceDis.setForeground(Color.YELLOW);
		distanceDis.setHorizontalAlignment(SwingConstants.CENTER);
		distanceDis.setEditable(false);
		
		run = new JButton("Run/D-Run");
		run.setBackground(Color.YELLOW);
	
		
		pass = new JButton("Pass/D-Pass");
		pass.setBackground(Color.YELLOW);
		
		
		kick = new JButton("Kick/D-Kick");
		kick.setBackground(Color.YELLOW);
	
		
		punt = new JButton("Punt/D-Punt");
		punt.setBackground(Color.YELLOW);
		
		
		gamePlay2.add(down);
		gamePlay2.add(downDis);
		gamePlay2.add(distance);
		gamePlay2.add(distanceDis);
		gamePlay2.add(yard);
		gamePlay2.add(yardDis);
		gamePlay2.add(run);
		gamePlay2.add(pass);
		gamePlay2.add(kick);
		gamePlay2.add(punt);
		
		gamePlay.add(gamePlay2, BorderLayout.NORTH);
		
		
		
	}
	
	/* setInfo method
	 * Passed: None
	 * Returned: None
	 * Method sets the info panel of the GUI
	 */
	private void setInfo(){
		
		bar = new JMenuBar();
		bar.setBackground(Color.YELLOW);
		
		
		bar2 = new JMenuBar();
		bar2.setBackground(Color.YELLOW);
		
		info2 = new JPanel(new GridLayout(2,2));
		info2.setBackground(Color.YELLOW);
		
		start = new JButton("Start");
		start.setBackground(Color.YELLOW);
		start.addActionListener(this);
		
		homeTeam = new JMenu("Home Team");
		awayTeam = new JMenu("Away Team");
		vikes = new JMenuItem("Vikings");
		vikes.addActionListener(this);
		pack = new JMenuItem("Packers");
		pack.addActionListener(this);
		cats = new JMenuItem("Lions");
		cats.addActionListener(this);
		cBear= new JMenuItem("Bears");
		cBear.addActionListener(this);
		vikesA = new JMenuItem("Vikings Away");
		vikesA.addActionListener(this);
		packA = new JMenuItem("Packers Away");
		packA.addActionListener(this);
		catsA = new JMenuItem("Lions Away");
		catsA.addActionListener(this);
		cBearA= new JMenuItem("Bears Away");
		cBearA.addActionListener(this);
		
		awayTeam.add(vikesA);
		awayTeam.add(packA);
		awayTeam.add(catsA);
		awayTeam.add(cBearA);
		
		homeTeam.add(vikes);
		homeTeam.add(pack);
		homeTeam.add(cats);
		homeTeam.add(cBear);
		
		bar.add(homeTeam);
		setJMenuBar(bar);
		bar2.add(awayTeam);
		setJMenuBar(bar2);
		
		info2.add(bar);
		info2.add(bar2);
		info2.add(start);
		
		info = new JPanel(new BorderLayout());
		pBP = new JTextArea(50,50);
		scrollBar = new JScrollPane(pBP);
		scrollBar.setVerticalScrollBarPolicy
		(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	
		pBP.setBackground(Color.BLACK);
		pBP.setForeground(Color.YELLOW);
		
		info.add(info2, BorderLayout.NORTH);
		info.add(scrollBar, BorderLayout.CENTER);
		
	}
	
	/* MainGUI class object
	 * This object uses the paint component to creates and change the main
	 * portion of the GUI
	 */
	private class MainGui extends JPanel{
		
		private static final long serialVersionUID = 1L;
		
		/* MainGui no Argument constructor
		 * sets the background to black
		 */
		public MainGui(){
			
			setBackground(Color.BLACK);
			
		}
		
		/* Overridden painComponent method
		 * Passed: Graphics
		 * returned: None
		 * Method uses images for the football field, 
		 * home team, away team, and the ball and paints them to the GUI 
		 */
		protected void paintComponent(Graphics g){
			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawImage(ballField, 0, 0,800,500, null);
			g2.drawImage(homeP, 0,501, 400, 400, null);
			g2.drawImage(awayP,401, 501,400,400, null);
			
		
			ballPosition();
			drawBall(g2);
					
		}
		
		

		/* ballPosition method
		 * Passed: None
		 * Returned: None
		 * Calculates the position of the ball on the GUI
		 */
		private void ballPosition() {
			
			ballPos = 63 + (int)Math.round(6.45*yardValue);
			
		}
		
		/* drawBall method
		 * Passed: Graphics2D
		 * Returned: None
		 * Method that draws the image of the ball.
		 */
		private void drawBall(Graphics2D g2){
				
			g2.drawImage(ball, ballPos, 250, 25, 25, null);
					
		}
		
	}
	
	/* main method
	 * Passed: String[]
	 * Returned: None
	 * Method runs the main method to run the GUI
	 */
	public static void main(String[]args){
		GameGUI window = new GameGUI("Football Game");
		window.setVisible(true);
	}

	/* changePos method
	 * Passed: None
	 * Returned: None
	 * Method changes the possession of the ball between
	 * home team or away team
	 */
	public void changePos(){
		if(gInfo.getPossesion()){
			gInfo.setPossesion(false);
			home.setForeground(Color.RED);
			away.setForeground(Color.GREEN);
			gInfo.setDown(1);
			gInfo.setDist(10);
			downDis.setText(""+gInfo.getDown());
			distanceDis.setText(""+gInfo.getDist());
				
		}else{
			gInfo.setPossesion(true);
			home.setForeground(Color.GREEN);
			away.setForeground(Color.RED);
			gInfo.setDown(1);
			gInfo.setDist(10);
			downDis.setText(""+gInfo.getDown());
			distanceDis.setText(""+gInfo.getDist());
		
		}	
		mainGui.repaint();
	}
	
	/* checkClock method
	 * Passed: None
	 * Returned: None
	 * Method checks the clock and quarter and established if the game is over 
	 * or not and displays the winner of if there is a tie
	 */
	public void checkClock(){
		
		if(gInfo.getQtr()>=4 && gInfo.getHomeScore()!= gInfo.getAwayScore() && gInfo.getTime() <= 0){
			run.removeActionListener(this);
			pass.removeActionListener(this);
			punt.removeActionListener(this);
			kick.removeActionListener(this);
			
			pBP.setForeground(Color.RED);
			if(gInfo.getHomeScore()>gInfo.getAwayScore()){
				pBP.append("GAME OVER! The " + ht.getName()+ " Win!\n");
			}else{
				pBP.append("GAME OVER! The " + at.getName()+ " Win!\n");
			}
			
			vikes.addActionListener(this);
			vikesA.addActionListener(this);
			pack.addActionListener(this);
			packA.addActionListener(this);
			cats.addActionListener(this);
			catsA.addActionListener(this);
			cBear.addActionListener(this);
			cBearA.addActionListener(this);

		}
		
		if(gInfo.getQtr()>=4 && gInfo.getHomeScore()== gInfo.getAwayScore() && gInfo.getTime() <= 0){
			run.removeActionListener(this);
			pass.removeActionListener(this);
			punt.removeActionListener(this);
			kick.removeActionListener(this);
			
			pBP.setForeground(Color.RED);
			pBP.append("GAME OVER! The " + ht.getName() + " tied with " + at.getName()+ "!\n");
			
			vikes.addActionListener(this);
			vikesA.addActionListener(this);
			pack.addActionListener(this);
			packA.addActionListener(this);
			cats.addActionListener(this);
			catsA.addActionListener(this);
			cBear.addActionListener(this);
			cBearA.addActionListener(this);

		}
	}
	
	/* playScore method
	 * Passed: None
	 * Returned: None
	 * Method adds a score to appropriate team and repaints the GUI
	 * for a kickoff
	 */
	public void playScore(){
		kick.addActionListener(this);
		if(gInfo.getPossesion()){
			gInfo.setScoreTd(false);	
			run.removeActionListener(this);
			pass.removeActionListener(this);
			punt.removeActionListener(this);
			yardValue = 35;
			gInfo.setCurYard(yardValue);
			gInfo.setHomeScore(gInfo.getHomeScore()+7);
			play();
			mainGui.repaint();
			
		}else{		
			gInfo.setScoreTd(false);
			run.removeActionListener(this);
			pass.removeActionListener(this);
			punt.removeActionListener(this);
			yardValue = 65;
			gInfo.setCurYard(yardValue);
			gInfo.setAwayScore(gInfo.getAwayScore()+7);
			play();
			mainGui.repaint();
		}
	}
	
	/* play method
	 * Passed: None
	 * Returned: None
	 * Method changes all values per play and repaints the GUI
	 */
	public void play(){
		
		gInfo.setGameDown();
		gInfo.setGameClock();
		gInfo.setGameQtr();
		gInfo.setGameDist(gameYard);
		homeDis.setText(ht.getName()+": "+ gInfo.getHomeScore());
		awayDis.setText(at.getName()+": "+ gInfo.getAwayScore());
		timeDis.setText(gInfo.getTime()/60 + ":" + gInfo.getTime()%60);
		qtrDis.setText(""+gInfo.getQtr());
		downDis.setText(""+gInfo.getDown());
		distanceDis.setText(""+gInfo.getDist());
		
		if(gInfo.getCurYard()<50){
			yardDis.setText("< "+ gInfo.getCurYard());
		}else{
			yardDis.setText("> "+(100 - gInfo.getCurYard()));
		}
		
		mainGui.repaint();

	}
	
	/* specialTeamsPlay method
	 * Passed: None
	 * Returned: None
	 * Method establishes values for a special teams play
	 * and repaints the GUI
	 */
	public void specialTeamsPlay(){
		if(gInfo.getPossesion()){
			
			gameYard = gInfo.calcSpecial(ht.getSoRating(), at.getSdRating());
			
			switch(gameYard){
			case -101:
				changePos();
				changePos();
				yardValue=75;
				mainGui.repaint();
				checkClock();
				break;
			case -102:
				changePos();
				playScore();
				checkClock();
				break;
			case -103:
				changePos();
				yardValue=75;
				gInfo.setCurYard(yardValue);
				yardDis.setText("> "+(100 - gInfo.getCurYard()));
				mainGui.repaint();
				checkClock();
				break;
			}
		}else{
			gameYard = gInfo.calcSpecial(ht.getSoRating(), at.getSdRating());
			
			switch(gameYard){
			case -101:
				changePos();
				changePos();
				yardValue=25;
				mainGui.repaint();
				checkClock();
				break;
			case -102:
				changePos();
				playScore();
				checkClock();
				break;
			case -103:
				changePos();
				yardValue=25;
				gInfo.setCurYard(yardValue);
				yardDis.setText("< "+ gInfo.getCurYard());
				mainGui.repaint();
				checkClock();
				break;
			}
		}
	}
	
	/* puntDisplay method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP text area of the result of a punt
	 */
	public void puntDisplay(){
		
		if(gInfo.getPossesion()){
			if(yardValue==-101){
				pBP.append("Punt by " + ht.getPuntName() + at.getKickRetName() + " muffed the kick "
						+ " recovered by the " + ht.getName()+ ".\n");
			}else if(yardValue==-102){
				pBP.append("Punt by " + ht.getPuntName() + at.getKickRetName() +
					" returned the kick for a TOUCHDOWN!\n");
			}else{
				pBP.append("Punt by " + ht.getPuntName() + " for a touchback.\n");
			}
		}else{
			if(yardValue==-101){
				pBP.append("Punt by " + at.getPuntName() + ht.getKickRetName() + " muffed the kick "
						+ " recovered by the " + ht.getName()+ ".\n");
			}else if(yardValue==-102){
				pBP.append("Punt by " + at.getPuntName() + ht.getKickRetName() +
					" returned the kick for a TOUCHDOWN!\n");
			}else{
				pBP.append("Punt by " + at.getPuntName() + " for a touchback.\n");
			}
			
		}
	}
	
	/* kickDisplay method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP text area of the result of a kick
	 */
	public void kickDisplay(){
		run.addActionListener(this);
		pass.addActionListener(this);
		punt.addActionListener(this);
		kick.removeActionListener(this);
		start.addActionListener(this);
		if(gInfo.getPossesion()){
			if(yardValue==-101){
				pBP.append("Kickoff by " + ht.getKickName() + at.getKickRetName() + " muffed the kick "
						+ " recovered by the " + ht.getName()+ ".\n");
			}else if(yardValue==-102){
				pBP.append("Kickoff by " + ht.getKickName() + at.getKickRetName() +
					" returned the kick for a TOUCHDOWN!\n");
			}else{
				pBP.append("Kickoff by " + ht.getKickName() + " for a touchback.\n");
			}
		}else{
			if(yardValue==-101){
				pBP.append("Kickoff by " + at.getKickName() + ht.getKickRetName() + " muffed the kick "
						+ " recovered by the " + ht.getName()+ ".\n");
			}else if(yardValue==-102){
				pBP.append("Kickoff by " + at.getKickName() + ht.getKickRetName() +
					" returned the kick for a TOUCHDOWN!\n");
			}else{
				pBP.append("Kickoff by " + at.getKickName() + " for a touchback.\n");
			}
			
		}
		 
	}
	
	/* runDisplay method
	 * Passed: None
	 * Returned: None
	 * Method append the pBP text area of the result of a run play
	 */
	public void runDisplay(){
		if(gInfo.getPossesion()){
			pBP.append(ht.getQbName()+" Hands off to " + ht.getRbName() + " For " + gameYard + " yards.\n");
			
		}else{
			pBP.append(at.getQbName()+" Hands off to " + at.getRbName() + " For " + gameYard + " yards.\n");
		}
	}
	
	/* turnOver method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP text area of the result of a turnover.
	 */
	public void turnOver(){
		if(gInfo.getPossesion()){
			pBP.append(ht.getName()+" turnover the ball, " + at.getName()+" ball\n");
		}else{
			pBP.append(at.getName()+" turnover the ball, " + ht.getName()+" ball\n");
		}
	}
	
	/* runScore method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP text area of a run score
	 */
	public void runScore(){
		if(gInfo.getPossesion()){
			pBP.append(ht.getQbName()+" hands off to " + ht.getRbName() + " he goes in for the TOUCHDOWN!\n");
		}else{
			pBP.append(at.getQbName()+" hands off to " + at.getRbName() + " he goes in for the TOUCHDOWN!\n");
		}
	}
	
	/* passScore method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP area of a pass score
	 */
	public void passScore(){
		String wr = null;
		if(gInfo.getPossesion()){
			
			switch(wrNum()){
				case 0: 
					wr = ht.getWr1Name();
					break;
				case 1:
					wr = ht.getWr2Name();
					break;
				case 2:
					wr = ht.getWr3Name();
					break;
				case 3:
					wr = ht.getTeName();
					break;
			}
			
			pBP.append(ht.getQbName() + " passes to " + wr + " for a touchdown!\n");
		}else{
			
			switch(wrNum()){
				case 0: 
					wr = at.getWr1Name();
					break;
				case 1:
					wr = at.getWr2Name();
					break;
				case 2:
					wr = at.getWr3Name();
					break;
				case 3:
					wr = at.getTeName();
					break;
			}
			pBP.append(at.getQbName() + " passes to " + wr + " for a touchdown!\n");
				
		}
	}
	
	/* passDisplay method
	 * Passed: None
	 * Returned: None
	 * Method appends the pBP text area of the result of a pass play.
	 */
	public void passDisplay(){
		
		String wr = null;
		if(gInfo.getPossesion()){
			
			switch(wrNum()){
				case 0: 
					wr = ht.getWr1Name();
					break;
				case 1:
					wr = ht.getWr2Name();
					break;
				case 2:
					wr = ht.getWr3Name();
					break;
				case 3:
					wr = ht.getTeName();
					break;
			}
			
			if(gameYard == 0){
				
				pBP.append(ht.getQbName() + " throws an incomplete pass to " + wr + ".\n");
				
			}else if(gameYard > 0){
			
				pBP.append(ht.getQbName() + " completes pass to " + wr + " for " + gameYard + " yards.\n");
				
			}else{
				
				pBP.append(ht.getQbName()+ " sacked for " + gameYard + ".\n");
			}
		}else{
			
			switch(wrNum()){
				case 0: 
					wr = at.getWr1Name();
					break;
				case 1:
					wr = at.getWr2Name();
					break;
				case 2:
					wr = at.getWr3Name();
					break;
				case 3:
					wr = at.getTeName();
					break;
			}
			if(gameYard == 0){
				
				pBP.append(at.getQbName() + " throws an incomplete pass to " + wr + ".\n");
				 
			}else if(gameYard > 0){
				
				pBP.append(at.getQbName() + " completes pass to " + wr + " for " + gameYard + " yards.\n");
			}else{
				
				pBP.append(at.getQbName()+ " sacked for " + gameYard + ".\n");
			}
				
			
		}
	}
	
	/* wrNum method
	 * Passed: None
	 * Returned: integer
	 * Method create a random number 0-3 for the use to pick a wide receiver or tight end for the
	 * passPlay and passScore methods
	 */
	private int wrNum(){
		SecureRandom luck = new SecureRandom();
		return luck.nextInt(4);
	}
	
	/* Overridden actionPerformed method
	 * Passed: ActionEvent
	 * Returned: None
	 * Method checks for button presses and does an appropriate action for the relative 
	 * button press
	 */
	public void actionPerformed(ActionEvent e) {
		
		String button = e.getActionCommand();
		
		if(button.equals("Packers")){
			homeP = packP;
			ht = teamList.getTeam(2);
			mainGui.repaint();
		}
		
		if(button.equals("Vikings")){
			homeP = vikeP;
			ht = teamList.getTeam(0);
			mainGui.repaint();
		}
		
		if(button.equals("Lions")){
			homeP = lionsP;
			ht = teamList.getTeam(3);
			mainGui.repaint();
		}
		
		if(button.equals("Bears")){
			homeP = bearsP;
			ht = teamList.getTeam(1);
			mainGui.repaint();
		}
		if(button.equals("Vikings Away")){
			awayP = vikeP;
			at = teamList.getTeam(0);
			mainGui.repaint();
		}
		
		if(button.equals("Packers Away")){
			awayP = packP;
			at = teamList.getTeam(2);
			mainGui.repaint();
		}
		
		if(button.equals("Bears Away")){
			awayP = bearsP;
			at = teamList.getTeam(1);
			mainGui.repaint();
		}
		
		if(button.equals("Lions Away")){
			awayP = lionsP;
			at = teamList.getTeam(3);
			mainGui.repaint();
		}
		
		if(button.equals("Run/D-Run")){
			checkClock();
			if(gInfo.getPossesion()){
				gameYard = gInfo.calcYardRun(ht.getOrRating(), at.getDrRating());
				
				if(!gInfo.checkPos(gameYard)){
					turnOver();
					changePos();
					checkClock();
					
				}else{
					yardValue += gameYard;
					gInfo.setCurYard(yardValue);
					gInfo.score();
					if(gInfo.getScoreTd()){
						runScore();
						playScore();
						checkClock();
						
						
					}else{
						runDisplay();
						play();
						checkClock();
					}
				}
			
			}else{
				gameYard = gInfo.calcYardRun(at.getOrRating(), ht.getDrRating());
				if(!gInfo.checkPos(gameYard)){
					turnOver();
					changePos();
					checkClock();
					
				}else{
					if(gameYard==-102){
						gameYard=0;
						checkClock();
					}
					yardValue += -gameYard;
					gInfo.setCurYard(yardValue);
					gInfo.score();
					if(gInfo.getScoreTd()){
						runScore();
						playScore();
						checkClock();
					}else{
						runDisplay();
						play();
						checkClock();
					}
				}
			}		
		}
		
		if(button.equals("Pass/D-Pass")){
			checkClock();
			if(gInfo.getPossesion()){
				gameYard = gInfo.calcYardRun(ht.getOpRating(), at.getDpRating());
				
				if(!gInfo.checkPos(gameYard)){
					turnOver();
					changePos();
					checkClock();
				}else{
					yardValue += gameYard;
					gInfo.setCurYard(yardValue);
					gInfo.score();
					if(gInfo.getScoreTd()){
						passScore();
						playScore();
						checkClock();
					}else{
						passDisplay();
						play();
						checkClock();
					}
				}
			
			}else{
				gameYard = gInfo.calcYardRun(at.getOpRating(), ht.getDpRating());
			
				if(!gInfo.checkPos(gameYard)){
					turnOver();
					changePos();
					checkClock();
				
				}else{
					yardValue += -gameYard;
					gInfo.setCurYard(yardValue);
					gInfo.score();
					if(gInfo.getScoreTd()){
						passScore();
						playScore();
						checkClock();
					}else{
						passDisplay();
						play();
						checkClock();
					}
				}
			}		
		}
		
		if(button.equals("Kick/D-Kick")){
			checkClock();
			kickDisplay();
			specialTeamsPlay();
			
		}
		
		if(button.equals("Punt/D-Punt")){
			checkClock();
			puntDisplay();
			specialTeamsPlay();
			
		}
		
		if(button.equals("Start")){
			
			gInfo = new GameInfo();
			
			gInfo.setHomeScore(0);
			gInfo.setAwayScore(0);
		
			homeDis.setText(ht.getName()+": "+gInfo.getHomeScore());
			awayDis.setText(at.getName()+ ": "+ gInfo.getHomeScore());
			timeDis.setText(gInfo.getTime()/60 + ":" + gInfo.getTime()%60);
			qtrDis.setText(""+gInfo.getQtr());
			downDis.setText(""+gInfo.getDown());
			distanceDis.setText(""+gInfo.getDist());
			yardDis.setText("< "+ gInfo.getCurYard());
			pBP.setText("");
			pBP.setText("You must kick the ball to start the game!\n");
			
			yardValue = 35;
			
			pBP.setForeground(Color.YELLOW);
			kick.addActionListener(this);
			run.removeActionListener(this);
			pass.removeActionListener(this);
			punt.removeActionListener(this);
			start.removeActionListener(this);
			
			vikes.removeActionListener(this);
			vikesA.removeActionListener(this);
			pack.removeActionListener(this);
			packA.removeActionListener(this);
			cats.removeActionListener(this);
			catsA.removeActionListener(this);
			cBear.removeActionListener(this);
			cBearA.removeActionListener(this);

			
			
			mainGui.repaint();
			
		}
	}
}
