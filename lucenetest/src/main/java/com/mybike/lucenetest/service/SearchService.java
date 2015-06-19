package com.mybike.lucenetest.service;


import com.mybike.lucenetest.domain.VideoInfo;
import com.mybike.lucenetest.util.ConfigUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private IndexSearcher indexSearcher;
    private Query query;
    private String searchField = ConfigUtils.get("search_field");
    private String indexPath = ConfigUtils.get("index_path");


    //实例化一个IndexSearcher实例
    public SearchService() {

        try {
            IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(indexPath)));
            indexSearcher = new IndexSearcher(indexReader);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public List<VideoInfo> search(String keyword) {

        ArrayList<VideoInfo> list = new ArrayList<VideoInfo>();
        try {
            Analyzer analyzer = new IKAnalyzer(true);
            QueryParser queryParser = new QueryParser(Version.LUCENE_4_9, searchField, analyzer);
            query = queryParser.parse(keyword);

            TopDocs topDocs = indexSearcher.search(query, 10);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            System.out.println(scoreDocs.length);

            for (ScoreDoc scoreDoc : scoreDocs) {
                VideoInfo video = new VideoInfo();
                Document doc = indexSearcher.doc(scoreDoc.doc);
                String videoName = doc.get("videoName");

                //高亮
                SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
                TokenStream ts = analyzer.tokenStream("title", new StringReader(videoName));
                QueryScorer scorer = new QueryScorer(query, searchField);
                Highlighter highlighter = new Highlighter(htmlFormatter, scorer);
                highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));
                String highLightTitle = highlighter.getBestFragment(ts, videoName);
                video.setVideoName(highLightTitle);
                list.add(video);
            }
        } catch (Exception e) {
            //logger.error("", e);
            e.printStackTrace();
        }
        return list;
    }
}
