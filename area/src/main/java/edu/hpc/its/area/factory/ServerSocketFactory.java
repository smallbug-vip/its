package edu.hpc.its.area.factory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * 创建ServerSocket
 * 
 * @timestamp Feb 21, 2016 8:13:51 PM
 * @author smallbug
 */
public final class ServerSocketFactory {

	public ServerSocket createSocket(int port)
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {

		return (new ServerSocket(port));

	}

	public ServerSocket createSocket(int port, int backlog)
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {

		return (new ServerSocket(port, backlog));

	}

	public ServerSocket createSocket(int port, int backlog, InetAddress ifAddress)
			throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {

		return (new ServerSocket(port, backlog, ifAddress));

	}

}
