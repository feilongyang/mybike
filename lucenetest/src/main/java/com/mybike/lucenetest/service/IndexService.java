package com.mybike.lucenetest.service;


import com.mybike.lucenetest.domain.VideoInfo;
import com.mybike.lucenetest.util.ConfigUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class IndexService {

    private static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    private IndexWriter indexWriter;
    private String indexPath = ConfigUtils.get("index_path");

    public IndexService() {
        try {
            Directory indexDir = FSDirectory.open(new File(indexPath));

            Analyzer analyzer = new IKAnalyzer(true);


            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            indexWriter = new IndexWriter(indexDir, config);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    private void build(VideoInfo vi) {

        try {
            String videoName = vi.getVideoName();
            Document doc = new Document();
            //添加索引字段
            TextField videoNameField = new TextField("videoName", videoName, Field.Store.YES);
            doc.add(videoNameField);
            //将索引字段添加到索引库
            indexWriter.addDocument(doc);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public void build(List<VideoInfo> list) {

        for (VideoInfo vi : list) {
            build(vi);
        }
        try {
            indexWriter.close();
        } catch (Exception e) {
            logger.error("", e);
        }
    }


}
