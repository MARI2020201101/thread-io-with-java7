package niosecond;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFiles {
    public static void main(String[] args) {
        PathsAndFiles sample = new PathsAndFiles();
        String dir = "C:\\study\\thread-io-with-java7";
        String dir2 = "C:\\Users\\dbrtm\\OneDrive\\사진\\Captures";
        sample.checkPath(dir);
        sample.checkPath2(dir,dir2);
    }

    private void checkPath2(String dir, String dir2) {
       Path path = Paths.get(dir);
       Path path2 = Paths.get(dir2);
       Path relativize = path.relativize(path2);
       System.out.println("relativize path : " + relativize.toString());
       Path absolute = relativize.toAbsolutePath();
       System.out.println("to absolute path : " + absolute);
       Path normalized  = absolute.normalize();
       System.out.println("normalized path : " + normalized);

       Path resolved = path.resolve("sample");
       System.out.println("resolved path :  " + resolved);
    }

    private void checkPath(String dir) {
        Path path = Paths.get(dir);
        System.out.println("--------------");
        System.out.println(path.toString());
        System.out.println(path.getFileName());
        System.out.println(path.getFileSystem());
        System.out.println(path.getRoot());
        System.out.println(path.getNameCount());
        System.out.println(path.getParent());
    }
}
