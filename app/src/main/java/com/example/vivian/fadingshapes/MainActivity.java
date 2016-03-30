package com.example.vivian.fadingshapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Vector;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String shapeName;
    Shape shapeCreated;
    ShapeFactory shapeFactory = new ShapeFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hides title bar
        getSupportActionBar().hide();

        final RelativeLayout shapesLayout = (RelativeLayout) findViewById(R.id.shapesLayout);

        // Clears the application's screen and resets all variables

        Button clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapesLayout.removeAllViews();
                shapeVector.clear();
                updateShapesCount();
            }
        });

        Button circleBtn = (Button) findViewById(R.id.circle);
        circleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeName = "circle";
                adjustShapesAlpha();
                // get shapeCreated from factory
                shapeCreated = shapeFactory.getShape(view.getContext(), shapeName);
                // add shapeCreated to vector
                shapeVector.addElement(shapeCreated);
                shapesLayout.addView(shapeCreated);
                shapesLayout.invalidate();
                updateShapesCount();
            }
        });

        Button rectBtn = (Button) findViewById(R.id.rectangle);
        rectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shapeName = "rectangle";
                adjustShapesAlpha();
                // get shapeCreated from factory
                shapeCreated = shapeFactory.getShape(view.getContext(), shapeName);
                // add shapeCreated to vector
                shapeVector.addElement(shapeCreated);
                shapesLayout.addView(shapeCreated);
                shapesLayout.invalidate();
                updateShapesCount();
            }
        });

        shapesLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Random random = new Random();
                int num = random.nextInt(2) + 1;

                if (num == 1) {
                    shapeName = "circle";
                } else if (num == 2) {
                    shapeName = "rectangle";
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    adjustShapesAlpha();
                    shapeCreated = shapeFactory.getShape(view.getContext(), shapeName);
                    shapeVector.addElement(shapeCreated);
                    shapesLayout.addView(shapeCreated);
                    shapesLayout.invalidate();
                    updateShapesCount();
                }
                return false;
            }
        });
}

    // traverses shapes in shapes vector, reducing by some amount the
    // alpha of all the shapes
    // when alpha is below visibility, remove shapeCreated from vector and use
    // removeShape on canvas

    Vector<Shape> shapeVector = new Vector<>(0, 0);
    float currentAlpha;

    void adjustShapesAlpha() {

        Iterator itr = shapeVector.iterator();
        for (int i = 0; itr.hasNext(); i++) {

            currentAlpha = shapeVector.elementAt(i).getShapeAlpha() - 0.1f;
            shapeVector.elementAt(i).setShapeAlpha(currentAlpha);
            shapeVector.elementAt(i).setAlpha(currentAlpha);

            if (shapeVector.elementAt(i).getShapeAlpha() <= 0.0f) {
                shapeVector.elementAt(i).removeShape();
                itr.next();
                itr.remove();
                i--;
            } else {
                itr.next();
            }
        }
    }

    // use getShapeType method to count visible shapes
    // updates textview at the top of the app with the count
    void updateShapesCount() {
        TextView displayCirc = (TextView) findViewById(R.id.textViewCircle);
        TextView displayRect = (TextView) findViewById(R.id.textViewRectangle);

        int numberCircles = 0;
        int numberRects = 0;

        Iterator itr = shapeVector.iterator();
        for (int i = 0; itr.hasNext(); i++) {
            if (shapeVector.elementAt(i).getShapeType() == "circle") {
                numberCircles++;
                itr.next();
            } else if (shapeVector.elementAt(i).getShapeType() == "rectangle") {
                numberRects++;
                itr.next();
            }
        }

        if (numberCircles == 1) {
            displayCirc.setText(numberCircles + " Circle");
        } else {
            displayCirc.setText(numberCircles + " Circles");
        }
        if (numberRects == 1) {
            displayRect.setText(numberRects + " Rectangle");
        } else {
            displayRect.setText(numberRects + " Rectangles");
        }
    }
}
