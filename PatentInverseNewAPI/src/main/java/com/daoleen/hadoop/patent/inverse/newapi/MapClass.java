package com.daoleen.hadoop.patent.inverse.newapi;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by alex on 12.4.14.
 */
public class MapClass extends Mapper<Text, Text, Text, Text> {
    private final Logger logger = Logger.getLogger(MapClass.class);
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        logger.debug("Mapper starts: ");
        logger.debug("\tThread: " + Thread.currentThread().getName());
        context.write(value, key);
        logger.debug("Mapper ends");
    }
}
