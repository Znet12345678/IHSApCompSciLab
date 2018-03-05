import java.util.*;
class Utility
{
    private static Scanner keyboard = new Scanner(System.in);

    /** This method prompts a user to enter
     *  an integer value from the keyboard. It
     *  then reads and returns the integer value  
     *  using the Scanner class's nextInt method.
     *  @param prompt the user prompt
     *  @return the value read from keyboard
     */
    public static int readInt(String prompt)
    {
		System.out.printf("%s",prompt);
		int n = keyboard.nextInt();
		keyboard.nextLine();
		return n;
    }

    /** This method prompts a user to enter
     *  a double value from the keyboard. It
     *  then reads and returns the double value  
     *  using the Scanner class's nextDouble method.
     *  @param prompt the user prompt
     *  @return the value read from keyboard
     */
    public static double readDouble(String prompt)
    {
		System.out.printf("%s",prompt);
		double n = keyboard.nextDouble();
		keyboard.nextLine();
		return n;
    }

    /** This method prompts a user to enter
     *  a string from the keyboard. It then
     *  reads and returns the string using  
     *  the Scanner class's nextLine method.
     *  @param prompt the user prompt
     *  @return the value read from keyboard
     */
    public static String readString(String prompt)
    {
		System.out.printf("%s",prompt);
		String str = keyboard.nextLine();
		return str;
    }

    /** This method prints blank lines on the
     *  console window.
     *  @param num the number of lines to display
     */
    public static void blankLines(int num)
    {
		for(int i = 0; i < num;i++)
			System.out.println();
    }
}

class MethodPractice2{
		public void greeting(String firstName,int num){
			for(int i = 0; i < num;i++)
				System.out.printf("%s, have a nice day\n",firstName);
		}
		public void range(int begin,int end){
			for(int i = begin; i <= end;i++)
				System.out.println(i);
		}
		public boolean compare(String str){
			return str.substring(0,1).equals(str.substring(str.length()-1,str.length()));
		}
		public String reverse(String str){
			String ret = new String();
			for(int i = 1; i <= str.length();i++)
				ret=str.substring(i-1,i)+ret;
			return ret;
		}
		public int numOccurrences(String word,String letter){
			int ret = 0;
			for(int i = 1; i <= word.length();i++)
					if(word.substring(i-1,i).equals(letter))
						ret++;
			return ret;
		}
}
 class MethodPractice1
{
    private Scanner keyboard = new Scanner(System.in);

    /** This method returns a string using the following
     *  conditions: if weight is less than 100 it returns
     *  "small", if weight is greater than or equal to 100
     *  and less than or equal to 200 it returns "medium",
     *  if weight is greater than 200 it returns "large".
     *  @return the string "small", "medium", or "large"
     *  @param weight number representing a weight
     */
    public String method1(int weight)
    {
		if(weight < 100)
			return "small";
		else if(weight <= 200)
			return "medium";
		else 
			return "large";
    }

    /** This method prints phrase 10 times.
     *  @ param phrase the string to be printed
     */
    public void method2(String phrase)
    {
		for(int i = 0; i < 10;i++)
			System.out.println(phrase);
    }

    /** This method allows a user to enter an unknown
     *  number of integers from the keyboard. When the
     *  sentinel value -1 is entered the method returns
     *  the count of the number of integers entered.
     *  @return count of the number of integers entered
     */
    public int method3()
    {
		Scanner sc = new Scanner(System.in);
		int ret = 0;
		System.out.println("Enter numbers-->");
		while(true){
			try{
				int n = sc.nextInt();
				sc.nextLine();
				if(n == -1)
					return ret;
				ret++;
			}catch(Exception e){
				System.out.println("Enter an int please");
				sc.nextLine();
			}
			

		}
    }

    /** This method returns a string containing the
     *  first and last letter of str concatenated
     *  together.
     *  @return a string containing two letters
     *  @param str the string from which to extract
     *     the first and last letters
     */
    public String method4(String str)
    {
		return str.substring(0,1) + str.substring(str.length()-1,str.length());
    }

    /** This method returns a random number.
     *  @return a random number in range of 0 to upper-1
     *  @param upper the upper limit of the random number
     */
    public int method5(int upper)
    {
		return (int)(Math.random() * upper);
    }
}
class GuessingGame{
	int num;
	int attempts ;
	public GuessingGame(){
		num = (int)(Math.random() * 100 + 1);
		attempts = 0;
	}
	public void Play(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Try to guess my number!\n");
		while(true){
			System.out.print(">");
				try{
					int n = sc.nextInt();
					sc.nextLine();
					if(n == num){
						System.out.printf("You found it in %d tries\n",attempts);
						return;
					}else if(n < num)
						System.out.printf("Too low, Try Again.\n");
					else
						System.out.printf("Too high, Try Again.\n");
					attempts++;
				}catch(Exception e){
					System.out.println("Enter an int please");
					sc.nextLine();
				}
		}
	}
}
public class MethodPractice{
    public static void main(String[] args)
    {
		System.out.println("Play Guessing game(y)?");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		if(str.equals("y")){
			GuessingGame g = new GuessingGame();
			g.Play();
		}
        MethodPractice1 app = new MethodPractice1();

        System.out.println("******************");
        System.out.println("   Test Method1");
        System.out.println("******************");

        System.out.println("Weight is " + app.method1(150));
        System.out.println("Weight is " + app.method1(99));
        System.out.println("Weight is " + app.method1(200));
        System.out.println("Weight is " + app.method1(300));

        System.out.println("\n\n******************");
        System.out.println("   Test Method2");
        System.out.println("******************");
        app.method2("Computers are fun!");

        System.out.println("\n\n******************");
        System.out.println("   Test Method3");
        System.out.println("******************");
        System.out.println("\nCount = " + app.method3());

        System.out.println("\n\n******************");
        System.out.println("   Test Method4");
        System.out.println("******************");
        System.out.println("String = " + app.method4("red"));
        System.out.println("String = " + app.method4("green"));
        System.out.println("String = " + app.method4("blue"));

        System.out.println("\n\n******************");
        System.out.println("   Test Method5");
        System.out.println("******************");
        System.out.println("Random Number = " + app.method5(5));
        System.out.println("Random Number = " + app.method5(50));
        System.out.println("Random Number = " + app.method5(500));
        System.out.println();
		MethodPractice2 app2 = new MethodPractice2();

        System.out.println("******************");
        System.out.println("   Test greeting");
        System.out.println("******************");
        app2.greeting("Kendall", 6);

        System.out.println("\n\n******************");
        System.out.println("   Test range");
        System.out.println("******************");
        app2.range(10, 15);

        System.out.println("\n\n******************");
        System.out.println("   Test compare");
        System.out.println("******************");
        System.out.println(app2.compare("demand"));
        System.out.println(app2.compare("football"));
        System.out.println(app2.compare("bulb"));

        System.out.println("\n\n******************");
        System.out.println("   Test reverse");
        System.out.println("******************");
        System.out.println(app2.reverse("ball"));
        System.out.println(app2.reverse("courage"));
        System.out.println(app2.reverse("hamburger"));

        System.out.println("\n\n******************");
        System.out.println("   Test numOccurrences");
        System.out.println("******************");
        System.out.println(app2.numOccurrences("MISSISSIPPI", "I"));
        System.out.println(app2.numOccurrences("AUTOMOBILE", "O"));
        System.out.println(app2.numOccurrences("TEXAS", "R"));
        System.out.println();
		int iNum = Utility.readInt("Enter an integer -->");
        System.out.println("The number = " + iNum);

        Utility.blankLines(2);

        double dNum = Utility.readDouble("Enter a double -->");
        System.out.println("The number = " + dNum);

        Utility.blankLines(2);

        str = Utility.readString("Enter a string -->");
        System.out.println("The string = " + str);

        Utility.blankLines(2);

		}
}  
