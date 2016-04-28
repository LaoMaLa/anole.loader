package org.tbwork.anole.subscriber.client;

public class GlobalConfig {

	public static final int CONNECTION_CHECK_INTERVAL = 60 ; // second
	
	/**
	 * E.g. If you set this value as 4, no matter how many threads
	 * ask for retrieving configuration from the server at the 
	 * same time, there are only 4 of them will be executed at 
	 * the same time. Other threads will block until some of those
	 * running threads finished retrieving.
	 */
	public static final int RETRIEVING_THREAD_POOL_SIZE = 4; 
	
	public static final int RETRIEVING_CONFIG_TIMEOUT_TIME = 2000; // millisecond
	
	public static final int RECONNECT_INTERVAL = 3; //second
	 
	
	public static int PING_INTERVAL = 5 ; //second
}
