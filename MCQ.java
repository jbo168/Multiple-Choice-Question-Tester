import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class MCQ
{
	public static ArrayList<ArrayList<String>> topics;
	public static ArrayList<ArrayList<String>> questions;


	public static void main (String[] args) throws IOException
	{
		boolean readFile;
		readFile = readFilesIntoArrayLists();
		if(!readFile)
			System.out.println("One or more files do not exist");
		else
		{
			quizImplementation();
		}
	}

	public static boolean readFilesIntoArrayLists() throws IOException
	{
		String filename1 = "topics.txt";
		String filename2 = "questions.txt";

		File topicFile = new File(filename1);
		File questionFile = new File(filename2);
		String fileElements[];

		topics = new ArrayList<ArrayList<String>>();
		topics.add(new ArrayList<String>());
		topics.add(new ArrayList<String>());

		questions = new ArrayList<ArrayList<String>>();
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());
		questions.add(new ArrayList<String>());

		if(topicFile.exists() && questionFile.exists())
		{
			Scanner in = new Scanner(topicFile);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				topics.get(0).add(fileElements[0]);
				topics.get(1).add(fileElements[1]);
			}
			in.close();
			in = new Scanner(questionFile);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				questions.get(0).add(fileElements[0]);
				questions.get(1).add(fileElements[1]);
				questions.get(2).add(fileElements[2]);
				questions.get(3).add(fileElements[3]);
				questions.get(4).add(fileElements[4]);
				questions.get(5).add(fileElements[5]);
				questions.get(6).add(fileElements[6]);
				questions.get(7).add(fileElements[7]);
				questions.get(8).add(fileElements[8]);
			}
			in.close();
			return true;
		}else
			return false;

	}

	/* 
		John Long - 12132306
		This method dynamically gets the topic selection from the user 
		and returns this as a String
	*/

	public static String getTopicOption()
	{
		String topicOptionNums = "";
		String topicOptionText = "";
		String topicOptions = "";
		String selection = "";
		int topicNumListSize = topics.get(0).size();                                               // **
		int topicTextListSize = topics.get(1).size();											   // **

		// get topic numbers and question text from topics file
		for(int i=0; i<topicNumListSize && i<topicTextListSize; i++)                        
		{
			topicOptionNums = topics.get(0).get(i);                                                 // **
			topicOptionText = topics.get(1).get(i);                                                 // **
			topicOptions += topicOptionNums + " " + topicOptionText + "\n";
		}

		String menuMessage = "Select which topics test you would like to take";
		String errorMessage =  "Invalid menu selection.\n\nValid options are 0 to " + topicNumListSize + " inclusive.";
       	   errorMessage += "\nSelect OK to retry or cancel to exit.";
		String errorHeader = "Error in user input";       	
       	boolean validInput = false;
       	String menuChoicePattern = "[1-4]{1}"; // **need to modify to dynamically perform**
		
		while (!(validInput))
    	{
      		selection = JOptionPane.showInputDialog(null, topicOptions, menuMessage, 3);
      		if (selection == null || selection.matches(menuChoicePattern))
       		validInput = true;
      		else
       		JOptionPane.showMessageDialog(null, errorMessage, errorHeader, 2);
    	}	

    	return selection;
	}

	/*
		John Long - 12132306
		This method runs the quiz for the user
	*/
	public static void quizImplementation()
	{
		int selection = Integer.parseInt(getTopicOption());
		String fileElements[];
		String qOptions = "";
		String question = "";
		String topicHeader = "";

		
		for(int i=0; i<questions.get(0).size(); i++)
		{		
			if(Integer.parseInt(questions.get(i).get(0)) == selection)
			{
				topicHeader += topics.get(1).get(selection);
				qOptions += questions.get(2).get(i) + "\n";
				qOptions += "Enter the number of the chosen answer.\n";
				qOptions += "1. " + questions.get(3).get(i) + "\n";
				qOptions += "2. " + questions.get(4).get(i) + "\n";
				qOptions += "3. " + questions.get(5).get(i) + "\n";
				qOptions += "4. " + questions.get(6).get(i) + "\n";

				question = JOptionPane.showInputDialog(null,qOptions,topicHeader);
       		}
       	}
	}
}