import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderClass {
    private String text;
    private String path;



    public String getText(Path path){
        this.path  = path.getPath();
        readFile();

        return text;
    }

    public void readFile()
    {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = new String(encoded, Charset.defaultCharset());
    }
}