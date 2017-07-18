package com.erayic.agr.common.dahua;

import java.io.IOException;

import com.company.PlaySDK.IPlaySDKCallBack.DEMUX_INFO;
import com.company.PlaySDK.IPlaySDKCallBack.fDemuxCBFun;

/**
 * 实时监控回调函数
 *
 * @author HeJian
 */
public class ErayicVideoDataCallBack implements fDemuxCBFun {
	@Override
	public void invoke(int nPort, byte[] pOrgBuffer, int nOrgLen,
					   byte[] pBuffer, int nLen, DEMUX_INFO stInfo, long dwUser) {
		try {
			if (null != ErayicDaHuaBundle.m_Fout) {
				ErayicDaHuaBundle.m_Fout.write(pBuffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}