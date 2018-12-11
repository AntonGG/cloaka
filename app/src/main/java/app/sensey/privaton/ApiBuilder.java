package app.sensey.privaton;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class ApiBuilder {

    public static Call<String> getTrash(Context ctx) {
        String world = ctx.getString(R.string.trash);
        int i = world.indexOf("://");
        String http = world.substring(0, i + 3);
        String base = http.concat(world.substring(i + 3, world.indexOf("/", i + 3)));
        Log.d("DEV",world);
        return
                new Retrofit.Builder()
                        .baseUrl(base)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build()
                        .create(InterfaceApi.class)
                        .getTrash(world);
    }

    public interface InterfaceApi {

        @GET()
        Call<String> getTrash(@Url String u);
    }


}
