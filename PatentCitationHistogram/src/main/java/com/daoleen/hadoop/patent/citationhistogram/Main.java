package com.daoleen.hadoop.patent.citationhistogram;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * CitationHistogram module
 * Производит подсчет количества патентов, цитированных
 * один раз, два раза, три раза и т.д. для дальнейшего построения
 * гистограммы на основе полученного результата, где будет наглядно
 * видно сколько патентов цитировались сотню раз, а какие ни разу.
 * Пример вывода:
 * 1     7       - т.е. есть 7 патентов, которые цитировались 1 раз
 * 2    3       - т.е. есть 3 патента, которые цитировались 2 раза
 * 3    9       - т.е. есть 9 патентов, которые цитировались 3 раза
 *
 * -> input: В качестве входных данных используются данные, полученные из
 * модуля PatentReferencesCount, представляющие собой количество
 * ссылок на патент.
 *
 * Created by alex on 12.4.14.
 */
public class Main extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        JobConf job = new JobConf(conf);
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        FileInputFormat.setInputPaths(job, in);
        FileOutputFormat.setOutputPath(job, out);

        job.setJobName("PatentCitationHistogram");
        job.setMapperClass(MapClass.class);
        job.setReducerClass(ReduceClass.class);
        job.setInputFormat(KeyValueTextInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        JobClient.runJob(job);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Main(), args);
        System.exit(res);
    }
}
