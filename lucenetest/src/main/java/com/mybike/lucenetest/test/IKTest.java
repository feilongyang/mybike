package com.mybike.lucenetest.test;

import com.mybike.lucenetest.analyzer.TestAnalyzer;
import com.mybike.lucenetest.util.AnalyzerUtils;

public class IKTest {

    public static void main(String[] args) {

        //IKAnalyzer analyzer = new IKAnalyzer(true);
        //TestAnalyzer testAnalyzer = new TestAnalyzer();
        TestAnalyzer analyzer = new TestAnalyzer();

        AnalyzerUtils.displayTokens(analyzer,"搜狐的总裁张朝阳");
    }
}
