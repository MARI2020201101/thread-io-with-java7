package niosecond;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FilesManager {

    public static void main(String[] args) {
        FilesManager manager = new FilesManager();
        manager.readAndWrite("C:\\study\\thread-io-with-java7\\sample.txt");
    }

    public List<String> getContents(){
        List<String> content = new ArrayList<>();
        content.add("Hi~~~~");
        content.add("Let's learn nio2~~~");
        content.add("bye~~~~~");
        return content;
    }
    public Path writeFile(Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        List<String> content = getContents();
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        return Files.write(path,content,charset,openOption);
    }
    public void readFile(Path path)throws Exception{
        Charset charset = Charset.forName("UTF-8");
        System.out.println("Path : "+path);
        List<String> content = Files.readAllLines(path,charset);
        for (String s : content) {
            System.out.println("content : " + s);
        }
    }

    public Path readAndWrite(String fileName){
        Path returnPath = null;
        try{
            Path path = Paths.get(fileName);
            returnPath=writeFile(path);
            System.out.println("-----------file created-------------");
            readFile(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPath;
    }

}
