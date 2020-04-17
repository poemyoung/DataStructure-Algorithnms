package TreeSet;
import java.lang.Comparable;

/**
 * 测试javaBean
 */
public class JayChouSongs implements Comparable<JayChouSongs>{
    int NO;
    String songName;

    public JayChouSongs(int NO, String songName) {
        this.NO = NO;
        this.songName = songName;
    }

    public JayChouSongs() {
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }


    @Override
    public String toString() {
        return NO+"";
    }

    @Override
    public int compareTo(JayChouSongs o) {
        return this.NO - o.NO;
    }
}
