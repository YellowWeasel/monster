package com.erayic.agr.common.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：手机唯一识别码
 */

public class ErayicIdentifier {

    private static ErayicIdentifier instance = null;
    private Context mContext;

    //私有构造函数，避免外界可以通过new 获取对象
    private ErayicIdentifier(Context context) {
        this.mContext = context;
    }

    public static ErayicIdentifier getInstance(Context context) {
        if (instance == null) {
            instance = new ErayicIdentifier(context.getApplicationContext());
        }
        return instance;
    }

    public String getErayicdentifier() {
        if (mContext == null) {
            try {
                throw new Exception("Context null");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        String m_szImei = getIMEIId(mContext);
        String m_szWLANMAC = getMacId(mContext);
        String m_szBTMAC = getBluetoothID(mContext);
        String m_szInstallation = getInstallationId(mContext);

        String m_szLongID = (TextUtils.isEmpty(m_szImei) ? "" : m_szImei)
                + (TextUtils.isEmpty(m_szInstallation) ? "" : m_szInstallation)
                + (TextUtils.isEmpty(m_szWLANMAC) ? "" : m_szWLANMAC)
                + (TextUtils.isEmpty(m_szBTMAC) ? "" : m_szBTMAC);
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());

        // get md5 bytes
        byte p_md5Data[] = m.digest();

        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            if (b <= 0xF) {
                m_szUniqueID += "0";
            }

            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }

        // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();
        return m_szUniqueID;
    }

    /**
     * 设备IMEI
     * android.permission.READ_PHONE_STATE 权限
     */
    private synchronized static String getIMEIId(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        String ID = TelephonyMgr.getDeviceId();
        return ID;
    }

    /**
     * WLAN MAC Address 网卡地址
     * android.permission.ACCESS_WIFI_STATE 权限
     */
    private synchronized static String getMacId(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String WLANMAC = wm.getConnectionInfo().getMacAddress();
        return WLANMAC;
    }

    /**
     * 蓝牙地址
     * android.permission.BLUETOOTH 权限
     */
    private synchronized static String getBluetoothID(Context context) {
        BluetoothAdapter mBluetooth = BluetoothAdapter.getDefaultAdapter();
        String mBluetoothId = mBluetooth.getAddress();
        return mBluetoothId;
    }

    /**
     * 考虑到Android设备的多样性，比如一些平板没有通话功能，或者部分低价设备没有WLAN或者蓝牙，
     * 甚至用户不愿意赋予APP这些需要的权限，我们就使用无需权限的方法;这种方式的原理是在程序安
     * 装后第一次运行时生成一个ID，该方式和设备唯一标识不一样，不同的应用程序会产生不同的ID，
     * 同一个程序重新安装也会不同。所以这不是设备的唯一ID，但是可以保证每个用户的ID是不同的。
     * 可以说是用来标识每一份应用程序的唯一ID（即Installtion ID）
     */
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";

    private synchronized static String getInstallationId(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }


}
