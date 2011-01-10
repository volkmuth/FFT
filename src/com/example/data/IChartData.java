package com.example.data;

public interface IChartData {
	public double getRangeMin();
	public double getRangeMax();
	public double getDomainMin();
	public double getDomainMax();
	public double[] getXValues();
	public double[] getYValues();
	public double[][] getXYValues();
	
	public void setXYValues(double[] newX, double[] newY);
}
