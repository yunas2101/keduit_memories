package com.kedu.beans;

import com.kedu.interfaces.Tv;

public class LGTv implements Tv{

	private int channel;
	private int volume;
	
	public LGTv() {
		System.out.println("LGTv ����");
	}
	
	
	public LGTv(int channel, int volume) {
		super();
		this.channel = channel;
		this.volume = volume;
	}


	@Override
	public void channelUp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void channelDown() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setChannel(int channel) {
		this.channel = channel;
		
	}


	@Override
	public int getChannel() {
		return this.channel;
	}


	@Override
	public void setVolume(int volume) {
		this.volume = volume;
		
	}


	@Override
	public int getVolume() {
		return this.volume;
	}

	
}
