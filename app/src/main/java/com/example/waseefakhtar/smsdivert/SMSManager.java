package com.example.waseefakhtar.smsdivert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by waseefakhtar on 4/28/16.
 */
public class SMSManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage smsMessage = null;
            try {
                if (Build.VERSION.SDK_INT > 19) {
                    SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                    smsMessage = messages[0];
                } else {
                    Object[] objPdus = (Object[]) bundle.get("pdus");
                    SmsMessage.createFromPdu((byte[]) objPdus[0]);
                }

                String phoneNumber = smsMessage.getDisplayOriginatingAddress();
                String message = smsMessage.getMessageBody();
                Toast.makeText(context, "SenderNum: \n " + phoneNumber + "; message: \n " + message,
                        Toast
                                .LENGTH_LONG).show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            SmsManager smsManager = SmsManager.getDefault();
            String number = "03331575850";
            smsManager.sendTextMessage(number, null, "Test SMS Message", null, null);
    }
}
