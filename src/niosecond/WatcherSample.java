package niosecond;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherSample extends Thread{
    private String dirName;

    public WatcherSample(String dirName) {
        this.dirName=dirName;
    }

    public static void main(String[] args) throws InterruptedException {
        String dirName ="C:\\study\\thread-io-with-java7\\copied";
        String fileName = "watchService.txt";
        WatcherSample sample = new WatcherSample(dirName);
        sample.setDaemon(true);
        sample.start();
        Thread.sleep(1000);
        for(int i =0; i<10 ; i++){
            sample.fileWriteAndDelete(dirName, fileName+i );
        }
    }

    @Override
    public void run() {
        System.out.println("===============watcher thread start============");
        System.out.println("dir name : " + dirName);
        try {
            addWatcher();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addWatcher() throws IOException, InterruptedException {
        Path dir = Paths.get(dirName);
        WatchService watcher = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY,ENTRY_DELETE);
        while(true){
            key = watcher.take();
            List<WatchEvent<?>> events = key.pollEvents();
            for (WatchEvent<?> event : events) {
                Path name = (Path) event.context();
                System.out.println(event.kind() +" event : "+name);
            }
            key.reset();
        }

    }

    private void fileWriteAndDelete(String dirName, String s) {
        Path path = Paths.get(dirName);
        String content = "Watcherrrrrrrrrrrrr.......";
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        System.out.println("Write .."+s);
        try {
            Files.write(path , content.getBytes(StandardCharsets.UTF_8), openOption);
            Files.delete(path);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
