/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * This version uses an array to hold the default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie5
{
	private static final String[] RANDOM_RESPONSES = {
		"Interesting, tell me more",
		"Hmmm.",
		"Do you really think so?",
		"You don't say.",
		"Kys",
		"No u",
		"Your mom has big gay"
	};

	/**
	 * Get a default greeting
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			response = "Tell me more about your family.";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		//  Part of student solution
		else if (findKeyword(statement, "I want", 0) >= 0)
		{
			response = transformIWantStatement(statement);
		}

		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int pos = findKeyword(statement, "you", 0);

			if (pos >= 0 && findKeyword(statement, "me", pos) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				//  Part of student solution
				// Look for a two word (I <something> you)
				// pattern
				pos = findKeyword(statement, "i", 0);

				if (pos >= 0 && findKeyword(statement, "you", pos) >= 0)
				{
					response = transformIYouStatement(statement);
				}
				else
				{
					response = getRandomResponse();
				}
			}
		}
		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int pos = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(pos + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "I want <something>." and transform it into
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int pos = findKeyword(statement, "I want", 0);
		String restOfStatement = statement.substring(pos + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "you <something> me" and transform it into
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement,
	 *        assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}

		int posOfYou = findKeyword(statement, "you", 0);
		int posOfMe = findKeyword(statement, "me", posOfYou + 3);

		String restOfStatement =
			statement.substring(posOfYou + 3, posOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}

	/**
	 * Take a statement with "I <something> you" and transform it into
	 * "Why do you <something> me?"
	 * @param statement the user statement,
	 *        assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}

		int posOfI = findKeyword(statement, "I", 0);
		int posOfYou = findKeyword(statement, "you", posOfI);

		String restOfStatement = statement.substring(posOfI + 1, posOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}




	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of
	 * a longer string (so, for example, "I know" does not contain "no").
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or
	 *         -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int pos = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		//  Refinement--make sure the goal isn't part of a word
		while (pos >= 0)
		{
			//  Find the string of length 1 before and after the word
			String before = " ";
			String after = " ";
			if (pos > 0)
			{
				before = phrase.substring(pos - 1, pos).toLowerCase();
			}
			if (pos + goal.length() < phrase.length())
			{
				after = phrase.substring(pos + goal.length(),
				        pos + goal.length() + 1).toLowerCase();
			}

			//  If before and after aren't letters, we've found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0))
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
			{
				return pos;
			}

			//  The last position didn't work, so let's find the next,
			//  if there is one.
			pos = phrase.indexOf(goal.toLowerCase(), pos + 1);
		}

		return -1;
	}

	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of
	 * a longer string (so, for example, "I know" does not contain "no").
	 * The search begins at the beginning of the string.
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or
	 *         -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword(statement, goal, 0);
	}



	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		double r = Math.random();
		return RANDOM_RESPONSES[(int)(r * RANDOM_RESPONSES.length)];
	}
}
import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner2
{
	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Magpie2 maggie = new Magpie2();

		System.out.println(maggie.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();

		while (!statement.equals("Bye"))
		{
			System.out.println(maggie.getResponse(statement));
			statement = in.nextLine();
		}
	}
}
