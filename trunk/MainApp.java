package core;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;


import java.io.DataInputStream;   
import java.io.DataOutputStream;   



import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


public class MainApp {
	
	static ServerSocket aServerSocket = null; // Server Socet.   
    DataInputStream aDataInput = null; // Server input Stream that to   
    // receive msg from client.   
    DataOutputStream aDataOutput = null; // Server output Stream that to   
    static ArrayList<DataOutputStream> list = new ArrayList();   
    analysis.Sohu sh=new analysis.Sohu();
    


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("À— È–¬Œ≈");
		analysis.Sohu sh2=new analysis.Sohu();
		//String result=sh2.getNews();
		System.out.println(sh2.getNews());
		
	
		//System.out.println(sh.getNews());
		
		
//		try {   
//            aServerSocket = new ServerSocket(50003); // listen 50003 port.   
//            System.out.println("already listen 50003 port.");   
//        } catch (Exception e) {   
//            e.printStackTrace();   
//        }   
//       // int num = 0;   
//        while (true) {   
//            Socket aSessionSoket = null;   
//            try {   
//                aSessionSoket = aServerSocket.accept();   
//                MyThread thread = new MainApp().new MyThread(aSessionSoket);   
//                thread.start();   
//                //num = list.size();   
//            } catch (IOException e1) {   
//                // TODO Auto-generated catch block   
//                e1.printStackTrace();   
//            }   
//        }   

		
	}
	
	
	
	class MyThread extends Thread {   
        Socket aSessionSoket = null;   
  
        public MyThread(Socket socket) {   
            aSessionSoket = socket;   
        }   
  
        public void run() {   
            try {   
                aDataInput = new DataInputStream(aSessionSoket.getInputStream());   
                aDataOutput = new DataOutputStream(aSessionSoket   
                        .getOutputStream());   
               // list.add(aDataOutput);   
                while (true) {   
                    String msg = aDataInput.readUTF(); // read msg.   
//                    if (!msg.equals("connect...")) {   
//                        System.out.println("ip: "  
//                                + aSessionSoket.getInetAddress());// ip.   
//                        System.out.println("receive msg: " + msg);   
//                        for (int i = 0; i < list.size(); i++) {   
//                            DataOutputStream output = (DataOutputStream) list   
//                                    .get(i);   
//                            output.writeUTF(msg + "----" + list.size());   
//                        }   
//                        if (msg.equals("end"))   
//                            break;   
//                    } 
                    if (msg.equals("end"))   
                        break; 
                    else
                    {
                    	 System.out.println("ip: "  
                                 + aSessionSoket.getInetAddress());// ip.
                         aDataOutput.writeUTF(sh.getNews()); 
                    }
                   
                }   
  
            } catch (IOException e) {   
                // TODO Auto-generated catch block   
                e.printStackTrace();   
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {   
                try {   
                    aDataInput.close();   
                    if (aDataOutput != null)   
                        aDataOutput.close();   
                    //list.remove(aDataOutput);   
                    aSessionSoket.close();   
                    System.out.println("Closed°£°£°£°£°£");
  
                } catch (Exception e2) {   
                    e2.printStackTrace();   
                }   
  
            }   
  
        }   
    }   
	


}
