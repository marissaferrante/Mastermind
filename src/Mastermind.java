import java.util.Random;
import java.util.Scanner;

public class Mastermind
{
    static Scanner input;

    public static void main(String[] args)
    {
        Game secretGameWord;
        Game playerGuess = new Game();
        String playerEntry;
        int level; 
        
        input = new Scanner(System.in);
        String[] gameArray = chooseGameArray();
        level = chooseGameLevel();
        String randWord = chooseRandomWord(gameArray, level);
        secretGameWord = new Game(randWord, level);
        
        displayPrompt(level);

        do
        {
            playerEntry = getPlayerGuess(secretGameWord);
            playerGuess.setGameWord(playerEntry);
            playerGuess.setGameLevel(playerEntry.length());
            
            if (playerEntry.equals("q"))
                break;
             
            boolean validGuess = playerGuess.setGameWord(playerEntry);
            secretGameWord.displayComputerResponse(playerGuess, secretGameWord,
                    validGuess);
            
        } while (!playerGuess.toString().equals(secretGameWord.toString()));
        
        sayThanks(playerEntry, secretGameWord);
    }
    
    // determines the game level. The level is based on the number of letters 
    // the secret word
    public static int chooseGameLevel()
    {
        String levelPrompt, strUserResponse;
        int BEGINNER_WORD_LENGTH = 3;
        int INTERMEDIATE_WORD_LENGTH = 4;
        int ADVANCED_WORD_LENGTH = 5;
        
        levelPrompt = "\nComputer: What level would you like to play?\n\tPlease"
                + " type:\n\t\t'B' for Beginner\n\t\t'I' for Intermediate\n\t\t"
                + "'A' for Advanced\n\nPlayer: ";
        
        while(true)
        {
            System.out.print(levelPrompt);
            strUserResponse = input.nextLine();
            strUserResponse = strUserResponse.toUpperCase(); 
            
            switch (strUserResponse.charAt(0))
            {
                case 'B': 
                    return BEGINNER_WORD_LENGTH;
                case 'I':
                    return INTERMEDIATE_WORD_LENGTH;
                case 'A':
                    return ADVANCED_WORD_LENGTH;
                default: 
                    System.out.print("Selection not found. Please try again."
                            + "\n\n");
            } 
        }
    }
    
    public static String[] chooseGameArray()
    {
        String intro, instructions, strUserResponse;
        String[] animals = {"deer", "bear", "fish", "frog", "mole", "vole", 
                "boar", "bull", "toad", "worm", "crow", "puma", "wolf", "newt",
                "duck", "swan", "hawk", "goat", "hare", "crab", "wren", "dodo", 
                "kudu", "lamb", "lion", "lynx", "dove", "puma", "seal", "mink",
                "mule", "orca", "ape", "bat", "cat", "cow", "dog", "elk", "fox",
                "hog", "pig", "ram", "rat", "yak", "bison", "camel", "dingo", 
                "hippo", "hyena", "horse", "koala", "lemur", "llama", "moose", 
                "otter", "panda", "zebra", "whale", "tiger", "sloth", "sheep", 
                "rhino", "shrew", "skunk"};
        String[] food = {"pear", "tofu", "beef", "rice", "bean", "lime", 
                "plum", "milk", "wine", "beer", "fish", "crab", "pork", "beet",
                "corn", "date", "kale", "kiwi", "leek", "okra", "taro", "pita",
                "brie", "ghee", "feta", "fig", "pea", "yam", "egg", "oat", "rye"
                ,"chard", "apple", "chive", "grape", "lemon", "mango", "melon", 
                "guave", "olive", "onion", "peach", "prune", "bread", "farro", 
                "spelt"};
        String[] names = {"James", "John", "David", "Daniel", "Mark", "Paul", 
                "Kevin", "Brian", "Jason", "Ryan", "Gary", "Jacob", "Eric", 
                "Larry", "Frank", "Jack", "Jerry", "Tyler", "Henry", "Jose", 
                "Peter", "Kyle", "Carl", "Adam", "Mary", "Linda", "Susan", 
                "Sarah", "Karen", "Nancy", "Betty", "Lisa", "Donna", "Carol",
                "Emily", "Helen", "Laura", "Amy", "Anna", "Ruth", "Debra", 
                "Janet", "Emma", "Maria", "Diana", "Julie", "Joyce", "Joan",
                "Kelly", "Keith", "Terry", "Sean", "Joe", "Ethan", "Jesse",
                "Billy", "Bryan", "Bruce", "Ralph", "Roy", "Noah", "Dylan", 
                "Wayne", "Alan", "Juan", "Louis", "Randy", "Harry", "Bobby",
                "Logan", "Megan", "Ann", "Alice", "Jean", "Doris", "Marie", 
                "Sara", "Julie", "Grace", "Judy", "Rose", "Amber", "Diane", 
                "Jane", "Lori", "Kayla"};
        String[] elements = {"gold", "iron", "lead", "neon", "zinc", "tin", 
                "argon", "boron", "radon", "xenon"};
        
        intro = "\t\t\tWelcome to Mastermind!\n\n";
        instructions = "Computer: What Mastermind category would you like to "
                + "play?\n\tPlease type: \n\t\t'A' for animals\n\t\t'F' for "
                + "foods\n\t\t'N' for names\n\t\t'E' for elements\n\nPlayer: ";
        
        System.out.print(intro);
        
        while(true)
        {
            System.out.print(instructions);
            strUserResponse = input.nextLine();
            strUserResponse = strUserResponse.toUpperCase();
            switch (strUserResponse.charAt(0))
            {
                case 'A': 
                    return animals;
                case 'F':
                    return food;
                case 'N':
                    return names;
                case 'E':
                    return elements;
                default: 
                    System.out.print("Selection not found. Please try again."
                            + "\n\n");
            } 
        }     
    }
    
    public static String chooseRandomWord(String[] gameArray, int level)
    {  
        Random rand = new Random(); 
        String randWord;
        
        do
        {
            int value = rand.nextInt(gameArray.length);
            randWord = gameArray[value];
            randWord = randWord.toLowerCase();
               
        } while (randWord.length() != level);
        
        return randWord; 
    }
    
    // displays game prompt
    public static void displayPrompt(int level)
    {
       String prompt;
       
        prompt = "\nComputer: I'm thinking of a " + level + " letter word in "
                + "this category. Can you guess it?\n\t   (You may type 'Q' at "
                + "any point to exit the game).";
        System.out.print(prompt);
    }
    
    // request word from user
    public static String getPlayerGuess(Game secretGameWord)
    {
        String prompt, strUserResponse;
        
        do
        {
            prompt = "\n\nPlayer: ";
            System.out.print(prompt);
            strUserResponse = input.nextLine(); 
            strUserResponse = strUserResponse.toLowerCase();
            if(strUserResponse.equals("q"))
                break;
            if (strUserResponse.length() != secretGameWord.getGameLevel())
                System.out.print("Please enter a " 
                        + secretGameWord.getGameLevel() + " letter word. "
                        + "Try again.");
            
        } while (strUserResponse.length() != secretGameWord.getGameLevel());
        
        return strUserResponse;  
    }
    
    
    // thanks user and reports number of tries
    public static void sayThanks(String playerEntry, Game secretGameWord)
    {
        String tryTense = " try";
        
        System.out.print("\nComputer: The word was " 
                + secretGameWord.getGameWord().toUpperCase() + ".");
        
        if(!playerEntry.equals("q"))
        {
            if(Game.attemptCounter() > 1)
                tryTense = " tries";
        
            System.out.print("\nComputer: You got it in " + Game.attemptCounter 
                    + tryTense + "!");
        }
            
        System.out.print("\n\n\t\t\tThank you for playing Mastermind!");
    }
}

class Game
{
    public static final char REPLACEMENT_CHAR_1 = '*';
    public static final char REPLACEMENT_CHAR_2 = '-';
    public static final char REPLACEMENT_CHAR_3 = ' ';
    public static final char MIN_LETTER = 'a';
    public static final char MAX_LETTER = 'z';
    public static final int MIN_WORD_LENGTH = 3;
    public static final int MAX_WORD_LENGTH = 5;
    public static final int MIN_LEVEL = 3;
    public static final int MAX_LEVEL = 5;
    public static final int DEFAULT_LEVEL = 3;
    public static final String DEFAULT_WORD = "zzz";
    
    private String gameWord;
    private int gameLevel;
    static int attemptCounter = 0;
    
    // constructor 
    public Game()
    {
        setGameWord(DEFAULT_WORD);
        setGameLevel(DEFAULT_LEVEL);
    }
      
    // constructor with two parameter
    public Game(String word, int level)
    {
        if(!setGameWord(word))
            setGameWord(DEFAULT_WORD);
        if(!setGameLevel(level))
        setGameLevel(DEFAULT_LEVEL);
    }
           
    // mutators 
    public boolean setGameWord(String word)
    {
        char[] testValidityArray = word.toCharArray();
        int letterNum;
        
        if(word.length() < MIN_WORD_LENGTH || 
                word.length() > MAX_WORD_LENGTH)
            return false;
        
        for (letterNum = 0; letterNum < word.length(); letterNum++)
        {
            if(testValidityArray[letterNum] < 'a' || 
                    testValidityArray[letterNum] > 'z')
                return false;
        }
        
        gameWord = word;
        return true;
    }
    
    public boolean setGameLevel(int level)
    {
        if(level < MIN_LEVEL || level > MAX_LEVEL)
            return false;
        gameLevel = level;
        return true;        
    }
    
    // assessors
    String getGameWord() { return gameWord; }
    int getGameLevel() {return gameLevel; }
     
    // uses multiple helper methods to determine computers response to the
    // players response
    public String getComputerResponse(Game playerGuess, Game secretGameWord, 
            boolean validGuess)
    {
        String errorMessage;
        
        errorMessage = "\n\t\t\t***Invalid entry. Please only use "
                + "letters a - z.***";
        attemptCounter++;
        
        if(!validGuess)
            return errorMessage;
        
        String newAsteriskString = replaceWithAsterisk(playerGuess, 
                secretGameWord);
        String modifiedGameWord = temporaryGameWord(newAsteriskString,
                secretGameWord);
        String newDashString = replaceWithDash(newAsteriskString, 
                modifiedGameWord);
        String maskedString = replaceWithSpace(newDashString, secretGameWord);
        String computerResponse = sortString(maskedString);
        
        return computerResponse;
    } 
    
    // replace correct letters in correct place with *
    private String replaceWithAsterisk (Game playerGuess, Game secretGameWord)
    {
        String newAsteriskString = "";
        int characterNum;
        
        for (characterNum = 0; characterNum < secretGameWord.getLength(); 
                characterNum++)
        {
            
            if(playerGuess.charAt(characterNum) == 
                    secretGameWord.charAt(characterNum))
                newAsteriskString += REPLACEMENT_CHAR_1;
            else           
                newAsteriskString += playerGuess.charAt(characterNum);       
        }

        return newAsteriskString;
    }
    
    // creates a temporary GameWord as a string for comparison purposes
    private String temporaryGameWord(String newAsteriskString, 
            Game secretGameWord)
    {
        int letterNum;
        String tempGameWord = "";
        final char INELIGIBLE_TO_MATCH = '#';
        
        for (letterNum = 0; letterNum < secretGameWord.getLength();
                letterNum++)
        {
            if(newAsteriskString.charAt(letterNum) == 
                    REPLACEMENT_CHAR_1)
                tempGameWord += INELIGIBLE_TO_MATCH;
            else
                tempGameWord += secretGameWord.charAt(letterNum);
        }
        
        return tempGameWord;  
    }
    
    // replace correct letters in incorrect place with -
    private String replaceWithDash (String newAsteriskString, 
            String modifiedGameWord)
    {
        String newDashString = ""; 

        int gameWordCharNum, playerGuessCharNum;
        boolean matchesALetter;      
         
        for (playerGuessCharNum = 0; playerGuessCharNum < 
                modifiedGameWord.length(); playerGuessCharNum++)
        {
            matchesALetter = false;
            
            for (gameWordCharNum = 0; gameWordCharNum < 
                    modifiedGameWord.length(); gameWordCharNum++)
            {
                if(newAsteriskString.charAt(playerGuessCharNum) == 
                        modifiedGameWord.charAt(gameWordCharNum))
                    matchesALetter = true;
            }
            
            if(matchesALetter == true)
               newDashString += REPLACEMENT_CHAR_2;
            else
                newDashString += newAsteriskString.charAt(playerGuessCharNum);  
        }
        
        return newDashString;         
    }  
    
    // replace remaining letters with (space)
    private String replaceWithSpace (String newDashString, 
            Game secretGameWord)
    {
        String maskedString = "";
        int characterNum;
        
        for (characterNum = 0; characterNum < secretGameWord.getLength(); 
                characterNum++)
        {
            
            if(newDashString.charAt(characterNum) >= 'a' && 
                    newDashString.charAt(characterNum) <= 'z')
                maskedString += REPLACEMENT_CHAR_3;
            else
                maskedString += newDashString.charAt(characterNum);
        }

        return maskedString;
    }
    
    // sorts characters to * then - then (space)
    private String sortString (String maskedString)
    {
        String computerOutputString = "\nComputer: ";
        int characterNum;
        
        
        for (characterNum = 0; characterNum < maskedString.length(); 
                characterNum++)
        {     
            if(maskedString.charAt(characterNum) == REPLACEMENT_CHAR_1)
                computerOutputString += REPLACEMENT_CHAR_1;
        }
        for (characterNum = 0; characterNum < maskedString.length();
                characterNum++)
        {   
            if(maskedString.charAt(characterNum) == REPLACEMENT_CHAR_2)
                computerOutputString += REPLACEMENT_CHAR_2;
        }
        for (characterNum = 0; characterNum < maskedString.length(); 
                characterNum++)
        {       
            if(maskedString.charAt(characterNum) == REPLACEMENT_CHAR_3)
                computerOutputString += REPLACEMENT_CHAR_3;
        }

        return computerOutputString;
    }  
    
    // displays the computer's response
    public void displayComputerResponse(Game playerGuess, 
            Game secretGameWord, boolean validGuess)
    {
        String legend;
        
        legend = "\nComputer: * means one of the letters is "
                + "correct and in the correct position \nComputer: - "
                + "means one of the letters is correct, but in an "
                + "incorrect position\nComputer: * and - do NOT "
                + "neccessarily appear in the correct position.\n";
        
        if(Game.attemptCounter() == 0)
            System.out.print(legend);
        
        String computerOutputString = secretGameWord.getComputerResponse(
                playerGuess, secretGameWord, validGuess); 
        System.out.print(computerOutputString);
    }
    
    // keeps count of user's number of attempts
    static int attemptCounter() { return attemptCounter; }
    
    // returns Game object as a String
    public String toString()
    {
       String gameWordString = gameWord;
       return gameWordString;
    }
    
    // gets a character from a Game object
    public char charAt(int charNumber)
    {
        char charAtInt;
          
        charAtInt = gameWord.charAt(charNumber);  
        return charAtInt;
    }
    
    // gets the length of a Game object
    public int getLength()
    {
        int length;
        
        length = gameWord.length();
        return length;
    }
}