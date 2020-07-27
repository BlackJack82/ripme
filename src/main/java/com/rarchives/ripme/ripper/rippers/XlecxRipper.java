package com.rarchives.ripme.ripper.rippers;

import com.rarchives.ripme.ripper.AbstractHTMLRipper;
import com.rarchives.ripme.utils.Http;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XlecxRipper extends AbstractHTMLRipper {
    private Map<String, String> cookies = new HashMap<>();

    private Pattern p = Pattern.compile("^https?://xlecx.org/([a-zA-Z0-9_\\-]+).html");

    public XlecxRipper(URL url) throws IOException {
        super(url);
    }

    @Override
    public String getHost() {
        return "xlecx";
    }

    @Override
    public String getDomain() {
        return "xlecx.org";
    }

    @Override
    public String getGID(URL url) throws MalformedURLException {
        Matcher m = p.matcher(url.toExternalForm());
        if (m.matches()) {
            return m.group(1);
        }
        throw new MalformedURLException("Expected URL format: http://xlecx.org/comic, got: " + url);

    }

    @Override
    public Document getFirstPage() throws IOException {
        return Http.url(url).get();
    }

    @Override
    public List<String> getURLsFromPage(Document page) {
        List<String> imageURLs = new ArrayList<>();
        Pattern p =  Pattern.compile("<!--(TBegin|dle_image_begin):(?<url>.*?)[|]-->");
        Matcher m = p.matcher(page.html());

        while (m.find()) {
            imageURLs.add(m.group("url"));
        }
        return imageURLs;
    }

    @Override
    public void downloadURL(URL url, int index) {
        addURLToDownload(url, getPrefix(index), "", this.url.toExternalForm(), cookies);
    }

    @Override
    public String getPrefix(int index) {
        return String.format("%03d_", index);
    }
}
