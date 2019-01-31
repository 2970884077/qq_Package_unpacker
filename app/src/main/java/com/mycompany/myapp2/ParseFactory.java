package com.mycompany.myapp2;

public class ParseFactory
{
	private byte[] body_encrypted;
	private byte[] key;
	private byte[] body_decrypted;
	private String qq;
	private byte[] TGTkey;
	private byte[] sharekey;
	private int login_result;
	private Crypter crypter = new Crypter();
	private byte[] tea_key;
	
	private ByteBuilder builder = new ByteBuilder();
	
	public byte[] parse(byte[] data){
		this.builder = new ByteBuilder();
		this.body_encrypted = this.un_pack(data,false);
		if (this.body_encrypted==null){
			return new byte[]{};
		}
	    this.decrypt_body();
		if (this.body_decrypted==null){
			this.builder.writebytes(this.body_encrypted);
			return builder.getdata();
		}
		this.builder.writebytes(this.body_decrypted);
		return this.builder.getdata();
	}



	
	public void decrypt_body(){
		this.body_decrypted=crypter.decrypt(this.body_encrypted,this.key);

	}
	public byte[] un_pack(byte[] data,boolean is){
		int position = Util.getByteIndexOf(data,this.qq.getBytes(),0,data.length);
		if (position==-1){
			return null;
		}
		System.out.println(position);
		this.builder.writebytes(Util.subByte(data,0,position+this.qq.getBytes().length));
		byte[] data_to_return = Util.subByte(data,position+this.qq.getBytes().length,data.length-(position+this.qq.getBytes().length));
		System.out.println(Util.byte2HexString(data_to_return));
		return data_to_return;
	}
	
	
	public ParseFactory(byte[] key,String q){
		this.qq=q;
	    this.key = key;
	}
	
}
