package com.infinity.testcontrolsystem;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TestPage extends ActionBarActivity {

    ServerSocket serverSocket;
    TextView textConnect;
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page);

        textConnect = (TextView) findViewById(R.id.textConnect);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);

        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();
    }

    private  class SocketServerThread extends  Thread{

        static final int SocketServerPORT = 8080;
        int count =0;

        @Override
        public void run(){
            try{
                serverSocket = new ServerSocket(SocketServerPORT);

                while(true){
                    Socket socket = serverSocket.accept();
                    count++;

                    TestPage.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textConnect.setText("NORM");
                        }
                    });
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
