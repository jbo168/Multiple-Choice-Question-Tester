import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class MCQ
{
	public static ArrayList<ArrayList<String>> topics;
	public static ArrayList<ArrayList<String>> questions;
	public static ArrayList<ArrayList<String>>  users;
	public static ArrayList<ArrayList<String>>	tempQ;


	public static void main (String[] args) throws IOException
	{
		boolean readFile;
		readFile = readFilesIntoArrayLists();

		if(!readFile)
			System.out.println("One or more files do not exist");
		else
		{
			String[] details = usernameValidation();
	        if(details[0]!= "") //Empty string is assigned to the first index if the username
	        {                   //and password are invalid in usernameValidation method
	            String username = details[0];
	            int userScore = Integer.valueOf(details[1]); //Gave the details simple variable names for ease of use
	        }
			quizImplementation();
		}
	}

	/*
		John Long - 12132306
		This method runs the quiz for the user
	*/
	public static boolean readFilesIntoArrayLists() throws IOException
	{
		String filename1 = "topics.txt";
		String filename2 = "questions.txt";
		String filename3 = "users.txt";

		File topicFile = new File(filename1);
		File questionFile = new File(filename2);
		File usersFile = new File(filename3);
		String fileElements[];

		users = new ArrayList<ArrayList<String>>();
		users.add(new ArrayList<String>());
		users.add(new ArrayList<String>());
		users.add(new ArrayList<String>());


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
			Scanner in = new Scanner(usersFile);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				users.get(0).add(fileElements[0]);
				users.get(1).add(fileElements[1]);
				users.get(2).add(fileElements[2]);
			}
			in.close();

			in = new Scanner(topicFile);
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
	public static String[] usernameValidation() throws IOException
	{
		boolean validInput = false;
		int chances = 3;
		String username = new String();
		String password = new String();
		String userScore = new String();

		while(!validInput && chances!=0)
		{
			username = JOptionPane.showInputDialog(null,"Enter your username you have only " + chances + " chance(s) remaining");
			password = JOptionPane.showInputDialog(null,"Enter your password"); //Allows user to enter name and password
			for(int j = 0;j<users.get(0).size();j++)							//and stores them in variables
			{		
				//goes through each element in the list
				if(username.equals(users.get(0).get(j)) && password.equals(users.get(1).get(j))) //compares details entered to each element
				{																				//in the array lists
					validInput = true;
					JOptionPane.showMessageDialog(null,"Login details are valid");
					userScore = users.get(2).get(j); //Since we have username, we must access score when details are at correct index
				}
			}
			if(!validInput)
			{
				chances--; //decrement chances if the details entered are incorrect
				JOptionPane.showMessageDialog(null,"Login details entered were invalid");
			}
		}
		if(validInput)
		{
			String[] details =  {username,userScore};
			return details; //returns the details required 
		}
		else
		{
			String[] details = {"",""};
			return details; //If the input is incorrect, return these values
		}
	
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
		int topicNumListSize = topics.get(0).size();                                               
		int topicTextListSize = topics.get(1).size();	


		// get topic numbers and question text from topics file
		for(int i=0; i<topicNumListSize && i<topicTextListSize; i++)                        
		{
			topicOptionNums = topics.get(0).get(i);                                                 
			topicOptionText = topics.get(1).get(i);                                                 
			topicOptions += topicOptionNums + " " + topicOptionText + "\n";
		}

		String menuMessage = "Select which topics test you would like to take";
		String errorMessage =  "Invalid menu selection.\n\nValid options are 0 to " + topicNumListSize + " inclusive.";
       	   errorMessage += "\nSelect OK to retry or cancel to exit.";
		String errorHeader = "Error in user input";       	
       	boolean validInput = false;
       	String pattern = "[0-4]{1}";
       	
		while (!(validInput))
    	{
      		selection = JOptionPane.showInputDialog(null, topicOptions, menuMessage, 3);
      		if (selection == null || selection.matches(pattern) )
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
		String qOptions = "";
		String question = "";
		String topicHeader = "";
		String msg1 = "Enter the number of the chosen answer.";
		String incorrectMsg = "Incorrect answer \n";
		int questionNum = 0;

		tempQ = new ArrayList<ArrayList<String>>();
		tempQ.add(new ArrayList<String>()); // Q num
		tempQ.add(new ArrayList<String>()); // Q
		tempQ.add(new ArrayList<String>()); // PA1
		tempQ.add(new ArrayList<String>()); // PA2
		tempQ.add(new ArrayList<String>()); // PA3
		tempQ.add(new ArrayList<String>()); // PA4
		tempQ.add(new ArrayList<String>()); //	A
		tempQ.add(new ArrayList<String>()); // Explanation

		// copies multi-dim array to temp multi-dim array
		for(int i=0; i<questions.get(0).size(); i++)
		{		
			if(Integer.parseInt(questions.get(0).get(i)) == selection)
			{
				tempQ.get(0).add(questions.get(1).get(i));
				tempQ.get(1).add(questions.get(2).get(i));
				tempQ.get(2).add(questions.get(3).get(i));
				tempQ.get(3).add(questions.get(4).get(i));
				tempQ.get(4).add(questions.get(5).get(i));
				tempQ.get(5).add(questions.get(6).get(i));
				tempQ.get(6).add(questions.get(7).get(i));
				tempQ.get(7).add(questions.get(8).get(i));
       		}
       		
       	}
       	
       	int arrSize = tempQ.get(0).size();
       	Random rnd = new Random();
       	int num = 0;
       	
       	for(int i=0; i<tempQ.get(0).size(); i++)
       	{
       		num = rnd.nextInt(arrSize);
	       	// choose questions at random if question number greater than 10
	       	if(arrSize>100)
	       	{
	       		topicHeader += topics.get(selection).get(0);
				qOptions = tempQ.get(1).get(num) + "\n";	// Q 
				qOptions += "1. " + tempQ.get(2).get(num) + "\n";	// PA1
				qOptions += "2. " + tempQ.get(3).get(num) + "\n";	// PA2 
				qOptions += "3. " + tempQ.get(4).get(num) + "\n";	// PA3	
				qOptions += "4. " + tempQ.get(5).get(num) + "\n";	// PA4

				question = JOptionPane.showInputDialog(null,qOptions,topicHeader,3);
       			if(question != tempQ.get(6).get(num))
       				JOptionPane.showMessageDialog(null,incorrectMsg + tempQ.get(7).get(num),"Explanation",2);
	       	}
	        else
	        // select available questions
	        {
	        	topicHeader += topics.get(selection).get(0);
				qOptions = tempQ.get(1).get(i) + "\n";	// Q 
				qOptions += "1. " + tempQ.get(2).get(i) + "\n";	// PA1
				qOptions += "2. " + tempQ.get(3).get(i) + "\n";	// PA2 
				qOptions += "3. " + tempQ.get(4).get(i) + "\n";	// PA3	
				qOptions += "4. " + tempQ.get(5).get(i) + "\n";	// PA4

				question = JOptionPane.showInputDialog(null,qOptions,topicHeader,3);
       			if(question != tempQ.get(6).get(i))
       				JOptionPane.showMessageDialog(null,incorrectMsg + tempQ.get(7).get(i),"Explanation",2);
	        }
    	}
	}

	
}