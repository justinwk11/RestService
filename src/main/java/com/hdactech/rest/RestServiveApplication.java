package com.hdactech.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiveApplication {
	
//	Justin VM
	public static final String FULL_NODE_IP = "192.168.181.130";
	public static final String FULL_NODE_PORT = "6448";
	public static final String RPC_USER = "hdacrpc";
	public static final String RPC_PW = "hdac1234";
	
	public static void main(String[] args) {
		SpringApplication.run(RestServiveApplication.class, args);
	}
}
