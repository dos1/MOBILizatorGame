package com.codeshakerz.hackaton;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.codeshakerz.hackaton.RestClient.RequestMethod;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.location.Criteria;
import android.location.LocationManager;

public class HackatonGameActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));
    }
    /** */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    /** */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mm_settings:
                this.setFullscreen(!this.isFullscreen());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean fullscreen;
    private boolean isFullscreen() {
    	return this.fullscreen;
    }
    private void setFullscreen(boolean on) {
    	Window win = getWindow();
    	WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        if (on) {
            winParams.flags |=  bits;
            this.fullscreen = true;
        } else {
            winParams.flags &= ~bits;
            this.fullscreen = false;
        }
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        win.setAttributes(winParams);
    }
    
    class Panel extends View {
    	DisplayMetrics metrics;   	
    	Radar radar;
    	
    	public Panel(Context context) {
            super(context);
            metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            
         // ==========================================================

            try {
                Socket s = new Socket("http://192.168.23.157",80);
        } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            
            RestClient client = new RestClient("http://192.168.23.157:8000/api/players/");
            client.AddParam("player", "mug3tsu");
            /*client.AddParam("source", "tboda-widgalytics-0.1");
            client.AddParam("Email", _username);
            client.AddParam("Passwd", _password);
            client.AddParam("service", "analytics");
            client.AddHeader("GData-Version", "2");*/

            try {
                client.Execute(RequestMethod.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            String response = client.getResponse();
            //System.out.println(response);
            // ==========================================================
                        
            Player[] players;
            players = new Player[2];
            
            radar = new Radar(metrics.widthPixels, metrics.heightPixels, players);      
        }
     
        @Override
        public void onDraw(Canvas canvas) {
            radar.Draw(canvas);
        }
    }
}