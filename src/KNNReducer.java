import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;

/**
 * Created by colntrev on 3/11/18.
 */
//TODO: Emit Text not Vector2
public class KNNReducer extends Reducer<Vector2, Vector2,Vector2,Vector2>{
    @Override
    protected void reduce(Vector2 key, Iterable<Vector2> values, Context context) throws IOException, InterruptedException {
        Map<String, Integer> labels = new HashMap<>();
        SortedMap<Integer, String> occurences = new TreeMap<>();

        // Tally the counts of each label
        for(Vector2 vec : values){
            labels.put(vec.getLabel(), labels.getOrDefault(vec.getLabel(), 0) + 1);
        }

        // Sort them by occurences in ascending order
        for(HashMap.Entry<String, Integer> entry : labels.entrySet()){
            occurences.put(entry.getValue(), entry.getKey());
        }

        // get the most common key
        Integer res = occurences.lastKey();

        // label our data with that label
        // and write it
        key.setLabel(occurences.get(res));
        context.write(key,null);
    }
}
