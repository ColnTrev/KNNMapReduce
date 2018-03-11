import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by colntrev on 3/11/18.
 */
public class Vector2 implements WritableComparable<Vector2> {
    private double[] point;
    private String label;

    public Vector2(double x, double y, String l){
        super();
        point = new double[]{x,y};
        label = l;
    }

    public Vector2(double x, double y){
        super();
        point = new double[]{x,y};
        label = "";
    }
    @Override
    public int compareTo(Vector2 vector2) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }
    public double distance(Vector2 vec){
        double xs = this.getXCoordinate() - vec.getXCoordinate();
        double ys  = this.getYCoordinate() - vec.getYCoordinate();
        return Math.sqrt((xs * xs) + (ys * ys));
    }

    public double getXCoordinate(){
        return point[0];
    }

    public double getYCoordinate(){
        return point[1];
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String s){
        label = s;
    }
    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
