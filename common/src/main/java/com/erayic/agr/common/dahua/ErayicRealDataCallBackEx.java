package com.erayic.agr.common.dahua;

import com.company.NetSDK.CB_fRealDataCallBackEx;
import com.company.PlaySDK.IPlaySDK;

/**
 * 实时监控回调函数
 *
 * @author HeJian
 */
public class ErayicRealDataCallBackEx implements CB_fRealDataCallBackEx {

	/**
	 * 网络断线回调函数
	 * @param lRealHandle 实时监视ID
	 * @param dwDataType  回调出来的数据类型
	 * @param pBuffer     回调数据，根据数据类型的不同每次回调不同的长度的数据，除类型0，其他数据类型都是按帧，每次回调一帧数据
	 * @param dwBufSize   回调数据参数结构体，根据不同的类型，参数结构也不一致
	 * @param param       回调数据的长度，根据不同的类型，长度也不同(单位字节)
	 */
	@Override
	public void invoke(long lRealHandle, int dwDataType, byte[] pBuffer,
					   int dwBufSize, int param) {
		if (0 == dwDataType) {
			IPlaySDK.PLAYInputData(ErayicDaHuaBundle.nPort, pBuffer, pBuffer.length);
		}
	}
}