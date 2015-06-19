package com.mybike.lucenetest.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import java.io.Reader;

/**
 * 测试自定义Lucene的分词器
 */
public class TestAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {

        Tokenizer tokenizer = new TestTokenizer(reader);
        return new TokenStreamComponents(tokenizer);
    }
}
