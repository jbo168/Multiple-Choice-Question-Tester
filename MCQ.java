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

	public static void quizImplementation()
	{
		selection = getTopicOption();
	}
}