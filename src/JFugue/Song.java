package JFugue;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

import java.util.ArrayList;

/**
 * Created by Thomas on 16.03.2016.
 */
public class Song {
    Mood mood;
    String key;
    String keyRaw;

    ChordProgression songProgression;
    Melody melodyBasis;
    Melody melodyRefrain;
    Rhythm rhythm;
    String progressionRoman;
    Pattern BassLine;


    public Song (Mood mood, int tempo){
        this.mood=mood;

        //Ny sang
        //***********************
        //******** KEY **********
        //***********************

        //Må velge key, foreløpig random.
        //todo skriv noe her som velger random key
        key = "G";
        keyRaw =key;

        String majmin = "";
        //Velg Maj/Min osv
        //basically hvis trist, minor, hvis glad major.

        if (mood == Mood.HAPPY){
            majmin = "maj";
        }else if (mood == Mood.SAD){
            majmin = "min";
        }else {
            majmin = "I DONT HAVE A MOOD";
        }

        //setter key med minor eller major.
        //todo fix this
        //key = key+majmin;
        key = key+majmin;
        System.out.println("HVA SKRIVER DEN?: " + majmin + " MOODEN ER: " +  mood);

        //************************************
        //******** CHORDPROGRESSION **********
        //************************************

        //lager en chordProgression
        //todo gjør ikke noe med mood nå, gå inn i klassen og fix it yo.

        progressionRoman = PossibleChordProgressions.getProgression(mood);
        songProgression = new ChordProgression(progressionRoman).setKey(key);
        //ha som default at alle chords spilles to ganger, foreløpig.
        //songProgression.allChordsAs("$0 $0 $1 $1 $2 $2 $3 $3 ");
        songProgression.allChordsAs("$0 $1 $2 $3 ");


        //todo set voices.


        //**************************
        //******** MELODY **********
        //**************************

        //lager nye melodier
        System.out.println("lager ny melodi med key: "+keyRaw);
        melodyBasis = new Melody(mood,keyRaw,songProgression,progressionRoman,"basis");
        System.out.println("melodddey " + melodyBasis.getMelodyString());
        melodyRefrain = new Melody(mood,keyRaw,songProgression,progressionRoman,"refrain");


        //**************************
        //******** BASS **********
        //**************************

        // return (new Pattern(melodyRefrain.getMelodyString()).setVoice(2).setInstrument("PIANO"));
        //lager nye melodier

        //songprogression - songprogressino

        BassLine = new ChordProgression(progressionRoman).setKey(keyRaw)

                .allChordsAs("$0 $1 $2 $3")
                //.eachChordAs("$0ia100 $0ia100")
                .getPattern()
                .setInstrument("ACOUSTIC_BASS")
                .setVoice(7);


        //**************************
        //******** RHYTHM **********
        //**************************

        //TODO lag rytmeklasse med predefinerte rytmer og hent herfra.
        //hardkodet rytme for now.
        rhythm = new Rhythm()
                .addLayer("O..oO...O..oOO..")
                .addLayer("..S...S...S...S.")
                .addLayer("````````````````")
                .addLayer("...............+");


        //***********************************
        //******** BOLK-GENERERING **********
        //***********************************


        Bolk introBolk = new Bolk(songProgression,melodyBasis.getMelodyString(),rhythm,"intro");
        

    }

    //**************************
    //******** GETTERS *********
    //**************************

    public Rhythm getRhythm() {
        return rhythm.setLength(3);
    }

    public Pattern getMelodyBasis() {
       // System.out.println("yolo" + melodyBasis.getMelodyString());
        return (new Pattern(melodyBasis.getMelodyString()).setVoice(2).setInstrument("PIANO"));

    }

    public Pattern getMelodyRefrain() {
        return (new Pattern(melodyRefrain.getMelodyString()).setVoice(2).setInstrument("PIANO"));
    }

    public Pattern getBassLine() {
        System.out.println(BassLine);
        return BassLine;
    }

    public Pattern getSongProgression() {
        Pattern pattern = songProgression
                //.distribute("7%6")
                //.allChordsAs("$0 $1 $2 $0 $1 $1 $0 $0 $2 $1 $0 $0")
                //.eachChordAs("$0ia100 $0ia100")
                .getPattern()
                .setInstrument("SYNTH_BASS_2")
                .setVoice(1);

        return pattern.setTempo(80);
    }
}
