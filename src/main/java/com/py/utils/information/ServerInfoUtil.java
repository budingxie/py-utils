package com.py.utils.information;

import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.*;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/24
 */
public class ServerInfoUtil {

    private static Map<String, Object> cpuMap = new HashMap<>();

    private static Map<String, Object> memMap = new HashMap<>();

    private static Map<String, Object> sysMap = new HashMap<>();

    private static Map<String, Object> jvmMap = new HashMap<>();

    private static List<Map<String, Object>> sysFilesMap = new ArrayList<>();

    public static void setCpuInfo(CentralProcessor processor) {
        // cpu信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 需要休眠才能获取到cpu信息
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softIrq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long ioWait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + ioWait + irq + softIrq + steal;
        cpuMap.put("CpuNum", processor.getLogicalProcessorCount());
        cpuMap.put("Total", totalCpu);
        cpuMap.put("Sys", cSys);
        cpuMap.put("Used", user);
        cpuMap.put("Wait", ioWait);
        cpuMap.put("Free", idle);
    }

    public static void main(String[] args) {
        // 初始化系统信息对象
        SystemInfo systemInfo = new SystemInfo();
        // 硬件：获取硬件信息
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        // 软件：获取操作系统进程相关信息
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

        // 1.获取BIOS系统信息
        ComputerSystem computerSystem = hardware.getComputerSystem();
        Firmware firmware = computerSystem.getFirmware();
        String name = firmware.getName();
        String description = firmware.getDescription();
        String firmwareManufacturer = firmware.getManufacturer();
        String releaseDate = firmware.getReleaseDate();
        String version = firmware.getVersion();

        System.out.println("固件名称：" + name);
        System.out.println("固件描述信息：" + description);
        System.out.println("固件制造商信息：" + firmwareManufacturer);
        System.out.println("固件发布时间：" + releaseDate);
        System.out.println("固件版本：" + version);

        // 2.传感器信息，风扇、温度信息
        Sensors sensors = hardware.getSensors();
        // 获取cpu风扇速度
        int[] sensorsFanSpeeds = sensors.getFanSpeeds();
        // 获取cpu电压
        double sensorsCpuVoltage = sensors.getCpuVoltage();
        // 获取cpu温度
        double sensorsCpuTemperature = sensors.getCpuTemperature();
        System.out.println("cpu风扇速度：" + Arrays.toString(sensorsFanSpeeds));
        System.out.println("cpu电压：" + sensorsCpuVoltage);
        System.out.println("cpu温度：" + sensorsCpuTemperature);

        // 3.内存信息
        GlobalMemory memory = hardware.getMemory();
        // 获取总内存
        long memoryTotal = memory.getTotal();
        // 获取可用系统运行内存
        long memoryAvailable = memory.getAvailable();
        // 获取虚拟内存对象
        VirtualMemory memoryVirtualMemory = memory.getVirtualMemory();
        System.out.printf("总内存：%d GB \n", memoryTotal / 1024 / 1024 / 1024);
        System.out.printf("可用系统运行内存：%d GB\n", memoryAvailable / 1024 / 1024 / 1024);
        System.out.println("虚拟内存对象：" + memoryVirtualMemory);

        // 4.Cpu线程信息

    }

}