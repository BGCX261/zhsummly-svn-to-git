package core;
import java.util.List;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;

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

public class GetHtml {
	
	public String getHtml(String url) throws Exception {       
	
		String html="";
		
		HttpHost proxy = new HttpHost("proxy1.bj.petrochina", 8080);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpclient = HttpClients.custom()
		.setRoutePlanner(routePlanner)
		.build();
		// ´´½¨HttpClientÊµÀý      
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		

        HttpGet httpget = new HttpGet(url);

        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    // do something useful
                	html = convertStreamToString(instream);   
                    //System.out.println(url);    
                    //System.out.println(html);   

                } finally {
                    instream.close();
                }
            }
        } finally {
            response.close();
        }
		
		
		return html;
	}
	
	
	public String convertStreamToString(InputStream is) throws UnsupportedEncodingException {       
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"GBK"));       
        StringBuilder sb = new StringBuilder();       
        
        String line = null;       
        try {       
            while ((line = reader.readLine()) != null) {   
                sb.append(line + "\n");       
            }       
        } catch (IOException e) {       
            e.printStackTrace();       
        } finally {       
            try {       
                is.close();       
            } catch (IOException e) {       
               e.printStackTrace();       
            }       
        }       
        return sb.toString();       
    }   

}
