package com.daoleen.hadoop.patent.citationhistogram;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * Created by alex on 12.4.14.
 */
public class MapClass extends MapReduceBase implements Mapper<Text, Text, IntWritable, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private final IntWritable citationCount = new IntWritable();

    @Override
    public void map(Text key, Text value, OutputCollector<IntWritable, IntWritable> outputCollector,
                    Reporter reporter) throws IOException {
        citationCount.set(Integer.parseInt(value.toString()));
        outputCollector.collect(citationCount, one);
    }
}
