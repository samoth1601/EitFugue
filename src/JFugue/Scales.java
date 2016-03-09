package JFugue;

/**
 * Created by Thomas on 02.03.2016.
 */
public class Scales{
    static int[] Major = {2,2,1,2,2,2,1};
    static int[] Minor = {2,1,2,2,1,2,2};

    private Scales() {
    }

    public static int[] getIntervals(ScaleType scaleType){
        switch(scaleType){
            case Major:
                return Major;
            case Minor:
               return Minor;
            default : return null;//optional
        }
    }

}
