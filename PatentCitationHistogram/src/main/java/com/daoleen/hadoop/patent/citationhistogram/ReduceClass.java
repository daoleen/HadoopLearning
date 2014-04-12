package com.daoleen.hadoop.patent.citationhistogram;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by alex on 12.4.14.
 */
public class ReduceClass extends MapReduceBase implements Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
    @Override
    public void reduce(IntWritable key, Iterator<IntWritable> value, OutputCollector<IntWritable, IntWritable> outputCollector,
                       Reporter reporter) throws IOException {
        int count = 0;
        while(value.hasNext()) {
            count += value.next().get();
        }

        outputCollector.collect(key, new IntWritable(count));
    }
}
