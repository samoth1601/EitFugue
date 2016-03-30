package JFugue;

import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.ChordProgression;

/**
 * Created by Thomas on 16.03.2016.
 */
public class Bolk {
    ChordProgression chordProgression;
    String part;
    String melody;
    Rhythm rhythm;

    public Bolk (ChordProgression chordProgression, String melody, Rhythm rhythm, String part){
        this.chordProgression = chordProgression;
        this.rhythm = rhythm;
        this.melody = melody;
        this.part = part;
    }

}
