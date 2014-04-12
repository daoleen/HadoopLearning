package com.daoleen.hadoop.patent.referencescount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by alex on 12.4.14.
 */
public class ReduceClass extends MapReduceBase implements Reducer<Text, Text, Text, IntWritable> {
    private static final Logger logger = Logger.getLogger(ReduceClass.class);

    static {
        logger.info("Current reducer thread is: " + Thread.currentThread().getName());
    }

    @Override
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, IntWritable> textTextOutputCollector,
                       Reporter reporter) throws IOException {
        int count = 0;
        while(values.hasNext()) {
            values.next();
            count++;
        }

        textTextOutputCollector.collect(key, new IntWritable(count));
    }
}
