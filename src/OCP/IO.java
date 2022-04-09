package OCP;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class IO {


    private static void readInputStream() throws IOException {
        /*Byte base reading
         *
         * you should use BufferedInputStream as a wrapper to speed up the the reading
         * BufferedInputStream use the buffering to improve performance
         * BufferedInputStream support mark and reset method , FileInputStream does not
         * The code below is not a good way to read file as file lenght should be a multiple of the length array
         * */


        try (InputStream is = new FileInputStream("zoo.txt")) {
            System.out.println(new String(is.readAllBytes()));
        }

        try (InputStream is = new FileInputStream("zoo.txt")) {
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            while (is.read(buffer, 0, buffer.length) != -1) {
                sb.append(new String(buffer));
            }
            System.out.println(sb);
        }

        try (ByteArrayInputStream bis = new ByteArrayInputStream("stream of byes ! ".getBytes(StandardCharsets.UTF_8))) {
            //ByteArrayInputStream enables you to read data from byte arrays as streams of bytes
        }

    }

    private static void writeInputStream() throws IOException {
        /*byte based
         *
         * BufferedOutputStream is a buffered output stream , thus , increase performance
         *
         * */
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("io.txt"))) {
            bos.write("holly days".getBytes());
        }

    }

    private static void readReader() throws IOException {

//        try (Reader reader = new FileReader("zoo.txt")) {
//            char[] buffer = new char[4];
//            StringBuilder sb = new StringBuilder();
//            while (reader.read(buffer, 0, buffer.length) != -1) { //This method will block until some input is available
//                sb.append(buffer);
//            }
//            System.out.println(sb);
//        }

        try (BufferedReader reader = new BufferedReader(new FileReader("zoo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //reader.reset();//IOException: Stream not marked
        }

        System.out.println("With lazy lines() method : ");
        try (BufferedReader reader = new BufferedReader(new FileReader("zoo.txt"))) {
            reader.lines().forEach(System.out::println);
        }

    }

    static void writeReader() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("writeWithBuffered.txt"))) {
            bufferedWriter.write("hello world !");
            bufferedWriter.newLine();
            bufferedWriter.append("hello every one ! ");
        }
    }


    static void writeDataOutput() throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.bin"))) {
            dataOutputStream.write(10);
            dataOutputStream.write(new byte[]{1, 3, 4});
            dataOutputStream.write(new byte[]{1, 2, 4}, 0, 2);
            dataOutputStream.writeByte(10);
            dataOutputStream.writeFloat(10.3f);
            dataOutputStream.writeLong(19);
            dataOutputStream.writeUTF("World");
        }
    }

    static void readDataInput() throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("data.bin"))) {
            System.out.println(dataInputStream.readByte());
            byte[] buffer = new byte[10];
            dataInputStream.read(buffer, 0, 5);
            System.out.println(Arrays.toString(buffer));
            System.out.println(dataInputStream.read());
            System.out.println(dataInputStream.readFloat());
            System.out.println(dataInputStream.readLong());
            System.out.println(dataInputStream.readUTF());
        }
    }

    public static void main(String... args) throws IOException {

        /*
            *An InputStreamReader is a bridge from byte streams to character streams:
          BufferedReader in
     = new BufferedReader(new InputStreamReader(System.in));
         */


        //readInputStream();
        //writeInputStream();
        //readReader();
        //writeReader();

        writeDataOutput();
        readDataInput();
    }

}


