package com.daoleen.hadoop.patent.inverse.newapi;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by alex on 12.4.14.
 */
public class ReduceClass extends Reducer<Text, Text, Text, Text> {
    private static Logger logger = Logger.getLogger(ReduceClass.class);

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        logger.info("Reducer starts: ");
        logger.info("\tThread: " + Thread.currentThread().getName());
        StringBuilder resultString = new StringBuilder();

        for(Text value : values) {
            resultString.append(value.toString()).append(',');
        }

        resultString.deleteCharAt(resultString.length()-1);
        context.write(key, new Text(resultString.toString()));
        logger.info("Reducer ends");
    }
}
