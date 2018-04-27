/**
 * EvilHangman.java  06/04/2015
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */


import java.util.*;
import java.io.*;
public class EvilHangman
{
	
	private boolean debug;
	private Scanner in;
	private Scanner fScnr;
	private ArrayList<String> wordList;
	private int wordLength;
	private int remainingGuesses;
	private String solution;
	private String guessedLetters;


	/**
	 * Construct an EvilHangman object.
	 * @param fineName the name of the file that contains the word list.
	 * @param debug indicates if the size of the remaining word list
	 *        should be included in the toString result.
	 * @throws FileNotFoundException when the fileName file does not exist.
	 */
	 public static void error(){
		 System.out.printf("I/O Error. Either the file does not exist or you do not have the permissions to access it\n");
	 }
   public EvilHangman(String fileName, boolean debug){
	  
		try{
				fScnr = new Scanner(new File(fileName));
				in = new Scanner(System.in);
				this.debug = debug;
				wordList = new ArrayList<String>();
				System.out.printf("Enter number of guesses>");
				remainingGuesses = in.nextInt();
				inputWords(fileName);
				guessedLetters = new String();
				solution = new String();
				for(int i = 0; i < wordLength;i++)
					solution+="-";
				
		}catch(IOException e){
			error();
		}
   }

	/**
	 * Plays one the game.  The user guesses letters until either
	 * they guess the word, or they run out of guesses.
	 * Game status is printed each turn and winning / losing
	 * information is printed at the conclusion of the game.
	 */
   public void playGame()
   {
		while(hasDash() && remainingGuesses > 0){
			System.out.println(this);
			String l = inputLetter();
			guessedLetters+=l;
			ArrayList <Partition>plst = getPartitionList(l);
			removeSmallerPartitions(plst);
			wordList = getWordsFromOptimalPartition(plst);
			String sol = solution;
			substitute(wordList.get(0),l);
			if(sol.equalsIgnoreCase(solution))
				remainingGuesses--;
		}
		if(remainingGuesses > 0)
			System.out.println("You win,congratulations!");
		else
			System.out.println("You lose, sorry!");
		int rand = (int)(Math.random()*wordList.size());
		System.out.printf("The word was %s\n",wordList.get(rand));
   }

	/**
	 * Creates and returns a status string that indicates the
	 * state of the game.
	 * @return the game status string.
	 */
   public String toString()
   {
	   if(debug)
			return String.format("Remaining guesses: %d\nGuessed letters: %s\nSolution: %s\nRemaining words: %d\n",remainingGuesses,guessedLetters,solution,wordList.size());
		else
			return String.format("Remaining guesses: %d\nGuessed letters: %s\nSolution: %s\n",remainingGuesses,guessedLetters,solution);
   }


	////////// PRIVATE HELPER METHODS //////////

	/**
	 * Helper method for the constructor:
	 * Inputs the word length from the user, reads in the words from
	 * the fileName file, and initializes the wordList instance variable
	 * with the words of the correct length.  This method loops until
	 * a valid word length is entered.
	 * @param fineName the name of the file that contains the word list.
	 * @throws FileNotFoundException when the fileName file does not exist.
	 */
   private void inputWords(String fileName) throws IOException
   {
			System.out.print("Enter word length>");
			wordLength = in.nextInt();
			in.nextLine();
			while(fScnr.hasNext()){
				String word = fScnr.next();
				if(word.length() == wordLength)
					wordList.add(word);
			}
			if(wordList.size() == 0){
				System.out.printf("There are no words with %d letters.\n",wordLength);
			}
   }

	/**
	 * Helper method for playGame:
	 * Inputs a one-letter string from the user.
	 * Loops until the string is a one-character character in the range
	 * a-z or A-Z.
	 * @return the single-letter string converted to upper-case.
	 */
	private String inputLetter()
	{
		while(true){
			System.out.printf("Enter one letter$");
			String str = in.nextLine();
			if(str.length() == 1)
				return str;
		}
	}

	/**
	 * Helper method for getPartitionList:
	 * Uses word and letter to create a pattern.  The pattern string
	 * has the same number of letter as word.  If a character in
	 * word is the same as letter, the pattern is letter; Otherwise
	 * it's "-".
	 * @param word the word used to create the pattern
	 * @param letter the letter used to create the pattern
	 * @return the pattern
	 */
	
	private String getPattern(String word, String letter)
	{
		String ret = new String();
		for(int i = 0; i < word.length();i++){
			String l = word.substring(i,i+1);
			if(l.equalsIgnoreCase(letter))
				ret+=l;
			else
				ret+="-";
		}
		return ret;
	}

	/**
	 * Helper method for playGame:
	 * Partitions each string in wordList based on their patterns.
	 * @param letter the letter used to find the pattern for
	 *        each word in wordList.
	 * @return the list of partitions.
	 */
	private ArrayList<Partition> getPartitionList(String letter)
	{
		ArrayList <Partition>ret = new ArrayList<Partition>();
		for(String str : wordList){
			String pattern = getPattern(str,letter);
			boolean found = false;
			for(Partition p : ret){
				if(p.addIfMatches(pattern,str))
					found = true;
			}
			if(!found)
				ret.add(new Partition(pattern,str));
		}
		return ret;
	}

	/**
	 * Helper method for playGame:
	 * Removes all but the largest (most words) partitions from partitions.
	 * @param partitions the list of partitions.
	 *        Precondition: partitions.size() > 0
	 * Postcondition: partitions
	 * 1) contains all of the partitions with the most words.
	 * 2) does not contain any of the partitions with fewer than the most words.
	 */
	private void removeSmallerPartitions(ArrayList<Partition> partitions)
	{
		int max = Integer.MIN_VALUE;
		int prevIndx = -1;
		for(int i = 0; i < partitions.size();i++){
				Partition p = partitions.get(i);
				max = Math.max(p.getWords().size(),max);
				if(max == p.getWords().size()){
					if(prevIndx >= 0)
						partitions.remove(prevIndx);
					prevIndx = i;
					max = p.getWords().size();
				}
		}
	}

	/**
	 * Helper method for playGame:
	 * Returns the words from one of the optimal partitions,
	 *    that is a partition with the most dashes in its pattern.
	 * @param partitions the list of partitions.
	 * @return the optimal partition.
	 */
	private ArrayList<String> getWordsFromOptimalPartition(ArrayList<Partition> partitions)
	{
		int maxDash = -1;
		Partition ref = null;
		for(Partition p : partitions){
			if(p.getPatternDashCount() > maxDash){
				maxDash = p.getPatternDashCount();
				ref = p;
			}
		}
		return ref.getWords();
	}

	/**
	 * Helper method for playGame:
	 * Creates a new string for solution.  If the ith letter of
	 * found equals letter, then the ith letter of solution is
	 * changed to letter; Otherwise it is unchanged.
	 * @param found the string to check for occurances of letter.
	 * @param letter the letter to check for in found.
	 */
	private void substitute(String found, String letter)
	{
		String dup = solution;
		solution = new String();
		for(int i = 0; i < found.length();i++){
			if(found.substring(i,i+1).equalsIgnoreCase(letter)){
				solution+=letter;
			}else{
				solution+=dup.charAt(i);
			}
		}
	}
	private boolean hasDash(){
		for(int i = 0; i < solution.length();i++)
			if(solution.substring(i,i+1).equalsIgnoreCase("-"))
				return true;
		return false;
	}
}
