import java.net.*;
import java.util.LinkedList;
import java.io.*;

public class Crawler {
    public static final String URL_PREFIX = "http://";
    public static final int port = 80;    

    LinkedList <URLDepthPair> unprocessedUrlDepthPairs = new LinkedList <URLDepthPair> ();
    LinkedList <URLDepthPair> processedUrlDepthPairs = new LinkedList <URLDepthPair> ();

    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        if (args.length == 2) {
            scan(args[0], Integer.parseInt(args[1]));
        }
    }
    public static void scan(String URL, int depth) throws UnknownHostException, IOException {
        Socket socket = new Socket(URL, port);
        socket.setSoTimeout(10000);
        InputStreamReader in = new InputStreamReader (socket.getInputStream());
        BufferedReader reader = new BufferedReader(in);
        String line = reader.readLine();
        while(line != null) {
            System.out.println(line);
        }
        socket.close();
    }
}
