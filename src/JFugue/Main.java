package JFugue;

        import org.jfugue.player.Player;
        import javax.sound.midi.MidiUnavailableException;
        import java.io.IOException;
        import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, MidiUnavailableException {

        String theMood = "excited";
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
            instrumentSet = "OCARINA";
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



                /***** SONGPROGRESSION *****/
                yolo.getSongProgression().repeat(10).setTempo(tempoSet),

                /***** DRUMS *****/
                yolo.getRhythmEmpty().getPattern().repeat(repeatIntro),
                yolo.getRhythm().getPattern().repeat(repeatVerse),
                yolo.getRhythm().getPattern().repeat(repeatRefrain),
                yolo.getRhythmEmpty().getPattern(),

                /***** MELODY *****/
                yolo.getMelodyEmpty().repeat(repeatIntro),
                yolo.getMelodyBasis().repeat(repeatVerse).setInstrument(instrumentSet),
                yolo.getMelodyRefrain().repeat(repeatRefrain).setInstrument(instrumentSet),
                yolo.getMelodyBasis().repeat(1).setInstrument(instrumentSet),
                yolo.getRhythm().getPattern().repeat(repeatRefrain).setInstrument(instrumentSet),
                yolo.getMelodyRefrain().repeat(repeatRefrain).setInstrument(instrumentSet),


                /***** BASS *****/
                yolo.getBassLine().repeat(repeatIntro+repeatVerse*2+repeatRefrain*1)
        );

    }
}