package com.example.fyp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class activity_chair extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable chairofficeRenderable,moonchairRenderable;

    ImageView chairoffice,moonchair;

    View arrayView[];

    int selected =1; // default chair is selected.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chair);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment_chair);

        //views


        moonchair =(ImageView) findViewById(R.id.moonchair);
        chairoffice =(ImageView) findViewById(R.id.chairoffice);



        setArrayView(); //ArrayMethod
        setClickListner();

        setupModel();


        //Surface Detection

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


    //Setting up models to the surface
    private void setupModel() {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.chairoffice)
                    .build().thenAccept(renderable -> chairofficeRenderable  = renderable)
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


    }

    //Model creation

    private void createModel(AnchorNode anchorNode, int selected) {




        if(selected==1)
        {
            TransformableNode moonchair = new TransformableNode(arFragment.getTransformationSystem());
            moonchair.setParent(anchorNode);
            moonchair.setRenderable(moonchairRenderable);
            moonchair.getScaleController();
            moonchair.select();
        }


        if(selected==2)
        {
            TransformableNode chairoffice = new TransformableNode(arFragment.getTransformationSystem());
            chairoffice.setParent(anchorNode);
            chairoffice.setRenderable(chairofficeRenderable);
            chairoffice.getScaleController();
            chairoffice.select();
        }

    }

    private void setClickListner() {
        for(int i=0;i<arrayView.length;i++)
            arrayView[i].setOnClickListener(this);

    }

    private void setArrayView(){

        arrayView = new View[]{
                chairoffice,moonchair
        };
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.moonchair ) {
            selected = 1;

        }
        else if(v.getId()==R.id.chairoffice){
            selected = 2;

        }


    }



}

