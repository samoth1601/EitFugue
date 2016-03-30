package JFugue;

import java.util.Random;

/**
 * Created by Thomas on 16.03.2016.
 */
public class PossibleChordProgressions {
    static String[] possibleProgressions = {
            "I ii IV V", "I vi IV V"
    };

    public static String getProgression(Mood mood){
        //velger en progression basert på mood

        Random rnd = new Random();

        //todo check this out
        return possibleProgressions[rnd.nextInt(possibleProgressions.length-1)];
    }

}
