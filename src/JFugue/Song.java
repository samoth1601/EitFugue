package JFugue;

import org.jfugue.pattern.Pattern;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

import java.util.Random;

/**
 * Created by Thomas on 16.03.2016.
 */
public class Song {
    Mood mood;
    String key;
    String keyRaw;

    ChordProgression songProgression;
    Melody melodyBasis;
    Pattern melodyEmpty;
    Melody melodyRefrain;
    Melody melodyRefrain2;
    Rhythm rhythm;
    Rhythm rhythmEmpty;
    String progressionRoman;
    Pattern BassLine;


    public Song (Mood mood, int tempo){
        this.mood=mood;
        Random rnd = new Random();

        //Ny sang
        //***********************
        //******* NY SANG *******
        //***********************
        //********* KEY *********
        //***********************

        //Må velge key, foreløpig random.
        String[] possibleKeys={"C","D","E","F","G","A","B"};

        key = possibleKeys[rnd.nextInt(possibleKeys.length)];
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
        key = key+majmin;
        System.out.println("Making new song in key: " + key + ". The mood of the song is: " +  mood);

        //************************************
        //******** CHORDPROGRESSION **********
        //************************************

        //lager en chordProgression
        progressionRoman = PossibleChordProgressions.getChordProgression(mood);
        songProgression = new ChordProgression(progressionRoman).setKey(key);
        //ha som default at alle chords spilles to ganger, foreløpig.
        //songProgression.allChordsAs("$0 $0 $1 $1 $2 $2 $3 $3 ");
        songProgression.allChordsAs("$0 $1 $2 $3 ");



        //**************************
        //******** MELODY **********
        //**************************

        //lager nye melodier for vers og refreng
        System.out.println("lager ny melodi med key: "+keyRaw);
        melodyBasis = new Melody(mood,keyRaw,songProgression,progressionRoman,"basis");
        System.out.println("melodddey " + melodyBasis.getMelodyString());
        melodyRefrain = new Melody(mood,keyRaw,songProgression,progressionRoman,"refrain");
        melodyRefrain2 = new Melody(mood,keyRaw,songProgression,progressionRoman,"refrain");

        //lager tom melody for bruk.
        String emptyPattern ="";
        for (int i = 0; i < 15; i++) {
            emptyPattern += "rs ";
        }
        melodyEmpty = new Pattern(emptyPattern);


        //**************************
        //******** BASS **********
        //**************************

        // return (new Pattern(melodyRefrain.getMelodyString()).setVoice(2).setInstrument("PIANO"));
        //lager nye melodier

        //songprogression - songprogressino

        BassLine = new ChordProgression(progressionRoman).setKey(keyRaw+="2")
                .allChordsAs("$0 $1 $2 $3")
                .eachChordAs("$0")
                .getPattern()
                .setInstrument("SAWTOOTH")
                .setVoice(7);


        //**************************
        //******** RHYTHM **********
        //**************************

        //TODO lag rytmeklasse med predefinerte rytmer og hent herfra.
        //hardkodet rytme for now.
        rhythm = new Rhythm()
                .addLayer("O..oO...")
                .addLayer("..S...S.")
                .addLayer("````````")
                .addLayer(".......+");


        rhythmEmpty = new Rhythm()
                .addLayer("........")
                .addLayer("........")
                .addLayer("........")
                .addLayer("........");

    }

    //**************************
    //******** GETTERS *********
    //**************************

    public Rhythm getRhythm() {
        return rhythm.setLength(1);
    }

    public Rhythm getRhythmEmpty() {
        return rhythmEmpty.setLength(1);
    }

    public Pattern getMelodyEmpty() {
        // System.out.println("yolo" + melodyBasis.getMelodyString());
        return ((melodyEmpty.setVoice(2).setInstrument("MUSIC_BOX")));
    }

    public Pattern getMelodyBasis() {
       // System.out.println("yolo" + melodyBasis.getMelodyString());
        return (new Pattern(melodyBasis.getMelodyString()).setVoice(2).setInstrument("MUSIC_BOX"));

    }
    public Pattern getMelodyRefrain() {
        return (new Pattern(melodyRefrain.getMelodyString()).setVoice(2).setInstrument("MUSIC_BOX"));
    }

    public Pattern getMelodyRefrain2() {
        return (new Pattern(melodyRefrain2.getMelodyString()).setVoice(2).setInstrument("MUSIC_BOX"));
    }

    public Pattern getBassLine() {
        System.out.println(BassLine);
        return BassLine;
    }

    public Pattern getSongProgression() {
        Pattern pattern = songProgression
                .getPattern()
                .setInstrument("SYNTH_BASS_2")
                .setVoice(1);

        return pattern.setTempo(80);
    }
}
