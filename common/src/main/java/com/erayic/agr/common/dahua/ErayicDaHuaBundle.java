package com.erayic.agr.common.dahua;

import java.io.FileOutputStream;

public class ErayicDaHuaBundle {
	private static boolean bGesture = false;
	static int nPort ;
	static int nCurVolume = -1;
	static long lRealHandle = 0;
	static boolean bRecordFlag = false;
	static FileOutputStream m_Fout;

	public static boolean isbGesture() {
		return bGesture;
	}

	public static void setbGesture(boolean bGesture) {
		ErayicDaHuaBundle.bGesture = bGesture;
	}
	
}
