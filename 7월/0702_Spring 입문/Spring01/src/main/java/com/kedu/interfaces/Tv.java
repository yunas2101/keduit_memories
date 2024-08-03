package com.kedu.interfaces;

public interface Tv {

	public void channelUp();
	public void channelDown();
	public void volumeUp();
	public void volumeDown();
	
	public void setChannel(int channel);
	public int getChannel();
	public void setVolume(int volume);
	public int getVolume();
}
