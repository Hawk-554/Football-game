package edu.century.football;
/****************************************************************************************
****************************************************************************************
Programmer: Adam (AJ) Kiheri
Class: Data Structures and Algorithms 2082 
PA: Project
Program: TeamCollection class object
*****************************************************************************************
****************************************************************************************/
import java.util.ArrayList;

 
/* TeamCollection class object
 * creates and ArrayList of Teams class objects
 */
public class TeamCollection {
	
	private ArrayList<Teams> teamList;
	
	/* No argument constructor
	 * creates the ArrayList
	 */
	public TeamCollection(){
		
		teamList = new ArrayList<>();
	}
	
	/* addTeam method
	 * Passed: Teams class object
	 * Returned: None
	 * Method adds a Teams class object to the teamList
	 * ArrayList 
	 */
	public void addTeam (Teams team)
	{
		teamList.add(team);
	}
	
	/* deleteTeam method
	 * Passed: Teams class Object
	 * Returned: None
	 * Method deletes a Teams class object from the teamList
	 * ArrayList
	 */
	public void deleteTeam(Teams team)
	{
		teamList.remove(team);
	}
	
	/* getTeam method
	 * Passed: integer
	 * Returned: Teams class object
	 * Method takes an integer for the index of the ArrayList
	 * and returns the Teams class object that is at that index in the
	 * ArrayList
	 */
	public Teams getTeam(int index){
		return teamList.get(index);
	}
	
	/* Overridden toString method 
	 * Passed: None
	 * Returned: String
	 * Method assigns the entire ArrayList to a String class object
	 */
	public String toString()
	{
		String str = null;
		
		for(Teams team : teamList)
		{	
			if (str == null)
			{
				str = team.toString();
			}
			else
				str += team.toString();
		}
		
		return str;
	}
	
}
