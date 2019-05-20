package com.janloong.javabase.therad.energy;


public class EnergyTransferTask implements Runnable{

	//共享的能量世界
	private EnergySystem energySystem;
	//能量转移的源能量盒子下标
	private int fromBox;
	//单次能量转移最大单元
	private double maxAmount;
	//最大休眠时间（毫秒）
	private int DELAY = 10;

	public EnergyTransferTask(EnergySystem energySystem, int from, double max){
		this.energySystem = energySystem;
		this.fromBox = from;
		this.maxAmount = max;
	}

	public void run() {
		try{
			while (true){
				int toBox = (int) (energySystem.getBoxAmount()* Math.random());
				double amount = maxAmount * Math.random();
				energySystem.transfer(fromBox, toBox, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
