package com.example.alex.opengl;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Alex on 2017/4/17.
 * <pre>
 *     author  ： Alex
 *     e-mail  ： 18238818283@sina.cn
 *     time    ： 2017/04/17
 *     desc    ：
 *     version ： 1.0
 */
public class MyRenderer implements GLSurfaceView.Renderer {

    float[] triangleData = new float[]{
            0.1f,0.6f,0.0f,
            -0.3f,0.0f,0.0f,
            0.3f,0.1f,0.0f
    };

    int[] triangleColor = new int[]{
            65535,0,0,0,
            0,65535,0,0,
            0,0,65535,0
    };

    float[] rectData = new float[]{
           0.4f,0.4f,0.0f,
            0.4f,-0.4f,0.0f,
            -0.4f,0.4f,0.0f,
            -0.4f,-0.4f,0.0f
    };

    int[] rectColor = new int[]{
           0,65535,0,0,
            0,0,65535,0,
            65535,0,0,0,
            65535,65535,0,0,
    };

    float[] rectData2 = new float[]{
            -0.4f,0.4f,0.0f,
            0.4f,0.4f,0.0f,
            0.4f,-0.4f,0.0f,
            -0.4f,-0.4f,0.0f,
    };

    float[] pentacle = new float[]{
            0.4f,0.4f,0.0f,
            -0.2f,0.3f,0.0f,
            0.5f,0.0f,0f,
            -0.4f,0.0f,0f,
            -0.1f,-0.3f,0f,
    };

    FloatBuffer triangleDataBuffer;
    IntBuffer triangleColorBuffer;
    FloatBuffer rectDataBuffer;
    IntBuffer rectColorBuffer;
    FloatBuffer rectDataBuffer2;
    FloatBuffer pentacleBuffer;
    private float rotate;

    public MyRenderer() {
       triangleDataBuffer = floatBufferUtil(triangleData);
        rectDataBuffer = floatBufferUtil(rectData);
        rectDataBuffer2 = floatBufferUtil(rectData2);
        pentacleBuffer = floatBufferUtil(pentacle);
        triangleColorBuffer = intBufferUtil(triangleColor);
        rectColorBuffer = intBufferUtil(rectColor);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //关闭抖动
        gl.glDisable(GL10.GL_DITHER);
        //设置系统透视修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        //清除背景
        gl.glClearColor(0,0,0,0);
        //设置阴影平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        //启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //深度测试类型
        gl.glDepthFunc(GL10.GL_LEQUAL);



    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置窗口大小
        gl.glViewport(0,0,width,height);
        //d设置当前矩阵设为投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //初始化单位矩阵
        gl.glLoadIdentity();
        //计算宽度比
        float ratio =  (float) width/height;
        //设置透视窗空间大小
        gl.glFrustumf(-ratio,ratio,-1,1,1,10);


    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //-------------------------绘制图形-------------------------
        //重置当前的模型试图矩阵
        gl.glLoadIdentity();
        //控制图形中心位置
        gl.glTranslatef(-0.32f,0.35f,-1.2f);
        //设置定点数据
        gl.glVertexPointer(3, GL10.GL_FLOAT,0,triangleDataBuffer);
        //设置颜色数据
        gl.glColorPointer(4, GL10.GL_FIXED,0,triangleColorBuffer);
        //绘制图形
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);

        gl.glLoadIdentity();
        gl.glTranslatef(0.6f,0.8f,-1.5f);
        gl.glRotatef(rotate,0f,0f,0.1f);
        gl.glVertexPointer(3, GL10.GL_FLOAT,0,rectDataBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED,0,rectColorBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);

        gl.glLoadIdentity();
        gl.glTranslatef(-0.4f,-0.5f,-1.5f);
        gl.glRotatef(rotate,0f,0.2f,0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT,0,rectDataBuffer2);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);

        gl.glLoadIdentity();
        gl.glTranslatef(0.4f,-0.5f,-1.5f);
        gl.glVertexPointer(3, GL10.GL_FLOAT,0,pentacleBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,5);

        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        rotate +=1;

    }

    private FloatBuffer floatBufferUtil(float[] floats){
        FloatBuffer mBuffer;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(floats.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mBuffer = byteBuffer.asFloatBuffer();
        mBuffer.put(floats);
        mBuffer.position(0);
        return  mBuffer;
    }

    private IntBuffer intBufferUtil(int[] ints){
        IntBuffer mBuffer;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(ints.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        mBuffer = byteBuffer.asIntBuffer();
        mBuffer.put(ints);
        mBuffer.position(0);
        return  mBuffer;
    }

}
