package com.example.fyp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable moonchairRenderable,sofaRenderable,cupboardRenderable,blackbedRenderable,bedRenderable,deskRenderable;

    ImageView sofa,cupboard,moonchair,blackbed,bed,desk;

    View arrayView[];
    ViewRenderable furniture_name;

    int selected =3; // default chair is selected.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        //views

        sofa =(ImageView) findViewById(R.id.sofa);
        cupboard =(ImageView) findViewById(R.id.cupboard);
        moonchair =(ImageView) findViewById(R.id.moonchair);
       blackbed =(ImageView) findViewById(R.id.blackbed);
        bed =(ImageView) findViewById(R.id.bed);
        desk =(ImageView) findViewById(R.id.desk);


//         chair = (ImageView)findViewById(R.id.chair);


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
                    .setSource(this,R.raw.cupboard)
                    .build().thenAccept(renderable -> cupboardRenderable = renderable)
                   .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.sofa)
                    .build().thenAccept(renderable -> sofaRenderable = renderable)
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
                    .setSource(this, R.raw.bedchild)
                    .build().thenAccept(renderable -> blackbedRenderable  = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.bed)
                    .build().thenAccept(renderable -> bedRenderable  = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                    );

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ModelRenderable.builder()
                    .setSource(this, R.raw.desk)
                    .build().thenAccept(renderable -> deskRenderable  = renderable)
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
            TransformableNode cupboard = new TransformableNode(arFragment.getTransformationSystem());
            cupboard.setParent(anchorNode);
            cupboard.setRenderable(cupboardRenderable);
//            addName(anchorNode,cupboard,"Cupboard");
            cupboard.select();

        }
        if(selected==2)
        {
            TransformableNode sofa = new TransformableNode(arFragment.getTransformationSystem());
            sofa.setParent(anchorNode);
           sofa.setRenderable(sofaRenderable);
           sofa.getScaleController();
//            addName(anchorNode,sofa,"Sofa");
          sofa.select();
        }
        if(selected==3)
        {
            TransformableNode moonchair = new TransformableNode(arFragment.getTransformationSystem());
            moonchair.setParent(anchorNode);
            moonchair.setRenderable(moonchairRenderable);
            moonchair.getScaleController();
//            addName(anchorNode,moonchair,"MoonChair");
            moonchair.select();
        }
        if(selected==4)
        {
            TransformableNode blackbed = new TransformableNode(arFragment.getTransformationSystem());
            blackbed.setParent(anchorNode);
            blackbed.setRenderable(blackbedRenderable);
            blackbed.getScaleController();
//            addName(anchorNode,blackbed,"Blackbed");
            blackbed.select();
        }

        if(selected==5)
        {
            TransformableNode bed = new TransformableNode(arFragment.getTransformationSystem());
            bed.setParent(anchorNode);
            bed.setRenderable(bedRenderable);
            bed.getScaleController();
//            addName(anchorNode,bed,"Bed");
            bed.select();
        }
        if(selected==6)
        {
            TransformableNode desk = new TransformableNode(arFragment.getTransformationSystem());
            desk.setParent(anchorNode);
            desk.setRenderable(deskRenderable);
            desk.getScaleController();
//            addName(anchorNode,desk,"Desk");
            desk.select();
        }

    }

    private void setClickListner() {
        for(int i=0;i<arrayView.length;i++)
            arrayView[i].setOnClickListener(this);

    }

    private void setArrayView(){

        arrayView = new View[]{
          sofa,cupboard,moonchair,blackbed,bed,desk
        };
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.cupboard ) {
            selected = 1;
//            setBackground(v.getId());

        }
        else if(v.getId()==R.id.sofa){
            selected = 2;
//            setBackground(v.getId());
        }
        else if(v.getId()==R.id.moonchair){

            selected = 3;
        }
        else if(v.getId()==R.id.blackbed){

            selected = 4;
        }
        else if(v.getId()==R.id.bed){

            selected = 5;
        }
        else if(v.getId()==R.id.desk){

            selected = 6;
        }

        }


        //Naming  Furniture's

//    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {
//
//
//
//        TransformableNode nameView= new TransformableNode(arFragment.getTransformationSystem());
//
//        ViewRenderable.builder().setView(this,R.layout.furniture_name)
//                .build()
//                .thenAccept(viewRenderable -> {
//                    nameView.setLocalPosition(new Vector3(0f,model.getLocalPosition().y+0.4f,0));
//                    nameView.setParent(anchorNode);
//                    nameView.getScaleController().setMaxScale(1f);
//                    nameView.getScaleController().setMinScale(0.1f);
//                    nameView.setRenderable(viewRenderable);
//                    nameView.select();
//                    TextView txt_name =(TextView)viewRenderable.getView();
//                    txt_name.setText(name);
//                    txt_name.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            anchorNode.setParent(null);
//                           Intent i = new Intent(MainActivity.this,MainActivity.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            MainActivity.this.startActivity(i);
//
//                        }
//                    });
//
//
//
//                });
//
//
//
//
//    }
//
//
//    //Setting Background of TEXT
//
//    private void setBackground(int id) {
//
//        for(int i=0; i<arrayView.length; i++){
//
//            if(arrayView[i].getId()== id) {
//                arrayView[i].setBackgroundColor(Color.parseColor("#17170459"));
//            }
//            else arrayView[i].setBackgroundColor(Color.TRANSPARENT);
//
//
//        }
//
//
//    }

    }

