
package com.qysoft.rapid.core;

import com.jfinal.kit.FileKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.server.IServer;
import com.jfinal.server.Scanner;
import com.qysoft.rapid.consts.RapidConsts;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 * Rapid开发服务器
 * @author liugong
 *
 */
class RapidServer implements IServer {
	
	private String webAppDir;
	private int port;
	private String context;
	private int scanIntervalSeconds;
	private boolean running = false;
	private Server server;
	private WebAppContext webApp;
	
	RapidServer(String webAppDir, int port, String context, int scanIntervalSeconds) {
		if (webAppDir == null)
			throw new IllegalStateException("Invalid webAppDir of web server: " + webAppDir);
		if (port < 0 || port > 65536)
			throw new IllegalArgumentException("Invalid port of web server: " + port);
		if (StrKit.isBlank(context))
			throw new IllegalStateException("Invalid context of web server: " + context);
		
		this.webAppDir = webAppDir;
		this.port = port;
		this.context = context;
		this.scanIntervalSeconds = scanIntervalSeconds;
	}
	
	public void start() {
		if (!running) {
			try {doStart();} catch (Exception e) {e.printStackTrace();}
			running = true;
		}
	}
	
	public void stop() {
		if (running) {
			try {server.stop();} catch (Exception e) {e.printStackTrace();}
			running = false;
		}
	}
	
	private void doStart() {
		if (!available(port))
			throw new IllegalStateException("port: " + port + " already in use!");
		
		deleteSessionData();
		
		System.out.println("Starting Rapid " + RapidConsts.RAPID_VERSION);
		server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		server.addConnector(connector);
		webApp = new WebAppContext();
		webApp.setContextPath(context);
                webApp.setResourceBase(PathKit.getWebRootPath());//修改为真实的目录
//		webApp.setResourceBase(webAppDir);	// webApp.setWar(webAppDir);
		webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");	// webApp.setInitParams(Collections.singletonMap("org.mortbay.jetty.servlet.Default.useFileMappedBuffer", "false"));
		persistSession(webApp);
		
		server.setHandler(webApp);
		changeClassLoader(webApp);
		
		// configureScanner
		if (scanIntervalSeconds > 0) {
			Scanner scanner = new Scanner(PathKit.getRootClassPath(), scanIntervalSeconds) {
				public void onChange() {
					try {
						System.err.println("\nLoading changes ......");
						webApp.stop();
						RapidClassLoader loader = new RapidClassLoader(webApp, getClassPath());
						webApp.setClassLoader(loader);
						webApp.start();
						System.err.println("Loading complete.");
					} catch (Exception e) {
						System.err.println("Error reconfiguring/restarting webapp after change in watched files");
						e.printStackTrace();
					}
				}
			};
			System.out.println("Starting scanner at interval of " + scanIntervalSeconds + " seconds.");
			scanner.start();
		}
		
		try {
			System.out.println("Starting web server on port: " + port);
			server.start();
			System.out.println("Starting Complete. Welcome To 【"+RapidConsts.RAPID_NAME+"】:)");
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
		return;
	}
	
	private void changeClassLoader(WebAppContext webApp) {
		try {
			String classPath = getClassPath();
			RapidClassLoader wacl = new RapidClassLoader(webApp, classPath);
			wacl.addClassPath(classPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getClassPath() {
		return System.getProperty("java.class.path");
	}
	
	private void deleteSessionData() {
		try {
			FileKit.delete(new File(getStoreDir()));
		}
		catch (Exception e) {
		}
	}
	
	private String getStoreDir() {
		String storeDir = PathKit.getWebRootPath() + "/../../session_data" + context;
		if ("\\".equals(File.separator))
			storeDir = storeDir.replaceAll("/", "\\\\");
		return storeDir;
	}
	
	private void persistSession(WebAppContext webApp) {
		String storeDir = getStoreDir();
		
		SessionManager sm = webApp.getSessionHandler().getSessionManager();
		if (sm instanceof HashSessionManager) {
			((HashSessionManager)sm).setStoreDirectory(new File(storeDir));
			return ;
		}
		
		HashSessionManager hsm = new HashSessionManager();
		hsm.setStoreDirectory(new File(storeDir));
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(hsm);
		webApp.setSessionHandler(sh);
	}
	
	private static boolean available(int port) {
		if (port <= 0) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}
		
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}
			
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// should not be thrown, just detect port available.
				}
			}
		}
		return false;
	}
}






