import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by colntrev on 3/11/18.
 * This MapReduce job classifies all points in a file based on KNN classification
 * Path training - This is a path to the file for the training data
 * The format is Dimension 1, Dimension 2, Label
 *
 * Path in - This is the file containing the testing data
 * Format is Dimension 1, Dimension 2
 *
 * Path out - This is the file containing the result
 * Format is Dimension 1, Dimension 2, Label
 *
 * limit - This is the furthest distance away a data point in the training set can be in order to be considered
 * a nearest neighbor
 */
public class KNN {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        Path training = new Path(args[2]);

        conf.set("training", training.toString());
        conf.set("limit", args[3]);
        conf.set("k", args[4]);

        Job job = Job.getInstance(conf);
        job.setJobName("K Nearest Neighbor");

        job.setJarByClass(KNN.class);
        job.setMapperClass(KNNMapper.class);
        job.setReducerClass(KNNReducer.class);

        job.setMapOutputKeyClass(Vector2.class);
        job.setMapOutputValueClass(Vector2.class);
        job.setOutputKeyClass(Vector2.class); // TODO: change to text class
        job.setOutputValueClass(Vector2.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, in);
        FileOutputFormat.setOutputPath(job, out);

        int status = job.waitForCompletion(true)? 0 : 1;
        System.exit(status);

    }
}
