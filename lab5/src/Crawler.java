import java.net.*;

public class Crawler {
    // пул всех адресов
    private URLPool pool;

    public int numThreads = 4;

    // конструктор класса, на кход корневой адрес и макс глубина
    public Crawler(String root, int max) throws MalformedURLException {
        pool = new URLPool(max);

        URL rootURL = new URL(root);
        pool.add(new URLDepthPair(rootURL, 0));
    }

    // запуск кравлера
    public void crawl() {
        // создание кравлеров и потоков, их запуск
        for (int i = 0; i < numThreads; i++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            Thread thread = new Thread(crawler);
            thread.start();
        }
        // если все потоки ждут, значит метод выполнен
        while (pool.getWaitCount() != numThreads) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Ignoring unexpected InterruptedException - " +
                        e.getMessage());
            }
        }
        // вывод всех адресов
        pool.printURLs();
        System.exit(1);
    }
    public static void main(String[] args) throws MalformedURLException{
        if (args.length == 2) {
            Crawler crawler = new Crawler(args[0], Integer.parseInt(args[1]));
            crawler.crawl();
        }
        else {
            System.err.println("Usage: java Crawler <URL> <depth> " + "<patience> -t <threads>");
            System.exit(1);
        }
    }

}
