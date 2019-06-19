package com.example.fyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable chairRenderable;

    ImageView chair;

    View arrayView[];
    ViewRenderable name_animal;

    int selected =1; // default chair is selected.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        //view

        chair =(ImageView)findViewById(R.id.chair);

        setArrayView();
        setClickListner();

        setupModel();


        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                //User tap plane

                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    createModel(anchorNode,selected);

            }
        });

    }

    private void setupModel() {

        ModelRenderable.builder()
                .setSource(this,R.raw.chair)
                .build().thenAccept(renderable -> chairRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

    }

    private void createModel(AnchorNode anchorNode, int selected) {

        if(selected==1)
        {
            TransformableNode chair = new TransformableNode(arFragment.getTransformationSystem());
            chair.setParent(anchorNode);
            chair.setRenderable(chairRenderable);
            chair.select();
        }
    }

    private void setClickListner() {
        for(int i=0;i<arrayView.length;i++)
            arrayView[i].setOnClickListener(this);

    }

    private void setArrayView(){

        arrayView = new View[]{
          chair
        };
    }

    @Override
    public void onClick(View v) {

    }
}
