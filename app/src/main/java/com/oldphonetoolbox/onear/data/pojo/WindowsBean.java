package com.oldphonetoolbox.onear.data.pojo;

import androidx.annotation.NonNull;

public class WindowsBean {
    private String windowTitle;
    private String cpuUsage;
    private String cpuCount;
    private String memoryUsage;
    private String memoryTotal;
    private String numberOfProcesses;


    @NonNull
    @Override
    public String toString() {
        return windowTitle + "," + cpuUsage + "," + cpuCount + "," + memoryUsage + "," + memoryTotal+ "," + numberOfProcesses;
    }

    public static WindowsBean build(String in){
        String[] arr = in.split(",");
        WindowsBean bean = new WindowsBean();
        bean.setWindowTitle(arr[0]);
        bean.setCpuUsage(arr[1]);
        bean.setCpuCount(arr[2]);
        bean.setMemoryUsage(arr[3]);
        bean.setMemoryTotal(arr[4]);
        bean.setNumberOfProcesses(arr[5]);
        return bean;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(String cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(String memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public String getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public void setNumberOfProcesses(String numberOfProcesses) {
        this.numberOfProcesses = numberOfProcesses;
    }
}
