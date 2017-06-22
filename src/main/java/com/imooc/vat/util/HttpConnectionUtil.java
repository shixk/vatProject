package com.imooc.vat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

public class HttpConnectionUtil {
	private static Logger log = Logger.getLogger(HttpConnectionUtil.class);

    public static String DEFAULT_CHARSET = "UTF-8";

    /**
     * 
     * @Title: sendHttpContent
     * @Description: TODO
    
     * @param method
     * @param url
     * @param params
     * @return String[]
     * @throws
     */
    public static String[] sendHttpContent_http(String method, String url,
            String params) {

        String[] sa = new String[] { "500", "" };

        HttpURLConnection connection = null;
        String content = "";
        int response_code = 500;
        try {
            URL urlAddress = new URL(url);
            connection = (HttpURLConnection) urlAddress.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            //
            if ("POST".equalsIgnoreCase(method)) {
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Charsert", DEFAULT_CHARSET);
                connection.setRequestProperty("Proxy-Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                //
                connection.connect();
                DataOutputStream out = new DataOutputStream(
                        connection.getOutputStream());
                out.writeBytes(params);
                out.flush();
                out.close(); // flush and close
            }

            // 得到访问页面的返回值
            response_code = connection.getResponseCode();
            sa[0] = String.valueOf(response_code);

            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                // InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content += line;
                }
                sa[1] = content;
            }
            else {
                sa[0] = "500";
                sa[1] = "500";
            }
            log.info(sa[0] + "------" + sa[1]);
        }
        catch (MalformedURLException e) {
            log.error(e.getMessage(),e);
        }
        catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return sa;
    }
    public static String[] sendHttpContent(String method, String url,
    		String params) {
    	log.info("调用地址为："+url);
    	if(url!=null && url.startsWith("https:")){
    		return sendHttpsContent_https(method, url, params);
    	}else{
    		return sendHttpContent_http(method, url, params);
    	}
    	
    }
    public static String sendContent(String method, String url,
    		String params) {
    	 return sendHttpContent(method, url, params)[1];
    	
    }
    

    static class MyX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
		final String TAG = "trustAllHosts";
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    public static String[] sendHttpsContent_https(String method, String url,
            String params) {

        String[] sa = new String[] { "500", "" };

        HttpsURLConnection connection = null;
        String content = "";
        int response_code = 500;
        try {

//            TrustManager[] tm = { new MyX509TrustManager() };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL urlAddress = new URL(url);
            trustAllHosts();
            connection = (HttpsURLConnection) urlAddress.openConnection();
            connection.setHostnameVerifier(DO_NOT_VERIFY);
            // connection.setSSLSocketFactory(ssf);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            // 设置请求方式（GET/POST）
            connection.setRequestMethod(method);

            if ("POST".equalsIgnoreCase(method)) {
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Charsert", DEFAULT_CHARSET);
                connection.setRequestProperty("Proxy-Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                //
                connection.connect();
                DataOutputStream out = new DataOutputStream(
                        connection.getOutputStream());
                out.writeBytes(params);
                out.flush();
                out.close(); // flush and close
            }

            // 得到访问页面的返回值
            response_code = connection.getResponseCode();
            sa[0] = String.valueOf(response_code);

            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                // InputStreamReader reader = new InputStreamReader(in,charSet);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in, "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    content += line;
                }
                sa[1] = content;
            }
            else {
                sa[0] = "500";
                sa[1] = "500";
            }
            log.info(sa[0] + "------" + sa[1]);
        }
        catch (MalformedURLException e) {
            log.error(e.getMessage(),e);
            
        }
        catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return sa;
    }
    
    public static String getHttpContent(String url) {

		HttpURLConnection connection = null;
		try {
			URL urlAddress = new URL(url);
			
			connection = (HttpURLConnection) urlAddress.openConnection();
			Integer response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK  ) {
				InputStream in = connection.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "UTF-8"));
				String content = "";
				String line = null;
				while ((line = reader.readLine()) != null) {
					content += line;
				}
				return content;
			} else {
				return response_code.toString();
			}
		} catch (IOException e) {
			// e.printStackTrace();
			return "IOException";
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
   
}
