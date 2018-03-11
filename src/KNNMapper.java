import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by colntrev on 3/11/18.
 */
public class KNNMapper extends Mapper<Text, Text, Vector2, Vector2> {
    private final ArrayList<Vector2> training = new ArrayList<>();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        Path train = new Path(conf.get("training"));
        FileSystem fs = FileSystem.get(conf);
        // read file and store into arraylist
    }

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String[] inputs = value.toString().split(",");
        int counter = 0;
        int k = Integer.parseInt(context.getConfiguration().get("k"));
        double limit = Double.parseDouble(context.getConfiguration().get("limit"));

        Vector2 test = new Vector2(Double.parseDouble(inputs[0]), Double.parseDouble(inputs[1]));
        for(Vector2 vec : training){
            if(test.distance(vec) < limit && counter < k){
                context.write(test, vec);
                counter++;
            }
            if(counter >= k){
                break;
            }
        }
    }
}
