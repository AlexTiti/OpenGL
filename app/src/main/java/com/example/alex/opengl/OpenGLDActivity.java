package com.example.alex.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGLDActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        MyDRenderer renderer = new MyDRenderer();
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);


    }
}
