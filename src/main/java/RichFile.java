import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.acl.NotOwnerException;

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

    public String read() throws IOException, NotOwnerException {
        if(readable) {
            return  prefix + this.filepath;
        } if(filepath.equals("NoPermisssionFile")) {
            throw new NotOwnerException();
        }
        throw new FileNotFoundException();
    }

}
