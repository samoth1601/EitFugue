package JFugue;

        import com.sun.media.sound.MidiUtils;
        import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
        import org.jfugue.parser.ParserListener;
        import org.jfugue.player.Player;
        import javax.sound.midi.MidiUnavailableException;
        import java.io.IOException;
        import java.util.Random;


        import java.util.*;
        import java.io.*;

        import org.jfugue.midi.*;
        import org.staccato.*;

        // DISSE HAR TORD LAGT TIL 13.04.2016
        import java.io.File;

        import javax.sound.midi.Soundbank;
        import javax.sound.midi.Synthesizer;
        import javax.sound.midi.*;

        import org.jfugue.player.SequencerManager;
        import org.jfugue.player.SynthesizerManager;


public class Main {
    public static void main(String[] args) throws IOException, MidiUnavailableException, InvalidMidiDataException {





        //SYNTH & SAMPLE CHANGE FROM DEFAULT

        Soundbank soundbank;        //Declares new Soundbank instance
        SynthesizerManager newSynth;//Declares new SynthesizerManager (jFugue class);

        Synthesizer synth;          //Declares new Synthesizer instance

        synth = MidiSystem.getSynthesizer();
        synth.open();

        try {
            // Make sure gervill.jar is in your classpath


            soundbank = MidiSystem.getSoundbank(new File("/Users/tordolsen/IdeaProjects/GervillTest/src/Compifont_01032016.sf2"));

            //Print compatibility and if synth is open
            //System.out.print(soundbank.getVersion() + "\n");
            //System.out.print(soundbank.getVendor() + "\n");
            //System.out.println(synth.isSoundbankSupported(soundbank));

            synth.loadAllInstruments(soundbank);


            newSynth = SynthesizerManager.getInstance();

            newSynth.setSynthesizer(synth);

        }
        catch (MidiUnavailableException e) {
            System.out.println("Catched: MidiUnavailableExeption " + e);
        }
        catch (InvalidMidiDataException e) {
            System.out.println("Catched: InvalidMidiDataExeption " + e);
        }
        catch (IOException e) {
            System.out.println("Catched: IOExeption " + e);
        }






        //String theMood = "excited";
        String theMood = "happy";
        System.out.println(theMood);

        /******CREATE SONG******/
        Random rnd = new Random();
        Mood songMood = Mood.HAPPY;

        int tempoSet = 120;
        String instrumentSet = "PIANO";

        if (theMood.equals("sad")){
            tempoSet = 40;
            instrumentSet = "VIOLIN";
            songMood = Mood.SAD;

        }else if (theMood.equals("calm")){
            tempoSet = 40;
            instrumentSet = "STRING_ENSEMBLE_1";
            songMood = Mood.HAPPY;

        }else if (theMood.equals("happy")){
            tempoSet = 80;
            instrumentSet = "MUSIC_BOX";
            songMood = Mood.HAPPY;

        }else if (theMood.equals("sick")){
            tempoSet = 74;
            instrumentSet = "VIOLIN";
            songMood = Mood.SAD;

        }else if (theMood.equals("excited")){
            tempoSet = 90;
            songMood = Mood.HAPPY;
            instrumentSet = "CALLIOPE";

        }else if (theMood.equals("scared")){
            tempoSet = 90;
            instrumentSet = "VIOLIN";
            songMood = Mood.SAD;

        }else if (theMood.equals("angry")){
            tempoSet = 80;
            instrumentSet = "CALLIOPE";
            songMood = Mood.SAD;
        }


        Player player = new Player();
        Song yolo = new Song(songMood,tempoSet);

        //how long intro
        int repeatIntro = rnd.nextInt(2);
        System.out.println("introtimes:" + repeatIntro);

        //how many times repeat verse
        int repeatVerse = rnd.nextInt(2)+2;
        System.out.println("versetimes:" + repeatVerse);

        //how many times repeat verse
        int repeatRefrain = rnd.nextInt(2)+2
                ;
        System.out.println("refrainTimes:" + repeatRefrain);

        player.play(
        //Sequence Test = player.getSequence(



                /***** SONGPROGRESSION *****/

                //yolo.getSongProgression().repeat(repeatIntro+repeatVerse*1+repeatRefrain*2 + 1).setTempo(tempoSet),        //endre repeat til faktiske runder
                yolo.getSongProgression().repeat(repeatIntro + repeatVerse).setTempo(tempoSet),
                yolo.getSongProgressionRefrain().repeat(repeatRefrain).setTempo(tempoSet),
                yolo.getSongProgression().repeat(1).setTempo(tempoSet),
                yolo.getSongProgressionRefrain().repeat(repeatRefrain).setTempo(tempoSet),



                /***** DRUMS *****/
                yolo.getRhythmEmpty().getPattern().repeat(repeatIntro),
                yolo.getRhythm().getPattern().repeat(repeatVerse),
                yolo.getRhythm().getPattern().repeat(repeatRefrain),
                yolo.getRhythmEmpty().getPattern(),
                yolo.getRhythm().getPattern().repeat(repeatRefrain),


                /***** MELODY *****/
                yolo.getMelodyEmpty().repeat(repeatIntro),
                yolo.getMelodyBasis().repeat(repeatVerse).setInstrument(instrumentSet),
                yolo.getMelodyRefrain().repeat(repeatRefrain).setInstrument(instrumentSet),
                yolo.getMelodyBasis().repeat(1).setInstrument(instrumentSet),
                //yolo.getRhythm().getPattern().repeat(repeatRefrain).setInstrument(instrumentSet),
                yolo.getMelodyRefrain().repeat(repeatRefrain).setInstrument(instrumentSet),


                /***** BASS *****/
                yolo.getBassLine().repeat(repeatIntro+repeatVerse*1+repeatRefrain*2 + 1)
                //yolo.getBassLine().repeat(repeatIntro + repeatVerse),
                //yolo.getBassLine().repeat(repeatRefrain),
                //yolo.getBassLine().repeat(1),
                //yolo.getBassLine().repeat(repeatRefrain)



        );



        //TRIED TO IMPLEMENT RENDERING FROM JFUGUE PLAYER TO .WAV
        // - Unsuccesful, still uses default soundbank for writing to wavefile
/*
        Midi2WavRenderer TestRenderer = new Midi2WavRenderer();
        TestRenderer.createWavFile(new File("/Users/tordolsen/IdeaProjects/GervillTest/src/Compifont_01032016.sf2"), patches ,Test, new File("WAVTEST2.wav"));

*/
        synth.close();  // CLEAN UP ON ISLE 4
    }
}