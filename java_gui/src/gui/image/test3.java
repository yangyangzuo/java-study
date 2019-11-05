package gui.image;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class test3 {
    public static void main(String[] args) throws IOException {
	
	//url必须使用协议，本地文件不可使用
	URL url=new URL("http://localhost:8080/java_gui/yaya.jpg");
	URLConnection conn=url.openConnection();
	conn.setConnectTimeout(1000);
	InputStream in=conn.getInputStream();
	BufferedInputStream bin=new BufferedInputStream(in);
	DataInputStream din=new DataInputStream(bin);
	
	byte[] buffer = new byte[1024];
	int length = -1;
	
	FileOutputStream fos = new FileOutputStream("c:/yaya.jpg");
	while((length = din.read(buffer)) != -1){
	    	fos.write(buffer,0,length);
	}
	
	

    }
}
