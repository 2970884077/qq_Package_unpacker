package com.mycompany.myapp2;
import java.io.*;
import java.nio.*;

public class Util
{
	public static byte[] ToByte(long x) {  
		ByteBuffer buffer = ByteBuffer.allocate(8);

		buffer.putLong(x);
		return  buffer.array();  
	}
	public static byte[] ToByte(int x) {  
        ByteBuffer buffer = ByteBuffer.allocate(8);

		buffer.putInt(x);
		return  buffer.array();  
    }
	public static byte[] ToByte(short x) {  
        ByteBuffer buffer = ByteBuffer.allocate(8);

		buffer.putShort(x);
		return  buffer.array();  
    }
	
	
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    }
	
	public static String byte2HexString(byte[] bytes) {
        String hex= "";
        if (bytes != null) {
            for (Byte b : bytes) {
                hex += String.format("%02X", b.intValue() & 0xFF)+" ";
            }
        }
        return hex;

    }
	
	public static byte[] str_to_byte(String str) {
        String replaceAll = str.replaceAll(" ", "");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(replaceAll.length() >> 1);
        for (int i = 0; i <= replaceAll.length() - 2; i += 2) {
            byteArrayOutputStream.write(Integer.parseInt(replaceAll.substring(i, i + 2), 16) & 255);
        }
        return byteArrayOutputStream.toByteArray();
    }
	public static int GetInt(byte[] data, int offset, int length)
	{
        byte[] test = new byte[]{0x00,0x00,data[offset],data[offset+1],0x00,0x00,0x00,0x00};
		ByteBuffer u = ByteBuffer.wrap(test);

		return u.getInt();
	}

	public static int GetInt(byte[] data)
	{
        byte[] test = new byte[]{0x00,0x00,data[0],data[1],0x00,0x00,0x00,0x00};

		ByteBuffer u = ByteBuffer.wrap(test);

		return u.getInt();
	}

	public static long GetLong(byte[] data)
	{
        byte[] test = new byte[]{0x00,0x00,0x00,0x00,data[0],data[1],data[2],data[3]};

		ByteBuffer u = ByteBuffer.wrap(test);

		return u.getLong();
	}

	public static short GetShort(byte[] data)
	{
        byte[] test = new byte[]{data[0],data[1],0x00,0x00,0x00,0x00,0x00,0x00};

		ByteBuffer u = ByteBuffer.wrap(test);

		return u.getShort();
	}
	
	
	public static int getByteIndexOf(byte[] sources, byte[] src, int startIndex, int endIndex) {

        if (sources == null || src == null || sources.length == 0 || src.length == 0) {
            return -1;
        }

        if (endIndex > sources.length) {
            endIndex = sources.length;
        }

        int i, j;
        for (i = startIndex; i < endIndex; i++) {
            if (sources[i] == src[0] && i + src.length < endIndex) {
                for (j = 1; j < src.length; j++) {
                    if (sources[i + j] != src[j]) {
                        break;
                    }
                }

                if (j == src.length) {
                    return i;
                }
            }
        }
        return -1;
    }
	
	public static byte[] subByte(byte[] b,int off,int length){

		if (b.length != 0 && b != null){
			byte[] b1 = new byte[length];
			System.arraycopy(b, off, b1, 0, length);
			return b1;
		}
		return new byte[1];


	}
	
}
