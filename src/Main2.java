import Model.FileSignatureReader;

import java.io.File;
import java.io.IOException;


public class Main2 {
    public static void main(String[] args) {
        File file = new File("/Users/lamar/Movies/TV/Media.localized/Movies/Naruto Shippuden Episode 035 An Unnecessary Addition.mkv");


        FileSignatureReader fsr = new FileSignatureReader(file);
        try {
            System.out.println(fsr.readFileSignature());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}