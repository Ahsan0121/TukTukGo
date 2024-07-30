package com.simcoder.tuktukgo.Utils;

import android.app.Activity;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;
import com.simcoder.tuktukgo.Objects.TypeObject;
import com.simcoder.tuktukgo.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Utils {
    /**
     * Round a float value to a specific decimal place
     * @param amount - the value to round
     * @param decimalPlace - to what decimal place to round the amount to
     * @return rounded number
     */
    public BigDecimal round(float amount, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(amount));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd;
    }


    /**
     * Returns array list with all of the driver rides available for this
     * application.
     * @param activity - activity that called this function
     * @return typeArrayList - array list with all the driver types
     */
    public static ArrayList<TypeObject> getTypeList(Activity activity){
        ArrayList<TypeObject> typeArrayList = new ArrayList<>();


        typeArrayList.add(new TypeObject("type_1", activity.getString(R.string.type_1), ContextCompat.getDrawable(activity, R.drawable.ic_type_1), 4));
        typeArrayList.add(new TypeObject("type_2", activity.getString(R.string.type_2), ContextCompat.getDrawable(activity, R.drawable.ic_type_2), 7));
        typeArrayList.add(new TypeObject("type_3", activity.getString(R.string.type_3), ContextCompat.getDrawable(activity, R.drawable.ic_type_3), 4));
        typeArrayList.add(new TypeObject("type_4", activity.getString(R.string.type_4), ContextCompat.getDrawable(activity, R.drawable.ic_type_4), 1));
        return  typeArrayList;
    }


    /**
     * Calculate Ride cost estimate
     */
    public static int rideCostEstimate(double distance, double duration){
        double price;
        price = 36 + distance * 26 + duration * 0.001;
        return (int) price;
    }

    /**
     * get type object that is in the arrayList with a certain id
     * @param activity - activity that called this function
     * @param id - id of the object to find
     * @return - type object
     */
    public static TypeObject getTypeById(Activity activity, String id){
        ArrayList<TypeObject> typeArrayList = getTypeList(activity);
        for(TypeObject mType : typeArrayList){
            if(mType.getId().equals(id)){
                return mType;
            }
        }
        return null;
    }
}
