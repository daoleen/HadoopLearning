package com.daoleen.hadoop.patent.referencescount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by alex on 12.4.14.
 */
public class MapClass extends MapReduceBase implements Mapper<Text, Text, Text, Text> {
    private final static Logger logger = Logger.getLogger(MapClass.class);

    static {
        logger.info("Current mapper thread is: " + Thread.currentThread().getName());
    }

    @Override
    public void map(Text key, Text value, OutputCollector<Text, Text> textTextOutputCollector,
                    Reporter reporter) throws IOException {
        textTextOutputCollector.collect(value, key);
    }
}
