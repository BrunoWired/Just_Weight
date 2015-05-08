package org.kidinov.justweight.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.kidinov.justweight.R;

/**
 * Created by akid on 17/04/15.
 */
public class EnvUtil {

    private static final String TAG = "EnvUtil";

    public static String formatWeight(int value) {
        DecimalFormat df = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return String.valueOf(df.format((float) value / 10));
    }

    public static Date getLocalFromString(String local) {
        try {
            return DateFormat.getDateInstance(DateFormat.DEFAULT).parse(local);
        } catch (ParseException e) {
            Log.e(TAG, "", e);
            return new Date();
        }
    }

    public static String formatWeightOnlyDec(int value) {
        return String.valueOf("0." + value % 10);
    }

    public static String getFormattedDate(long time) {
        return DateFormat.getDateInstance(DateFormat.DEFAULT).format(time);
    }

    public static String getFormattedDateForGraph(long date) {
        return new SimpleDateFormat("dd").format(new Date(date));
    }

    public static String getCurrentUnit(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx).getString("units", "kg");
    }

    public static String getLocalUnitString(Context ctx) {
        if (getCurrentUnit(ctx).equals("kg")) {
            return ctx.getString(R.string.kg);
        }
        return ctx.getString(R.string.lbs);
    }

    public static Date getDateMinusDays(int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public static int convertToKg(Context ctx, int value) {
        if (getCurrentUnit(ctx).equals("lbs")) {
            return (int) (value / 2.441933);
        }
        return value;
    }

    public static int convertFromKg(Context ctx, int value) {
        if (getCurrentUnit(ctx).equals("lbs")) {
            return (int) (value * 2.441933);
        }
        return value;
    }


    public static String getPubKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqIz/gdMyiTUrg8gvjReZQ9Uzz0mxmSTcSsQRwStiS0K6nXlJvg9ca+/89");
        sb.append("zuYrrixA0Ah3X1ta9/jpIVlAqZMRk57wkv9WAZNNrWkoiczkgOIbXAwdv2sgulPsGflPo1srCsDSiqCA4cKTN9/uAzSRQ3TvUX499pUGs+yAgcAOrK/4CgU6K91F");
        sb.append("aSGdo3fgaTgh/ZhZwj4FEr9NG7j92xM9L/nglCyCmNVCi/ppFvfhUUz7JlDQ0r2MW3bqXU/pGUexJqepYYvZ");
        sb.append("7q3z7IQFCG3RF23yOIVHuG29c6GbL0fzWqoomBOYLJ97VMSxrHVV/63aUZSIcN9UKPxrrTWV4qiiQIDAQAB");
        return sb.toString();
    }
}
