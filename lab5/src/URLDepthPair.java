import java.net.*;
import java.util.regex.*;

public class URLDepthPair {
    public static final String URL_PREFIX = "http: //";
    public static final Pattern URL_PATTERN = Pattern.compile(URL_PREFIX, Pattern.CASE_INSENSITIVE);
    private URL URL;
    private int depth;

    public URLDepthPair(URL url, int d) throws MalformedURLException{
        URL = new URL(url.toString());
        depth = d;
    }

    @Override
    public String toString() {
        return URL.toString() + " " + depth;
    }

    public URL getURL() {
        return URL;
    }
        
    public int getDepth() {
    return depth;
    }
        
    public String getHost() {
    return URL.getHost();
    }
    
    public String getDocPath() {
    return URL.getPath();
    }

    public static boolean isAbsolute(String url) {
        Matcher URLMatcher = URL_PATTERN.matcher(url);
        if(URLMatcher.find()) {
            return true;
        }
        return false;
    }
        
}
