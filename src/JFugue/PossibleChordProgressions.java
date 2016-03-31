package JFugue;

import java.util.Random;

/**
 * Created by Thomas on 16.03.2016.
 */
public class PossibleChordProgressions {
    static String[] possibleProgressionsMAJOR = {
            "I V vi IV", "I V vi iii", "vi V IV V", "I vi IV V", "I IV vi V", "I V IV V"
    };
    static String[] possibleProgressionsMINOR = {
            "i iv i i", "i v i v", "i iv v i ", "VI VII i i ", "I VII VI v", "i iv i iv", "v iv i i ", "ii v i i ", "i VII VI VII"
    };

    
    public static String getProgression(Mood mood){
        Random rnd = new Random();
        String toReturn= "";
        //velger en progression basert på mood
        if (mood == Mood.HAPPY){
            toReturn =  possibleProgressionsMAJOR[rnd.nextInt(possibleProgressionsMAJOR.length-1)];
            System.out.println("PossibleChordProgression returnerer major" + toReturn);
        }else if (mood == Mood.SAD){
            toReturn =  possibleProgressionsMINOR[rnd.nextInt(possibleProgressionsMINOR.length-1)];
            System.out.println("PossibleChordProgression returnerer minor: " + toReturn);
        }else {
            return ("I DONT HAVE A MOOD");
        }
        return toReturn;
    }

}
