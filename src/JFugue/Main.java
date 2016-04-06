package JFugue;

        import org.jfugue.player.Player;
        import javax.sound.midi.MidiUnavailableException;
        import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, MidiUnavailableException {

        Player player = new Player();

        Song yolo = new Song(Mood.SAD,80);
        player.play(yolo.getMelodyBasis().setTempo(55).repeat(2),
                yolo.getMelodyRefrain().repeat(2),
                yolo.getSongProgression().repeat(4),
                yolo.getRhythm()
                //yolo.getBassLine().repeat(4)
        );
    }
}