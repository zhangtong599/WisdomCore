package com.javacore.wisdom.p2p;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.javacore.wisdom.cache.NetConfigCache;

@Component
@Order(2)
public class P2PServer implements CommandLineRunner {

	// log record
	private Logger logger = Logger.getLogger(P2PServer.class);

	// net port
	private int port;

	// connect to server socket
	private List<WebSocket> revSockets = new ArrayList<WebSocket>();

	// get receive sockets list
	public List<WebSocket> getRevSockets() {
		return revSockets;
	}

	// set receive socket list
	public void setRevSockets(List<WebSocket> revSockets) {
		this.revSockets = revSockets;
	}

	@Override
	public void run(String... args) throws Exception {

		this.initP2PServer();
	}

	/*
	 * initialize p2p server implementation the call back method
	 */
	public void initP2PServer() {

		this.port = Integer.parseInt(NetConfigCache.netcgc.get("p2pport"));
		WebSocketServer wss = new WebSocketServer(new InetSocketAddress(port)) {

			@Override
			public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

				// add clientSocket to socket pool
				revSockets.add(webSocket);
			}

			@Override
			public void onClose(WebSocket webSocket, int i, String s, boolean b) {

				// remove clientSocket from socket pool
				revSockets.remove(webSocket);
			}

			@Override
			public void onMessage(WebSocket webSocket, String msg) {

			}

			@Override
			public void onError(WebSocket webSocket, Exception ef) {

				// when error occurs,remove client socket
				revSockets.remove(webSocket);
			}

			@Override
			public void onStart() {
				// server startup

			}

		};

		wss.start();

		logger.info("p2p server is listening on port:" + String.valueOf(port));
	}

	/**
	 * server send to client with peer to peer
	 * 
	 * @param wsClient client socket
	 * @param msg      send to client
	 */
	public void sendMessage(WebSocket wsRemote, String msg) {

		wsRemote.send(msg);
	}

	public void brodcastMessage(String msg) {

		// if this is no client connection then return
		if (this.revSockets.size() == 0)
			return;

		// if this is no normal message then return
		if (Strings.isNullOrEmpty(msg))
			return;

		// send one message to remote client after another
		for (WebSocket ws : this.revSockets) {
			this.sendMessage(ws, msg);
		}

	}

}
