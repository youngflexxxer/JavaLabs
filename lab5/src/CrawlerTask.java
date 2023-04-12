import java.io.*;
import java.net.*;
import java.util.regex.*;

public class CrawlerTask implements Runnable {
    public static final String LINK_REGEX = "href\\s*=\\s*\"([^$^\"]*)\"";
    public static final Pattern LINK_PATTERN = Pattern.compile(LINK_REGEX, Pattern.CASE_INSENSITIVE);
    public static final int port = 80;
    public static final int timeout = 5;
    
    private URLPool pool;
    
    public CrawlerTask(URLPool p) {
        pool = p;
    }

    @Override
    public void run() {
        URLDepthPair nextPair;
        while (true) {
            nextPair = pool.get();
            try {
                processURL(nextPair);
            }
            catch (IOException e) {
                System.err.println("Error reading the page at " + nextPair.getURL() + " - " + e.getMessage()); 
            }
        }
    }
    // отправление запроса, получает пару, возвращает сокет 
    public Socket sendRequest(URLDepthPair nextPair) throws UnknownHostException, SocketException, IOException {
    // создает новый HTTP сокет
    Socket socket = new Socket(nextPair.getHost(), port);
    socket.setSoTimeout(timeout * 1000);

    // вызов потока вывода
    OutputStream os = socket.getOutputStream();
    // преобразование полученных символов в байты
    PrintWriter writer = new PrintWriter(os, true);

    // обращение к серверу
    writer.println("GET " + nextPair.getDocPath() + " HTTP/1.1");
    writer.println("Host: " + nextPair.getHost());
    writer.println("Connection: close");
    writer.println();

    return socket;
    } 
    // работа с адресом
public void processURL(URLDepthPair url) throws IOException {
    Socket socket;
    try {
    socket = sendRequest(url);
    } catch (UnknownHostException e) {
    System.err.println("Host " + url.getHost() + " couldn't be determined " + e.getMessage());
    return;
    } catch (SocketException e) {
    System.err.println("Error with socket connection: " + url.getURL() +
    " - " + e.getMessage());
    return;
    } catch (IOException e) {
    System.err.println("Couldn't retrieve page at " + url.getURL() +
    " - " + e.getMessage());
    return;
    }
    
    // получение информации от потока сокета
    InputStream input = socket.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    
    String line;
    // пока строка не пустая
    while ((line = reader.readLine()) != null) {
        // поиск паттерна в строке
        Matcher LinkFinder = LINK_PATTERN.matcher(line);
        // поиск последующего элемента инпутстрима, подходящего по паттерну
        while (LinkFinder.find()) {
            String newURL = LinkFinder.group(1);
            URL newSite;
            try {
            if (URLDepthPair.isAbsolute(newURL)) {
            newSite = new URL(newURL);
            } else {
            newSite = new URL(url.getURL(), newURL);
    }
    pool.add(new URLDepthPair(newSite, url.getDepth() + 1));
    }
    catch (MalformedURLException e) {
        System.err.println("Error with URL - " + e.getMessage());
    }
    }
    }
    reader.close();
    // закрытие сокета
    try {
    socket.close();
    } catch (IOException e) {
    System.err.println("Couldn't close connection to " + url.getHost() + " - " + e.getMessage());
    }
    }
    
}
