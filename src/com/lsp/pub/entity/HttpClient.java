package com.lsp.pub.entity;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
 
import org.apache.commons.httpclient.util.TimeoutController.TimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.IOUtils;
/**
 * 资源管理
 * @author lsp 
 *   
 */
public class HttpClient {
	public static String http(String url, String str) {
		URL u = null;
		HttpURLConnection con = null;

		try {
			u = new URL(url);
			
				// TODO Auto-generated catch block
				
			
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			if(str!=null) 
				osw.write(str);
			osw.flush();
			osw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 璇诲彇杩斿洖鍐呭
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			br.close();
		} catch (Exception e) {
			
		}
		
		return buffer.toString();
	}
	public static String httpGb2312(String url,String str) {
		URL u = null;
		HttpURLConnection con = null;

		try {
			u = new URL(url);
			
				// TODO Auto-generated catch block
				
			
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "GBK");
			if(str!=null) 
				osw.write(str);
			osw.flush();
			osw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		// 璇诲彇杩斿洖鍐呭
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			br.close();
		} catch (Exception e) {
			
		}
		
		return buffer.toString();
	}
	public static String httpwrite(String url, String str) {
		URL u = null;
		HttpURLConnection con = null;

		try {
			u = new URL(url);
			
				// TODO Auto-generated catch block
				
			
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			if(str!=null)
				osw.write(str);
			osw.flush();
			osw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		
		// 璇诲彇杩斿洖鍐呭
		
		try {
			 BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
			 FileOutputStream fos = new FileOutputStream(new File("d:/ssss.jpg"));
		      byte[] buf = new byte[8096];
		      int size = 0;
		      while ((size = bis.read(buf)) != -1)
		        fos.write(buf, 0, size);
		      fos.close();
		      bis.close();
		} catch (Exception e) {
			
		}
		
		return "";
	}
	
	
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            
            // 定义 BufferedReader输入流来读取URL的响应
          
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
         // 璇诲彇杩斿洖鍐呭
    		StringBuffer buffer = new StringBuffer();
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(
    					conn.getInputStream(), "UTF-8"));
    			String temp;
    			while ((temp = br.readLine()) != null) {
    				buffer.append(temp);
    				buffer.append("\n");
    			}
    			br.close();
    			result=buffer.toString();
    		} catch (Exception e) {
    			
    		}
        } catch (Exception e) {
            
            e.printStackTrace();
        }
       
        return result;
    }    
    /**
	 *
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public static String get(String urlAll) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
		try {

			URL url = new URL(urlAll);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?data=" + param;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性 
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    

/**
     * 发送Http post请求
     * 
     * @param xmlInfo
     *            json转化成的字符串
     * @param URL
     *            请求url
     * @return 返回信息
     */
    public static String doHttpPost(String URL, String xmlInfo) {
        System.out.println("发起的数据:" + xmlInfo);
        byte[] xmlData = xmlInfo.getBytes();
        InputStream instr = null;
        java.io.ByteArrayOutputStream out = null;
        try {
            URL url = new URL(URL);
            URLConnection urlCon = url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setUseCaches(false);
            urlCon.setRequestProperty("content-Type", "application/json");
            urlCon.setRequestProperty("charset", "utf-8");
            urlCon.setRequestProperty("Content-length",
                    String.valueOf(xmlData.length));
            System.out.println(String.valueOf(xmlData.length));
            DataOutputStream printout = new DataOutputStream(
                    urlCon.getOutputStream());
            printout.write(xmlData);
            printout.flush();
            printout.close();
            instr = urlCon.getInputStream();
            byte[] bis = IOUtils.toByteArray(instr);
            String ResponseString = new String(bis, "UTF-8");
            if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                System.out.println("返回空");
            }
            System.out.println("返回数据为:" + ResponseString);
            return ResponseString;
 
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            try {
                out.close();
                instr.close();
 
            } catch (Exception ex) {
                return "0";
            }
        }
    }


}

