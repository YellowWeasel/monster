package com.erayic.agr.common.net.back;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：
 */

public class CommonByteArrayBean {

    private ByteArrayInfo icon;


    public static class ByteArrayInfo{
        private byte[] bytes;

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }
    }

    public ByteArrayInfo getByteArrayInfo() {
        return icon;
    }

    public void setByteArrayInfo(ByteArrayInfo byteArrayInfo) {
        this.icon = byteArrayInfo;
    }
}
