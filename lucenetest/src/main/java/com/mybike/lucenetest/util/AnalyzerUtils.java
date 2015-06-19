package com.mybike.lucenetest.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnalyzerUtils.class);

    public static void displayTokens(Analyzer analyzer, String text) {

        try {
            TokenStream ts = analyzer.tokenStream("", new StringReader(text));
            displayTokens(ts);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    public static void displayTokens(TokenStream ts) {

        try {
            ts.reset();
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            while (ts.incrementToken()) {
                System.out.print(term.toString() + "---");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }


    public static void displayTokensWithPositions(Analyzer analyzer, String text) throws IOException {

        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
        stream.reset();
        CharTermAttribute term = stream.getAttribute(CharTermAttribute.class);
        PositionIncrementAttribute posIncr = stream.addAttribute(PositionIncrementAttribute.class);

        int position = 0;
        while (stream.incrementToken()) {

            int increment = posIncr.getPositionIncrement();
            if (increment > 0) {
                position = position + increment;
                System.out.println();
                System.out.print(position + ":");
            }

            System.out.print("[" + term.toString() + "] ");
        }
        System.out.println();

    }


    public static void displayTokensWithFullDetails(Analyzer analyzer, String text) throws IOException {

        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));
        stream.reset();
        CharTermAttribute term = stream.getAttribute(CharTermAttribute.class);
        PositionIncrementAttribute posIncr = stream.addAttribute(PositionIncrementAttribute.class);

        OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
        TypeAttribute type = stream.addAttribute(TypeAttribute.class);
        PayloadAttribute payload = stream.addAttribute(PayloadAttribute.class);

        int position = 0;
        while (stream.incrementToken()) {

            int increment = posIncr.getPositionIncrement();
            if (increment > 0) {
                position = position + increment;
                System.out.println();
                System.out.print(position + ":");
            }

            BytesRef pl = payload.getPayload();

            if (pl != null) {
                System.out.print("[" +
                        term.toString() + ":" +
                        offset.startOffset() + "->" +
                        offset.endOffset() + ":" +
                        type.type() + ":" +
                        pl.toString() + "] ");

            } else {
                System.out.print("[" +
                        term.toString() + ":" +
                        offset.startOffset() + "->" +
                        offset.endOffset() + ":" +
                        type.type() + "] ");

            }


        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        displayTokensWithPositions(new IKAnalyzer(true), "搜狐掌门人张朝阳");
    }


}
