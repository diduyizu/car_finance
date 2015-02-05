package com.carfinance.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 */
public class HttpClient {
	public static String post(String http, String content, String encode) {
		try {
			URL url = new URL(http);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(20000);
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			//con.setRequestProperty("Content-Type", "text/xml");
			OutputStream out = con.getOutputStream();
			out.write(content.getBytes(encode));
			out.flush();
			InputStream in = con.getInputStream();
			byte[] buf = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (true) {
				int readsize = in.read(buf);
				if (readsize == -1)break;
				baos.write(buf, 0, readsize);
			}
			in.close();
			return new String(baos.toByteArray(), encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String syncpost(String http, String content, String encode) {
		try {
			URL url = new URL(http);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(60 * 1000 * 2);
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			OutputStream out = con.getOutputStream();
			out.write(content.getBytes(encode));
			out.flush();
			InputStream in = con.getInputStream();
			byte[] buf = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (true) {
				int readsize = in.read(buf);
				if (readsize == -1)break;
				baos.write(buf, 0, readsize);
			}
			in.close();
			return new String(baos.toByteArray(), encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

    public static String get(String http, String content, String encode) {
        try {
            URL url = new URL(http);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(10000);
            con.setReadTimeout(20000);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            //con.setRequestProperty("Content-Type", "text/xml");
            OutputStream out = con.getOutputStream();
            out.write(content.getBytes(encode));
            out.flush();
            InputStream in = con.getInputStream();
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (true) {
                int readsize = in.read(buf);
                if (readsize == -1)break;
                baos.write(buf, 0, readsize);
            }
            in.close();
            return new String(baos.toByteArray(), encode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
