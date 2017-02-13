package com.myboot;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Application implements EmbeddedServletContainerCustomizer {
	// 单网卡名称
	private static final String NETWORK_CARD = "eth0";
	// 绑定网卡名称
	private static final String NETWORK_CARD_BAND = "bond0";
	private static final Logger LOGGER = Logger.getLogger(Application.class);
	@RequestMapping("/")
	public String home() {
		return "Hello,MyBoot" + getLocalIP();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		int port=8899;
		if(System.getenv("PORT") != null){
			port = Integer.valueOf(System.getenv("PORT"));
		}
		System.out.println("springboot启动端口为："+port);
		container.setPort(port);
	}

	/**
	 * 获得主机IP
	 *
	 * @return String
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	// 获取虚机中ip地址
	public static String getLocalIPOnLinuix() {
		String ip = "";
		try {
			if (NetworkInterface.getNetworkInterfaces() == null) {
				return null;
			}
			Enumeration<NetworkInterface> enumer = (Enumeration<NetworkInterface>) NetworkInterface
					.getNetworkInterfaces();
			while (enumer.hasMoreElements()) {
				NetworkInterface nInterface = enumer.nextElement();
				// 单网卡或者绑定双网卡
				if ((NETWORK_CARD.equals(nInterface.getName())) || (NETWORK_CARD_BAND.equals(nInterface.getName()))) {
					Enumeration<InetAddress> enumerAddress = nInterface.getInetAddresses();
					if (enumerAddress == null) {
						continue;
					}
					while (enumerAddress.hasMoreElements()) {
						InetAddress inetAddress = enumerAddress.nextElement();
						if (inetAddress instanceof Inet6Address) {
							continue;
						}
						ip = inetAddress.getHostAddress();
					}
					break;
				} else {
					continue;
				}
			}
		} catch (SocketException e) {
			LOGGER.error("getLocalIP出现异常！异常信息：" + e.getMessage());
		}
		return ip;
	}

}