package com.daoleen.hadoop.patent.inverse;

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
public class ReduceClass extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    private static final Logger logger = Logger.getLogger(ReduceClass.class);

    static {
        logger.info("Current reducer thread is: " + Thread.currentThread().getName());
    }

    @Override
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> textTextOutputCollector,
                       Reporter reporter) throws IOException {
        StringBuilder resultString = new StringBuilder();
        while(values.hasNext()) {
            resultString.append(values.next().toString());
            resultString.append(',');
        }

        resultString.deleteCharAt(resultString.length()-1);
        textTextOutputCollector.collect(key, new Text(resultString.toString()));
    }
}
