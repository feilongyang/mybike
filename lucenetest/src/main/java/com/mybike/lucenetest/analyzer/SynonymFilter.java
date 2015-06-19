package com.mybike.lucenetest.analyzer;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;

public class SynonymFilter extends TokenFilter {

    /**
     * Construct a token stream filtering the given input.
     *
     * @param input
     */
    protected SynonymFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }
}
