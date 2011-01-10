package com.example.data;

public class ChartData implements IChartData {
	private double[] x;
	private double[] y;
	private double[][] xy;

	private Double xMin, xMax, yMin, yMax;

	public ChartData() { xy = new double[2][]; }

	public ChartData(final double[] x, final double[] y) {
		this();
		setXYValues(x, y);
	}

	@Override
	public double getRangeMin() {
		return yMin;
	}

	@Override
	public double getRangeMax() {
		return yMax;
	}

	@Override
	public double getDomainMin() {
		return xMin;
	}

	@Override
	public double getDomainMax() {
		return xMax;
	}

	@Override
	public double[] getXValues() {
		return x;
	}

	@Override
	public double[] getYValues() {
		return y;
	}

	@Override
	public double[][] getXYValues() {
		xy[0] = x;
		xy[1] = y;

		return xy;
	}

	@Override
	public void setXYValues(final double[] newX, final double[] newY) {
		if (newX == null || newY == null) {
			throw new RuntimeException("arrays of x and y values to set must be non-null");
		}
		if (newX.length != newY.length) {
			throw new RuntimeException("arrays of x and y values must be of equal length");
		}
		if (x == null || x.length != newX.length) {
			x = new double[newX.length];
		}
		if (y == null || y.length != newY.length) {
			y = new double[newY.length];
		}
		xMin = xMax = newX[0];
		yMin = yMax = newY[0];
		for (int i = 0; i < newX.length; i++) {
			x[i] = newX[i];
			y[i] = newY[i];
			if (yMin > y[i])
				yMin = y[i];
			if (yMax < y[i])
				yMax = y[i];
			if (xMin > x[i])
				xMin = x[i];
			if (xMax < x[i])
				xMax = x[i];
		}
	}
}
