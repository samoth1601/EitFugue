package JFugue;

import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Thomas on 16.03.2016.
 */
public class Melody {
    ScaleType scaleType;
    PossibleIntervalScales possibleIntervalScales = new PossibleIntervalScales();
    ArrayList<String> scale;
    String patternString;

    public Melody (Mood mood, String key, ChordProgression blockProgression,String progressionRoman){
        patternString = "";
        //velger skala ut fra mood
        if (mood == Mood.HAPPY){
            scaleType = ScaleType.MajorPentatonic;
        }else if (mood == Mood.SAD){
            scaleType = ScaleType.MinorPentatonic;
        }
        scale = possibleIntervalScales.getScaleArray(key,scaleType);



        Random rnd = new Random();

        //henter chordprogression for blokken og konverterer denne fra romerske tall til integers.
        int[] progressionInts = getNumbersFromRoman(progressionRoman);
        ArrayList<String> majorScaleForKey = possibleIntervalScales.getScaleArray(key,ScaleType.Major);


        //lager en sekvens av 16 noter, fordelt på 4 bolker.
        for (int i = 0; i < 4; i++) {

            //første note av 16 skal være lik grunnote i første chord.
            //******* BOLK1 *******
            if (i==0){
                patternString+= majorScaleForKey.get(0);

                for (int j = 0; j < 3; j++) {
                    patternString += scale.get(rnd.nextInt(scale.size()));
                }
                System.out.println(i +": "+ patternString);

                //******* BOLK 2, 3, 4 *******
            }else {


                //etter første 4-er blokk
                for (int j = 0; j < 4; j++) {
                    //første note av 4er-blokken skal være lik en av tonene som er i akkorden som spilles.
                    if (j == 0) {

                        //finne hvilken akkord som spilles.
                        int activeChordInt = progressionInts[i];

                        //finne aktiv skala for key
                        //var scale

                        //henter ut mulige noter som skal spilles basert på hvilken chord som spilles og i keyen.
                        ArrayList<String> possibleFirstNotesFromChord = new ArrayList<String>();
                        int progressionIntInt = progressionInts[i];

                        int k = 0;

                        if (progressionIntInt != 3 || progressionIntInt != 6){
                        possibleFirstNotesFromChord.add(majorScaleForKey.get(progressionIntInt));
                            k++;
                        }

                        if (progressionIntInt+2 != 3 || progressionIntInt+2 != 6){
                            possibleFirstNotesFromChord.add(majorScaleForKey.get((progressionIntInt+2)%7));
                            k++;
                        }

                        if (progressionIntInt+4 != 3 || progressionIntInt+4 != 6){
                            possibleFirstNotesFromChord.add(majorScaleForKey.get((progressionIntInt+4)%7));

                        }



                        /*possibleFirstNotesFromChord[0] = majorScaleForKey.get(progressionIntInt);
                        possibleFirstNotesFromChord[1] = majorScaleForKey.get((progressionIntInt+(2))%7);
                        possibleFirstNotesFromChord[2] = majorScaleForKey.get((progressionIntInt+(4))%7);
*/
                        patternString+=possibleFirstNotesFromChord.get(rnd.nextInt(possibleFirstNotesFromChord.size()));

                    }else{
                        patternString+=scale.get(rnd.nextInt(scale.size()));
                    }

                }
                System.out.println(i +": "+ patternString);
            }

        }


        System.out.println("horebitch" + patternString);
    }



    public String getMelodyString(){
        return patternString;
    }

    public int[] getNumbersFromRoman(String romanString){
        String[] romanArray = romanString.split(" ");
        int[] romanInts = new int[romanArray.length];
        for (int i = 0; i < romanArray.length; i++) {
            String R = romanArray[i];
            System.out.println("R: " + R);
            int factor = 0;
            if (R.equals("I")) {
                romanInts[i] = 1-factor;
            } else if (R.equals("ii")) {
                romanInts[i] = 2-factor;
            } else if (R.equals("iii")) {
                romanInts[i] = 3-factor;
            } else if (R.equals("IV")) {
                romanInts[i] = 4-factor;
            } else if (R.equals("V")) {
                romanInts[i] = 5-factor;
            } else if (R.equals("vi")) {
                romanInts[i] = 6-factor;
            } else if (R.equals("VII")) {
                romanInts[i] = 7-factor;
            }else{
                romanInts[i] = 1999;
            }
        }
        for (int testyolo: romanInts
             ) {
            System.out.println("$: " + testyolo);
        }
        return romanInts;
    }



}
