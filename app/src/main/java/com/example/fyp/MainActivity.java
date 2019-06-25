package com.example.fyp;

import android.os.Build;
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
    private ModelRenderable moonchairRenderable,bearRenderable,lionRenderable,blackbedRenderable;

    ImageView bear,lion,moonchair,blackbed;

    View arrayView[];
    ViewRenderable name_animal;

    int selected =3; // default chair is selected.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        //view

         bear =(ImageView)findViewById(R.id.bear);
         lion =(ImageView)findViewById(R.id.lion);
        moonchair =(ImageView)findViewById(R.id.moonchair);
       blackbed =(ImageView)findViewById(R.id.blackbed);


//         chair = (ImageView)findViewById(R.id.chair);


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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this,R.raw.lion)
                    .build().thenAccept(renderable -> lionRenderable = renderable)
                   .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.bear)
                    .build().thenAccept(renderable -> bearRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.moonchair)
                    .build().thenAccept(renderable -> moonchairRenderable  = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.blackbed)
                    .build().thenAccept(renderable -> blackbedRenderable  = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );
        }
    }

    private void createModel(AnchorNode anchorNode, int selected) {

        if(selected==1)
        {
            TransformableNode lion = new TransformableNode(arFragment.getTransformationSystem());
            lion.setParent(anchorNode);
            lion.setRenderable(lionRenderable);
            lion.select();
        }
        if(selected==2)
        {
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(bearRenderable);
            bear.select();
        }
        if(selected==3)
        {
            TransformableNode moonchair = new TransformableNode(arFragment.getTransformationSystem());
            moonchair.setParent(anchorNode);
            moonchair.setRenderable(moonchairRenderable);
            moonchair.select();
        }
        if(selected==4)
        {
            TransformableNode blackbed = new TransformableNode(arFragment.getTransformationSystem());
            blackbed.setParent(anchorNode);
            blackbed.setRenderable(blackbedRenderable);
            blackbed.select();
        }

    }

    private void setClickListner() {
        for(int i=0;i<arrayView.length;i++)
            arrayView[i].setOnClickListener(this);

    }

    private void setArrayView(){

        arrayView = new View[]{
          bear,lion,moonchair,blackbed
        };
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.lion ) {
            selected = 1;
        }
        if(v.getId()==R.id.bear){
            selected = 2;
        }
        if(v.getId()==R.id.moonchair){
            selected = 3;
        }
        if(v.getId()==R.id.blackbed){
            selected = 4;
        }

        }

    }

