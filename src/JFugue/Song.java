package JFugue;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

/**
 * Created by Thomas on 16.03.2016.
 */
public class Song {
    Mood mood;
    String key;

    ChordProgression songProgression;
    Melody melodyBasis;
    Melody melodyRefrain;
    Rhythm rhythm;
    String progressionRoman;


    public Song (Mood mood, int tempo){
        this.mood=mood;

        //Ny sang
        //***********************
        //******** KEY **********
        //***********************

        //Må velge key, foreløpig random.
        //todo skriv noe her som velger random key
        key = "C";
        String majmin = "";
        //Velg Maj/Min osv
        //basically hvis trist, minor, hvis glad major.
        switch(mood){
            case HAPPY:
                majmin = "maj";
            case SAD:
                majmin = "min";
        }

        //setter key med minor eller major.
        //todo fix this
        //key = key+majmin;
        key = key+majmin;

        //************************************
        //******** CHORDPROGRESSION **********
        //************************************

        //lager en chordProgression
        //todo gjør ikke noe med mood nå, gå inn i klassen og fix it yo.
        System.out.println(key);
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
        melodyBasis = new Melody(mood,key,songProgression,progressionRoman);
        System.out.println("melodddey " + melodyBasis.getMelodyString());
        melodyRefrain = new Melody(mood,key,songProgression,progressionRoman);


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
        System.out.println("yolo" + melodyBasis.getMelodyString());
        return (new Pattern(melodyBasis.getMelodyString()).setVoice(2).setInstrument("PIANO"));

    }

    public Pattern getMelodyRefrain() {
        return (new Pattern(melodyBasis.getMelodyString()).setVoice(2).setInstrument("PIANO"));

    }

    public Pattern getSongProgression() {
        Pattern pattern = songProgression
                //.distribute("7%6")
                //.allChordsAs("$0 $1 $2 $0 $1 $1 $0 $0 $2 $1 $0 $0")
                //.eachChordAs("$0ia100 $0ia100")
                .getPattern()
                .setInstrument("Piano")
                .setVoice(1);

        return pattern.setTempo(80);
    }
}
