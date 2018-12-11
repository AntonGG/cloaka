package app.sensey.privaton;

import android.os.Parcel;
import android.os.Parcelable;

public class GirlActivity implements Parcelable {

    private static String url="https://instagram.com/";

    private String nickname;
    private int image;

    public GirlActivity(String nickname, int image) {
        this.nickname = nickname;
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUrl() {
        return url+nickname;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeInt(this.image);
    }

    protected GirlActivity(Parcel in) {
        this.nickname = in.readString();
        this.image = in.readInt();
    }

    public static final Creator<GirlActivity> CREATOR = new Creator<GirlActivity>() {
        @Override
        public GirlActivity createFromParcel(Parcel source) {
            return new GirlActivity(source);
        }

        @Override
        public GirlActivity[] newArray(int size) {
            return new GirlActivity[size];
        }
    };
}
