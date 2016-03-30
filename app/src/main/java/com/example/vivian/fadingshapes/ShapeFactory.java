package com.example.vivian.fadingshapes;
import android.content.Context;

/**
 * Created by Vivian on 3/21/16.
 */

public class ShapeFactory {

    public Shape getShape(Context context, String shape){
        if(shape == null){
            return null;
        }
        if(shape.equals("circle")){
            return new Circle(context);

        } else if(shape.equals("rectangle")){
            return new Rectangle(context);
        }
        return null;
    }
}
