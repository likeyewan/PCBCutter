package com.hih.pcbcutter.ui.bean;

public class Signal {
	private Boolean choose;
	private String name;
	public Boolean getChoose() {
		return choose;
	}
	public void setChoose(Boolean choose) {
		this.choose = choose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Signal{" +
				"choose=" + choose +
				", name='" + name + '\'' +
				'}';
	}
}
