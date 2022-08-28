package com.oldphonetoolbox.onear.net.translate;

import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class Translator {
    public static String translate(String langFrom, String langTo,String word) throws Exception {
        String url = "https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

        String text = Jsoup.connect(url).ignoreContentType(true).timeout(5000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").get().text();
        return parseResult(text);
    }

    private static String parseResult(String inputJson) {
        return inputJson.substring(inputJson.indexOf("[[[\"")+4,inputJson.indexOf("\",\""));
    }
}