import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

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
    public static void main(String[] args){
        Configuration conf = new Configuration();
        Path in = new Path(args[0]);
        Path out = new Path(args[1]);
        Path training = new Path(args[2]);

        conf.set("training", training.toString());
        conf.set("limit", args[3]);
        conf.set("k", args[4]);
    }
}
