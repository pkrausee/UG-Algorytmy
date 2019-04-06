import java.io.*;

abstract class FileSupport {
    public static byte[] readFile(String path) {
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];

            is.read(bytes);
            is.close();

            return bytes;
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
            fe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    public static void saveFile(byte[] file, String fileName) {
        try {
            OutputStream os = new FileOutputStream(fileName);

            os.write(file);
            os.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
