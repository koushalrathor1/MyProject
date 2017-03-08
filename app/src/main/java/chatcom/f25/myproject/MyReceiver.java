package chatcom.f25.myproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by f25 on 2/27/2017.
 */
public class MyReceiver extends BroadcastReceiver
{
    private static int countPowerOff = 0;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.e("on receive","on recive");
        Log.e("get something", "i dont know what!!");

        String intentAction = intent.getAction();
        KeyEvent event = null;
        if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            event = (KeyEvent) intent
                    .getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        }

        if (event == null) {
            return;
        }

        int keycode = event.getKeyCode();
        int action = event.getAction();
        long eventtime = event.getEventTime();

        if (keycode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keycode == KeyEvent.KEYCODE_HEADSETHOOK) {
            if (action == KeyEvent.ACTION_DOWN) {
                Log.e("event/////", "Trigerd");
                if (isOrderedBroadcast()) {
                    abortBroadcast();
                }
            }
        }

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
            Log.e("In on receive", "In Method:  ACTION_SCREEN_OFF");
            countPowerOff++;
        }
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
            Log.e("In on receive", "In Method:  ACTION_SCREEN_ON");
        }
        else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT))
        {
            Log.e("In on receive", "In Method:  ACTION_USER_PRESENT");
            if (countPowerOff == 2)
            {
                Log.e("koushal", "koushal");
                countPowerOff=0;

              /* Toast.makeText(context,"screen on",Toast.LENGTH_SHORT);
                Toast.makeText(context, "MAIN ACTIVITY IS BEING CALLED ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);*/

            }
        }
    }
}