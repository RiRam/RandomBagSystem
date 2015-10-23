
/*
 * Ri Ram
 * reddit.com/r/dailyprogrammer Challenge #236 Random Bag System
 * https://redd.it/3ofsyb
 * 10-23-2015
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class RandomBag
{
    public static char[] pieces = {'O', 'I', 'S', 'Z', 'L', 'J', 'T'};
    
    //Main
    public static void main(String[] args)
    {
        //Generating lists of 50 tetromino pieces
        System.out.println("Picking 50 tetromino pieces:");
        System.out.println(pickTetromino());
        System.out.println(pickTetromino());
        System.out.println(pickTetromino());
        System.out.println(pickTetromino() + "\n");
        
        //Validating input of pieces
        System.out.println("Validating input strings of tetromino pieces:");
        //Checking for correct length
        System.out.println(isValidTetromino("AAAA") + "(Should be false)");
        
        //True condition
        System.out.println(isValidTetromino("LJOZISTTLOSZIJOSTJZILLTZISJOOJSIZLTZISOJTLIOJLTSZO") + "(Should be true)");
        
        //Checking for excess repeated piece
        System.out.println(isValidTetromino("LJOZISTTLOSZIJOSTJZILLLZISJOOJSIZLTZISOJTLIOJLTSZO") + "(Should be false)");
        
        //Checking for character other than valid piece
        System.out.println(isValidTetromino("LJOZISTTLMSZIJOSTJZILLTZISJOOJSIZLTZISOJTLIOJLTSZO") + "(Should be false)");
        
        //Additional true conditions
        System.out.println(isValidTetromino("OTJZSILILTZJOSOSIZTJLITZOJLSLZISTOJZTSIOJLZOSILJTS") + "(Should be true)");
    }
    
    //Picks 50 tetromino pieces to be given to the player using the random bag system.
    public static String pickTetromino()
    {
        String tetromino = "";
        List<Integer> newSeven = new ArrayList(7);
        while(tetromino.length() <= 50)
        {
            for(int i = 0; i < 7; i++)
                newSeven.add(i);
            Collections.shuffle(newSeven);
            for(int i = 0; i < 7; i++)
            {
                tetromino += pieces[newSeven.get(i)];
            }
            newSeven.clear();
        }
        return tetromino.substring(0, 51);
    }
    
    
    //Takes output as input and verifies that it is a valid sequence of pieces.
    public static boolean isValidTetromino(String input)
    {
        System.out.println("Input: " + input);
        
        //if the input is not 50 pieces, it is not a valid input
        if(input.length() != 50)
            return false;
            
        //truncate the last character to make the sets divide evenly by 7 
        //(last character is irrelevant to checking for validity)    
        input = input.substring(0, 49);
        
        //break into individual sets of 7
        ArrayList<String> sets = new ArrayList(7);
        for(int i = 0; i < 7; i++)
        {
            String temp = input;
            sets.add(temp.substring(0,7));
            input = input.substring(7);
            System.out.println("Set " + i + ": " + sets.get(i));
        }
        
        //flag for finding an invalid piece
        boolean isValidPiece = false;
        
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                char temp = sets.get(i).charAt(j);
                //if there is an invalid piece, the set is invalid
                if(!isValidPiece(temp))
                    return false;
            }
        }
        
        
        //sort each set, compare to pieces and see if the same characters are present in both
        char[] sortedPieces = pieces;
        
        Arrays.sort(sortedPieces);
        
        for(int i = 0; i < 7; i++)
        {
            char[] temp = (sets.get(i)).toCharArray();
            Arrays.sort(temp);
            if(!(Arrays.equals(sortedPieces, temp)))
                return false;
        }
        
        return true;
    }
    
    //Compares a char input parameter to see if it is a valid tetromino piece
    public static boolean isValidPiece(char piece)
    {
        for(int i = 0; i < 7; i++)
        {
            if(piece == pieces[i])
                return true;
        }
        return false;
    }
}
