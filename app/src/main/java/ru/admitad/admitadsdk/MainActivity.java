package ru.admitad.admitadsdk;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements TrackerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdmitadTracker.setLogEnabled(true);
        AdmitadTracker.initialize(getApplicationContext(), "TestAndroidPostback", new TrackerInitializationCallback() {
            @Override
            public void onInitializationSuccess() {
            }

            @Override
            public void onInitializationFailed(Exception exception) {

            }
        });
        AdmitadTracker.getInstance().addListener(this);

        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        if (intent.getData() != null) {
            AdmitadTracker.getInstance().handleDeeplink(intent.getData());
        }
    }

    @Override
    public void onSuccess(AdmitadEvent result) {
        logConsole("Event send successfully + " + result);
    }

    @Override
    public void onFailure(int errorCode, @Nullable String errorText) {
        logConsole("Event failed to send, code = " + errorCode + ",msg: " + errorText);
    }

    public void registrationClick(View v) {
        AdmitadTracker.getInstance().logRegistration("TestRegistrationUid");
    }

    public void orderClick(View v) {
        final AdmitadOrder order = new AdmitadOrder.Builder("123", "100.00")
                .setCurrencyCode("RUB")
                .putItem(new AdmitadOrder.Item("Item1", "ItemName1", 3))
                .putItem(new AdmitadOrder.Item("Item2", "ItemName2", 5))
                .setUserInfo(new AdmitadOrder.UserInfo().putExtra("Surname", "Kek").putExtra("Age", "10"))
                .build();
        AdmitadTracker.getInstance().logOrder(order, new TrackerListener() {
            @Override
            public void onSuccess(AdmitadEvent result) {
                logConsole("Order event send successfully + " + result.toString());
            }

            @Override
            public void onFailure(int errorCode, @Nullable String errorText) {
                logConsole("Order event failed to send, code = " + errorCode + ",msg: " + errorText);
            }
        });
    }

    public void purchaseClick(View v) {
        final AdmitadOrder order = new AdmitadOrder.Builder("321", "1756.00")
                .setCurrencyCode("USD")
                .putItem(new AdmitadOrder.Item("Item1", "ItemName1", 7))
                .putItem(new AdmitadOrder.Item("Item2", "ItemName2", 8))
                .setUserInfo(new AdmitadOrder.UserInfo().putExtra("Name", "Keksel").putExtra("Age", "1430"))
                .build();
        AdmitadTracker.getInstance().logPurchase(order);
    }

    public void returnClick(View v) {
        AdmitadTracker.getInstance().logUserReturn("TestReturnUserUid", 5);
    }

    public void loyaltyClick(View v) {
        AdmitadTracker.getInstance().logUserLoyalty("TestUserLoyaltyUid", 10);
    }

    public void manyEventsQueue(View v) {
        for (int i = 0; i < 100; i++) {
            AdmitadTracker.getInstance().logRegistration("userRegistration" + i);
            AdmitadTracker.getInstance().logUserLoyalty("userLoyalty" + i, i);
        }
    }

    public void setupNewAdmitadUid(View v) {
        AdmitadTracker.getInstance().handleDeeplink(Uri.parse("schema://host?uid=" + UUID.randomUUID()));
    }

    private void logConsole(String message) {
        Log.d("MainActivity", message);
    }
}
