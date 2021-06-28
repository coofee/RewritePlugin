package com.coofee.rewrite.hook.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * copy from didi booster
 */
public class ShadowSharedPreferences {

    public static boolean useSystem = false;

    public static SharedPreferences getSharedPreferences(final Context context, String name, final int mode) {
        if (TextUtils.isEmpty(name)) {
            name = "null";
        }

        if (useSystem) {
            return context.getSharedPreferences(name, mode);
        }

        Log.e("ShadowSharedPreferences", "getSharedPreferences; context=" + context + ", name=" + name + ", mode=" + mode);
        return BoosterSharedPreferences.getSharedPreferences(context, name);
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        if (useSystem) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        }

        Log.e("ShadowSharedPreferences", "getDefaultSharedPreferences; context=" + context);
        return BoosterSharedPreferences.getSharedPreferences(context, getDefaultSharedPreferencesName(context));
    }

    public static String getDefaultSharedPreferencesName(Context context) {
        Log.e("ShadowSharedPreferences", "getDefaultSharedPreferencesName; context=" + context);
        return context.getPackageName() + "_preferences";
    }

    public static SharedPreferences getPreferences(final Activity activity, final int mode) {
        if (useSystem) {
            return activity.getPreferences(mode);
        }

        Log.e("ShadowSharedPreferences", "getPreferences; activity=" + activity + ", mode=" + mode);
        return getSharedPreferences(activity.getApplicationContext(), activity.getLocalClassName(), mode);
    }

}