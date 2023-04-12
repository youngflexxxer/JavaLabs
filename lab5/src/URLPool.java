import java.util.*;;

public class URLPool {
    LinkedList <URLDepthPair> pendingURLs;
    LinkedList <URLDepthPair> processedURLs;

    private HashSet<String> seenURLs;

    private int maxDepth;

    private int waitCount;

    public URLPool(int max) {
        pendingURLs = new LinkedList <URLDepthPair> ();
        processedURLs = new LinkedList <URLDepthPair> ();
        seenURLs = new HashSet<String>();
        maxDepth = max;
    }

    // возвращает количество ожидающих потоков
    public synchronized int getWaitCount() {
        return waitCount;
    }
    
    // добавление адресов в список проверенных
    public synchronized void add(URLDepthPair nextPair) {
        String newURL = nextPair.getURL().toString();
        // убирает слеши для единого отображения
        String trimURL = (newURL.endsWith("/")) ? newURL.substring(0, newURL.length() - 1) : newURL;

        // если уже содержится то программа прерывается
        if (seenURLs.contains(trimURL)) {
            return;
        }
        seenURLs.add(trimURL);

        if (nextPair.getDepth() < maxDepth) {
            pendingURLs.add(nextPair);
            // оповещаем поток о новой паре на проверку
            notify();
        }
        processedURLs.add(nextPair);
    }
    
    // возвращает количество ожидающих потоков
    public synchronized URLDepthPair get() {
        // Приостановка потока до тех пор, пока не будет добавлен новый адрес
        while (pendingURLs.size() == 0) {
            waitCount++;
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("Ignoring unexpected InterruptedException - " + e.getMessage());
            }
            waitCount--;
        }
        return pendingURLs.removeFirst();
    }
    
    // вывод всех проработанных адресов
    public synchronized void printURLs() {
        System.out.println("\nUnique URLs Found: " + processedURLs.size());
        while (!processedURLs.isEmpty()) {
        System.out.println(processedURLs.removeFirst());
    }

}
}
