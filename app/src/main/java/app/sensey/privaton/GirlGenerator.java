package app.sensey.privaton;

import java.util.ArrayList;

public class GirlGenerator {

    public static ArrayList<GirlActivity> getGirls() {
        //тут надо подставлять новых телок и менять фотки в папке
        //я их перемешиваю и иногда новых докидываю
        ArrayList<GirlActivity> items = new ArrayList<>();
        items.add(new GirlActivity("sad_may", R.drawable.img8));
        items.add(new GirlActivity("ari.aks", R.drawable.img1));
        items.add(new GirlActivity("n.kvsper", R.drawable.img4));
        items.add(new GirlActivity("varera_lera", R.drawable.img3));
        items.add(new GirlActivity("nita_kuzmina", R.drawable.img5));
        items.add(new GirlActivity("darinaselivanovaa", R.drawable.img2));
        items.add(new GirlActivity("cloudanastasia", R.drawable.img6));
        items.add(new GirlActivity("bitchin.eli", R.drawable.img7));
        items.add(new GirlActivity("yagudina_k", R.drawable.img9));
        items.add(new GirlActivity("cloudanastasia", R.drawable.img6));
        items.add(new GirlActivity("bitchin.eli", R.drawable.img7));
        items.add(new GirlActivity("yagudina_k", R.drawable.img9));
        items.add(new GirlActivity("sad_may", R.drawable.img8));
        items.add(new GirlActivity("nita_kuzmina", R.drawable.img5));
        items.add(new GirlActivity("darinaselivanovaa", R.drawable.img2));
        items.add(new GirlActivity("ari.aks", R.drawable.img1));
        return items;
    }
}
