import java.io.FileNotFoundException;
import java.io.IOException;

public class RichFile {

    String filepath;
    boolean readable;
    private static String prefix = "contents-";

    public RichFile(String filepath) {
        this.filepath = filepath;
        this.readable = true;
    }

    public RichFile(String filepath, boolean readable) {
        this.filepath = filepath;
        this.readable = readable;
    }

    public String read() throws IOException {
        if(readable) {
            return  prefix + this.filepath;
        }
        throw new FileNotFoundException();
    }

}
