
package com.dove.common;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.dove.common.utils.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class Device {
    private static String sDeviceId = null;

    public static synchronized String getDeviceId(Context context) throws IOException {
        if (sDeviceId == null) {
            sDeviceId = getDeviceIdInternal(context);
        }
        return sDeviceId;
    }

    private static String getDeviceIdInternal(Context context) throws IOException {
        if (context == null) {
            throw new IllegalArgumentException("A context is requried to get device id");
        }
        String deviceId;
        final File f = context.getFileStreamPath("deviceName");
        if (f.exists()) {
            if (f.canRead()) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(f), 128);
                    deviceId = reader.readLine();
                    if (!TextUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            }
            // Try to delete invalid device cache file.
            f.delete();
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f), 128);
            final String consistentDeviceId = getConsistentDeviceId(context);
            if (!TextUtils.isEmpty(consistentDeviceId)) {
                deviceId = "androidc" + consistentDeviceId;
            } else {
                deviceId = "android" + System.currentTimeMillis();
            }
            writer.write(deviceId);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return deviceId;
    }

    public static String getConsistentDeviceId(Context context) {
        String deviceId = null;
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            deviceId = tm.getDeviceId();
        }
        if (!TextUtils.isEmpty(deviceId)) {
            return Utility.getSmallHash(deviceId);
        }
        return null;
    }
}
