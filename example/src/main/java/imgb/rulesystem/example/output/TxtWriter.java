package imgb.rulesystem.example.output;

import imgb.rulesystem.context.entity.result.Result;
import imgb.rulesystem.output.ResultWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by is on 1/14/15.
 */
public class TxtWriter extends ResultWriter{

    @Override
    public void write(Result result) {

        try {
            File file = new File("./test.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
