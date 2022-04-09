package OCP;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.stream.Collectors;

public class NIO {


    static void generalInfos() throws URISyntaxException {
        //Path pathWithUri = Paths.get(new URI("")); //throws checked URISyntaxException
        Path p = Paths.get("parent/zoo.txt");

        //get file name
        System.out.println("Get file name : ");
        System.out.println(Paths.get("parent", "zoo.txt").getFileName());//zoo.txt

        //get file system
        System.out.println("Get file system : ");
        System.out.println(Paths.get("parent/zoo.txt").getFileSystem());//sun.nio.fs.WindowsFileSystem@404b9385

        //get name part
        System.out.println("Get name : ");
        System.out.println(Paths.get("parent/zoo.txt").getName(0));//parent
        System.out.println(Paths.get("").getName(0));//empty
        System.out.println(Paths.get(".").getName(0));//.
        System.out.println(Paths.get("c:\\parent/zoo.txt").getName(0));//parent
        System.out.println(Paths.get("/parent/zoo.txt").getName(0));//parent

        //get root
        System.out.println("Get root : ");
        System.out.println(Paths.get("parent/zoo.txt").getRoot());//null
        System.out.println(Paths.get("/parent/zoo.txt").getRoot());//\
        System.out.println(Paths.get("c:\\parent/zoo.txt").getRoot());//c:\

        //get parent
        System.out.println("Get parent : ");
        System.out.println(Paths.get("").getParent());//null
        System.out.println(Paths.get("zoo.txt").getParent());//null
        System.out.println(Paths.get("./zoo.txt").getParent());//.
        System.out.println(Paths.get("../../zoo.txt").getParent());//..\..
        System.out.println(Paths.get("parent/zoo.txt").getParent());//parent
        System.out.println(Paths.get("c:\\parent/child/zoo.txt").getParent());//c:\parent\child
        System.out.println(Paths.get("./child/zoo.txt").getParent());//.\child

        //get name count
        System.out.println("Get name count : ");
        System.out.println(Paths.get("").getNameCount());//1
        System.out.println(Paths.get("zoo.txt").getNameCount());//1
        System.out.println(Paths.get("parent/zoo.txt").getNameCount());//2
        System.out.println(Paths.get("c:\\parent/child/zoo.txt").getNameCount());//3
        System.out.println(Paths.get("/parent/child/zoo.txt").getNameCount());//3

        //file size

        try {
            System.out.println("File size : ");
            System.out.println(Files.size(Path.of("zoo.txt")));// ? bytes
        } catch (IOException e) {
            e.printStackTrace();
        }

        //is absolute path
        System.out.println(Paths.get("").isAbsolute());//false
        System.out.println(Paths.get("/").isAbsolute());//false (on windows)
        System.out.println(Paths.get("/zoo.txt").isAbsolute());//false (on windows)
        System.out.println(Paths.get("c:\\parent/child/zoo.txt").isAbsolute());//true

        //files exists (follow links by default)
        System.out.println("File exists : ");
        System.out.println(Files.exists(Paths.get("notFound.txt")));//false
        System.out.println(Files.exists(Paths.get("zoo.txt")));//true

        //file attributes
        System.out.println("Files attributes : ");
        System.out.println(Files.isDirectory(Paths.get("")));//true
        System.out.println(Files.isDirectory(Paths.get("zoo.txt")));//false
        System.out.println(Files.isRegularFile(Paths.get("zoo.txt")));//true
        System.out.println(Files.isReadable(Paths.get("zoo.txt")));//true , checks if exists & that Java virtual machine has appropriate privileges
        System.out.println(Files.isWritable(Paths.get("zoo.txt")));//true ,  checks if exists & that Java virtual machine has appropriate privileges
        try {
            System.out.println(Files.isHidden(Paths.get("zoo.txt")));//false ,  checks if exists & that Java virtual machine has appropriate privileges
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Files.isSymbolicLink(Paths.get("zoo.txt")));//false
        System.out.println(Files.isExecutable(Paths.get("zoo.txt")));//true , checks if exists & that Java virtual machine has appropriate privileges to execute
        System.out.println(Files.isExecutable(Paths.get("mods/care.jar")));//true
        try {
            System.out.println(Files.getFileStore(Paths.get("zoo.txt")));//OS (C:)
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(Files.getOwner(Paths.get("zoo.txt")));//DESKTOP-LP1KIDD\Anas (User)
        } catch (IOException e) {
            e.printStackTrace();
        }

        //attributes view
        System.out.println("Attributes view : ");
        DosFileAttributeView attributes = Files.getFileAttributeView(Paths.get("zoo.txt"), DosFileAttributeView.class);
        System.out.println(attributes.name());
        try {
            DosFileAttributes dosFileAttributes = attributes.readAttributes();
            System.out.println("DosFileAttributes : ");
            System.out.println(dosFileAttributes.isSystem());//false
            System.out.println(dosFileAttributes.isHidden());//false
            System.out.println(dosFileAttributes.isArchive());//true
            System.out.println(dosFileAttributes.isReadOnly());//false
            System.out.println(dosFileAttributes.isDirectory());//false
            System.out.println(dosFileAttributes.isRegularFile());//true
            System.out.println(dosFileAttributes.creationTime());//2021-09-18T14:58:11.6275107Z
            System.out.println(dosFileAttributes.lastAccessTime());//2021-09-21T12:26:40.3373189Z
            System.out.println(dosFileAttributes.lastModifiedTime());//2021-09-18T14:58:11.6275107Z
            System.out.println(dosFileAttributes.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            attributes.setHidden(false);
//            attributes.setArchive(true);
//            attributes.setReadOnly(true);
//            attributes.setSystem(true);
//       attributes.setTimes(FileTime.from(Instant.now()) , FileTime.from(Instant.now()) , FileTime.from(Instant.now()) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void subPaths() {
        //start with
        System.out.println("Start with : ");
        System.out.println(Paths.get("").startsWith(""));//true
        System.out.println(Paths.get("./zoo.txt").startsWith("."));//true
        System.out.println(Paths.get("zoo.txt").startsWith("zoo.txt"));//true
        System.out.println(Paths.get("parent/zoo.txt").startsWith(Paths.get("parent")));//true
        System.out.println(Paths.get("c:\\parent/child/zoo.txt").startsWith("c:\\"));//true
        System.out.println(Paths.get("/zoo.txt").startsWith("."));//false
        System.out.println(Paths.get("/zoo.txt").startsWith("/"));//true

        //end with
        System.out.println("End with : ");
        System.out.println(Paths.get("").endsWith(""));//true
        System.out.println(Paths.get("zoo.txt").endsWith("zoo.txt"));//true
        System.out.println(Paths.get("parent/zoo.txt").endsWith(Paths.get("zoo.txt")));//true
        System.out.println(Paths.get("c:\\").endsWith("c:\\"));//true

        //sub path (beginIndex : inclusive , endIndex : exclusive)
        System.out.println("Sub path");
        //System.out.println(Paths.get("").subpath(0,0));//IllegalArgumentException
        System.out.println(Paths.get("").subpath(0, 1));//empty
        System.out.println(Paths.get("c:\\a\\b").subpath(0, 1));//a
        System.out.println(Paths.get("./zoo.txt").subpath(0, 1));//.
        System.out.println(Paths.get("../../zoo.txt").subpath(0, 2));//..\..
        System.out.println(Paths.get("parent/child/zoo.txt").subpath(0, 2));//parent\child
        System.out.println(Paths.get("/parent/child/zoo.txt").subpath(0, 2));//parent\child
        System.out.println(Paths.get("./parent/child/zoo.txt").subpath(0, 2));//.\parent

    }

    static void transformations() {
        //Normalize : Returns a path that is this path with redundant name elements eliminated(. , ..)
        System.out.println("Normalize : ");
        System.out.println(Paths.get(".").normalize());//empty
        System.out.println(Paths.get("./").normalize());//empty
        System.out.println(Paths.get("./zoo.txt").normalize());//zoo.txt
        System.out.println(Paths.get(".././../zoo.txt").normalize());//..\..\zoo.txt
        System.out.println(Paths.get("anas/../zoo.txt").normalize());//zoo.txt

        //to absolute path
        System.out.println("To absolute path : ");
        System.out.println(Paths.get(".").toAbsolutePath());//C:\Users\Anas\IdeaProjects\OCP_Java\.
        System.out.println(Paths.get("./").toAbsolutePath());//C:\Users\Anas\IdeaProjects\OCP_Java\.
        System.out.println(Paths.get("./zoo.txt").toAbsolutePath());//C:\Users\Anas\IdeaProjects\OCP_Java\.\zoo.txt
        System.out.println(Paths.get("/parent/zoo.txt").toAbsolutePath());//C:\parent\zoo.txt
        System.out.println(Paths.get("c:\\anas.txt").toAbsolutePath());//c:\anas.txt

        //relativize
        //file1.relativize(file2) supposing that the two paths have the same root
        //how could we get to file2 location from file1 location.
        //IllegalArgumentException if one argument is an absolute path

        System.out.println("Relativize : ");
        System.out.println(Paths.get(".").relativize(Paths.get(".")));//empty
        System.out.println(Paths.get("a").relativize(Paths.get("b")));//..\b
        System.out.println(Paths.get("a/b/d").relativize(Paths.get("c")));//..\..\..\c
        System.out.println(Paths.get("a/b/d").relativize(Paths.get("a/b")));//..
        System.out.println(Paths.get("a/b/d").relativize(Paths.get("a/e")));//..\..\e
        System.out.println(Paths.get("/a").relativize(Paths.get("e")));//.IllegalArgumentException: 'other' is different type of Path

        //Resolving (join if two paths are relatives , unspecified if one path is absolute..)
        System.out.println("Resolving : ");
        System.out.println(Paths.get(".").resolve(Paths.get(".")));//.\.
        System.out.println(Paths.get("a").resolve(Paths.get("b")));//a\b
        System.out.println(Paths.get("a/b").resolve(Paths.get("b")));//a\b\b
        System.out.println(Paths.get("/a").resolve(Paths.get("/b")));//\b
        System.out.println(Paths.get("/a").resolve(Paths.get("b")));//\a\b
        System.out.println(Paths.get("/a").resolve(Paths.get("../../b")));//\a\..\..\b
        System.out.println(Paths.get("a").resolve(Paths.get("../../b")));//a\..\..\b


        //to Real path
        //By default, symbolic links are resolved to their final target
        System.out.println("To real Path : ");
        try {
            System.out.println(Paths.get(".").toRealPath());//C:\Users\Anas\IdeaProjects\OCP_Java
            System.out.println(Paths.get("./").toRealPath());//C:\Users\Anas\IdeaProjects\OCP_Java
            System.out.println(Paths.get("zoo.txt").toRealPath());//C:\Users\Anas\IdeaProjects\OCP_Java\zoo.txt
            // System.out.println(Paths.get("notFoundFile.txt").toRealPath());//NoSuchFileException
        } catch (IOException e) {
            e.printStackTrace();
        }

        //to legacy io file
        System.out.println("To Legacy io File : ");
        File file = Paths.get(".").toFile();

        //legacy file to Path
        System.out.println("Legacy file to path : ");
        Path path = new File("zoo.txt").toPath();

        //to URI
        System.out.println("To Uri : ");
        System.out.println(Paths.get("zoo.txt").toUri());//file:///C:/Users/Anas/IdeaProjects/OCP_Java/zoo.txt
    }

    static void creating() {

        try {
            //Files.createFile(Paths.get("newFile.txt")); //FileAlreadyExistsException
            //Files.createDirectory(Paths.get("myDir1"));//NoSuchFileException if an element not exists , FileAlreadyExistsException
            //Files.createDirectories(Paths.get("myDir2/myChildDir"));//creates all elements
            //Files.createSymbolicLink(Paths.get("link") , Paths.get("target"));
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void copying() {
        /*the content of non empty directories are not coppied , use walkTree method */

        try {
            //Files.copy(Path.of("zoo.txt") , Path.of("myDir1" , "zooCopy.txt") , StandardCopyOption.COPY_ATTRIBUTES);
            Files.copy(Path.of("zoo.txt"), Files.newOutputStream(Path.of("myDir1", "zooCopy.txt"), StandardOpenOption.TRUNCATE_EXISTING));
            //StandardOpenOption.TRUNCATE_EXISTING will overwrite the file
            //Files.copy(new FileInputStream("zoo.txt"), Path.of(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void moving() {
                    /*
            move or rename a file.
            failing if the target file exists except if the source and target are the same file,
            in which case this method has no effect. If the file is a symbolic link then the symbolic link itself is moved.

            FileAlreadyExistsException - if the target file exists but cannot be replaced because the REPLACE_EXISTING option is not specified

           */

        try {

            Files.move(Path.of("zoo.txt"), Path.of("zoo.txt"));//No Effect
            Files.move(Path.of("zoo2.txt"), Path.of("zoo.txt"));//renaming
            Files.move(Path.of("zoo.txt"), Path.of("nonExistingDir/zoo.txt"));//NoSuchFileException
            Files.move(Path.of("myDir3"), Path.of("myDir1/myDir3"));//move myDir3 and its subdirectories and files
            Files.move(Path.of("myDir3"), Path.of("myDir1"), StandardCopyOption.REPLACE_EXISTING);//DirectoryNotEmptyException: myDir1
            Files.move(Path.of("myDir3"), Path.of("newDir3"), StandardCopyOption.REPLACE_EXISTING);//DirectoryNotEmptyException: myDir1
            Files.move(Path.of("myDir1"), Path.of("newDir3"), StandardCopyOption.REPLACE_EXISTING);//OK , because newDir3 is empty
            Files.move(Path.of("myDir3"), Path.of("myDir1"));//FileAlreadyExistsException: myDir1

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deleting() {

        /*
        Deletes a file.
        this method may not be atomic with respect to other file system operations.
        delete the symbolic link not the target itself
        the directory must be empty. use walkFileTree method to delete a directory and all entries in the directory

        NoSuchFileException – if the file does not exist (optional specific exception)
        DirectoryNotEmptyException – because the directory is not empty (optional specific exception)
        java.io.IOException – if an I/O error occurs
        */


        try {
            //Files.delete(Path.of("newFile.txt"));
            System.out.println(Files.deleteIfExists(Path.of("notFoundFile.txt")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void comparing() {

        /*

        If both Path objects are equal , returns true without checking if the file exists

        return true if, and only if, the two paths locate the same file
         */


        try {
            System.out.println(Files.isSameFile(Path.of("zoo.txt"), Path.of("zoo.txt")));//true
            System.out.println(Files.isSameFile(Path.of("./zoo.txt"), Path.of("zoo.txt")));//true
            System.out.println(Files.isSameFile(Path.of("./zoo.txt"), Path.of("../OCP_Java/zoo.txt")));//true
            System.out.println(Files.isSameFile(Path.of("zoo.txt").toAbsolutePath(), Path.of("zoo.txt")));//true

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(Path.of("hello/zoo.txt").compareTo(Path.of("./hello/zoo.txt")));// != 0 , the comparison is string literal

        try {
            /*

            Finds and returns the position of the first mismatched byte in the content of two files, or -1L if there is no mismatch.
            Two files are considered to match if they satisfy one of the following conditions:

            The two paths locate the same file, even if two equal paths locate a file does not exist, or
            The two files are the same size, and every byte in the first file is identical to the corresponding byte in the second file.

            Otherwise there is a mismatch between the two files and the value returned by this method is:

            The position of the first mismatched byte, or
            The size of the smaller file (in bytes) when the files are different sizes and every byte of the smaller file is identical to the corresponding byte of the larger file.

             */
            Files.mismatch(Path.of("zoo.txt"), Path.of("bigZoo.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void reading() throws IOException {
        //default charset : StandardCharsets.UTF_8

        //read String
        System.out.println("read String : ");
        System.out.println(Files.readString(Path.of("zoo.txt")));

        //read all bytes
        System.out.println("read all bytes : ");
        System.out.println((char) Files.readAllBytes(Path.of("zoo.txt"))[0]);

        //read all lines
        System.out.println("read all lines : ");
        Files.readAllLines(Path.of("zoo.txt")).forEach(System.out::println);

        //read lines as a Stream with Lazy load , ideal for large file
        System.out.println("read lines (streams) : ");
        try (var lines = Files.lines(Path.of("zoo.txt"))) {
            lines.forEach(System.out::println);
        }

        //read with buffered reader
        Files.newBufferedReader(Path.of("zoo.txt"));
        Files.newInputStream(Path.of("zoo.txt"), StandardOpenOption.CREATE);
    }

    static void writing() throws IOException {
        /*
        The options parameter specifies how the file is created or opened.
        If no options are present then this method works as if the CREATE, TRUNCATE_EXISTING, and WRITE options are present.
         In other words, it opens the file for writing, creating the file if it doesn't exist,
         or initially truncating an existing regular-file to a size of 0.
         */
        Files.writeString(Path.of("newZoo.txt"), "hello world\n");
        Files.writeString(Path.of("newZoo.txt"), new StringBuilder("second line"), StandardOpenOption.APPEND);

        Files.write(Path.of("bigZoo.txt"), Files.readAllLines(Path.of("newZoo.txt")));

        //get Buffered writer

        Files.newBufferedWriter(Path.of("zoo.txt"), StandardOpenOption.APPEND).close();
    }

    static void traversingAndSearching() throws IOException {

        //Files lists
        System.out.println("Files lists : ");
        System.out.println(Files.list(Path.of(".")).collect(Collectors.toList()));

        //Files find ( lazily populated )
        var files = Files.find(Path.of(""), 5, (path, basicFileAttributes) -> basicFileAttributes.isRegularFile());
        System.out.println(files.collect(Collectors.toList()));

        //Files newDirectoryStream()
        System.out.println("newDirectoryStream with is regular filter : ");
        try (var directories = Files.newDirectoryStream(Path.of(""), entry -> Files.isRegularFile(entry))) {

            for (var directory : directories) {
                System.out.println(directory);
            }
        }

        System.out.println("newDirectoryStream with is text file filter : ");
        try (var directories = Files.newDirectoryStream(Path.of(""), "*.txt")) {

            for (var directory : directories) {
                System.out.println(directory);
            }
            /*
            bigZoo.txt
            newZoo.txt
            zoo.txt
             */
        }

        // Files walking
        /*
        Return a Stream that is lazily populated with Path by walking(depth-first) the file tree rooted at a given starting file.

        The Stream returned is guaranteed to have at least one element, the starting file itself.

        When all entries have been visited, then the directory is closed.

        Symbolic links are not automatically followed by this method.

        When following links, the stream keeps track of directories visited so that cycles can be detected(throws FileSystemLoopException).

        The returned stream contains references to one or more open directories. The directories are closed by closing the stream.
         */
        System.out.println("Files Walking : ");
        try (var entries = Files.walk(Path.of(""), 10)) {
            //looks for text files
            System.out.println(entries.filter(path -> path.toString().contains(".txt")).collect(Collectors.toList()));
        }

        // Files.walk(p) , do not take maxDepth eq : walk(start, Integer.MAX_VALUE, options)

        //walk file tree
        //with this method you can delete a file if you want
        System.out.println("Walk File Tree : ");
        Files.walkFileTree(Path.of(""), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("I'm entering this directory : " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("Hi , I'm on the " + file + " file");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("I'm quiting " + dir + " directory , it was a great visit ");
                return FileVisitResult.CONTINUE;
            }
        });//as walkFileTree(start, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, visitor)

        //read Symbolic link

        System.out.println("get Symbolic link");
        //Files.readSymbolicLink(Path.of("link"));//return Path , throws NotLinkException – if is not a symbolic link
    }

    public static void main(String... args) throws IOException, URISyntaxException {
        //Open Mode
//        StandardOpenOption.CREATE;//Create a new file if it does not exist.
//        StandardOpenOption.APPEND;//Append
//        StandardOpenOption.CREATE_NEW;//Create a new file, failing if the file already exists
//        StandardOpenOption.TRUNCATE_EXISTING;//If the file already exists and it is opened for WRITE access, then its length is truncated to 0
//        StandardOpenOption.DELETE_ON_CLOSE;//
//        StandardOpenOption.READ;
//        StandardOpenOption.WRITE;


        generalInfos();
//        transformations();
        //creating();
        //copying();
        //moving();
        //traversingAndSearching();
        //reading();
        //writing();
        //deleting();
        //comparing();
//        subPaths();
    }

}


