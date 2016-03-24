package com.lifeservice.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil
{
	private String address;
	private int port;
	//private String password;
	
	public Jedis createRedis()
	{
		 Jedis jedis = new Jedis(address, port,5000);
		 //jedis.auth(password);
		 
		 return jedis; 
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	/*public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}*/
	
	
	
}
