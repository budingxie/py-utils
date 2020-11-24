package com.py.utils.information;

import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.*;
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

        // 4.Cpu线程信息，获取机器硬件执行，cpu频率/型号/核心相关信息
        CentralProcessor centralProcessor = hardware.getProcessor();
        // 获取供应商
        String vendor = centralProcessor.getVendor();
        // 获取供应商频率
        long vendorFreq = centralProcessor.getVendorFreq();
        // 获取处理器id
        String processorProcessorId = centralProcessor.getProcessorID();
        // 获取标识符
        String identifier = centralProcessor.getIdentifier();
        // 判断cpu是否为64位的
        boolean cpu64bit = centralProcessor.isCpu64bit();
        // 获取cpu步进
        String stepping = centralProcessor.getStepping();
        // 获取型号
        String model = centralProcessor.getModel();
        // 获取家族
        String processorFamily = centralProcessor.getFamily();
        // 获取cpu负载间隔刻度
        double systemCpuLoadBetweenTicks = centralProcessor.getSystemCpuLoadBetweenTicks(new long[]{1, 2, 3});
        // 获取cpu负载刻度
        long[] systemCpuLoadTicks = centralProcessor.getSystemCpuLoadTicks();
        // 获取cpu平均负载
        double[] systemLoadAverage = centralProcessor.getSystemLoadAverage(10);
        // 获取处理器cpu负载间隔刻度
        double[] processorCpuLoadBetweenTicks = centralProcessor.getProcessorCpuLoadBetweenTicks(new long[][]{});
        // 获取处理器cpu负载刻度
        long[][] processorCpuLoadTicks = centralProcessor.getProcessorCpuLoadTicks();
        // 获取逻辑处理器数量
        int logicalProcessorCount = centralProcessor.getLogicalProcessorCount();
        // 获取物理处理器数量
        int physicalProcessorCount = centralProcessor.getPhysicalProcessorCount();
        // 获取物理包装数量
        int physicalPackageCount = centralProcessor.getPhysicalPackageCount();
        // 获取上下文切换数量
        long contextSwitches = centralProcessor.getContextSwitches();
        // 获取中断
        long interrupts = centralProcessor.getInterrupts();


        // 5.显示器信息，显示器相关信号/分辨率之类的信息
        Display[] displays = hardware.getDisplays();

        // 6.磁盘信息，当前磁盘的硬件信息，读写状态，分区信息等
        HWDiskStore[] diskStores = hardware.getDiskStores();

        // 7.网卡信息，获取网卡详细信息，mac/ip4/ip6地址，读写状态，中断/错误等信息
        NetworkIF[] networkIfs = hardware.getNetworkIFs();

        // 8.电源状态
        PowerSource[] powerSources = hardware.getPowerSources();

        // 9.声卡信息，获取名称/描述等
        SoundCard[] soundCards = hardware.getSoundCards();

        // 10.USB信息，获取USB接口信息，可用过滤正在使用的USB接口，以及相关详细信息，true 树形返回
        UsbDevice[] usbDevices = hardware.getUsbDevices(true);

        // 系统进程相关信息
        // 11.获取操作系统位数
        int bitness = operatingSystem.getBitness();

        // 12.获取指定线程下的子线程，获取该父进程下指定数量排序下的子进程
        // 参数：父进程ID；设置返回进程数量；进程排序烦烦烦
        OSProcess[] childProcesses = operatingSystem.getChildProcesses(1, 2, OperatingSystem.ProcessSort.CPU);

        // 13.获取操作系统类别 linux/MACOS/unix/windows
        String family = operatingSystem.getFamily();

        // 14.获取网络参数，系统中网卡信息，dns信息，域名信息，ip4/6信息
        NetworkParams networkParams = operatingSystem.getNetworkParams();
        String[] dnsServers = networkParams.getDnsServers();
        String domainName = networkParams.getDomainName();
        String hostName = networkParams.getHostName();
        String ipv4DefaultGateway = networkParams.getIpv4DefaultGateway();
        String ipv6DefaultGateway = networkParams.getIpv6DefaultGateway();

        // 15.获取指定进程信息，传入进程号，获取该进程详细信息，所属组/用户/状态等
        OSProcess process = operatingSystem.getProcess(1121);
        // 获取进程名称
        String processName = process.getName();
        // 获取进程程序所在的位置
        String path = process.getPath();
        // 获取命令行
        String commandLine = process.getCommandLine();
        // 获取当前工作目录
        String currentWorkingDirectory = process.getCurrentWorkingDirectory();
        // 获取用户信息
        String user = process.getUser();
        // 获取用户id
        String userId = process.getUserID();
        // 获取组信息
        String group = process.getGroup();
        // 获取组id
        String groupId = process.getGroupID();
        // 获取状态
        OSProcess.State state = process.getState();
        // 获取进程id
        int processId = process.getProcessID();
        // 获取夫进程id
        int parentProcessId = process.getParentProcessID();
        // 获取线程数
        int processThreadCount = process.getThreadCount();
        // 优先级
        int priority = process.getPriority();
        // 虚拟大小
        long virtualSize = process.getVirtualSize();
        // 实际使用物理内存
        long residentSetSize = process.getResidentSetSize();
        // 内核时间
        long kernelTime = process.getKernelTime();
        // 用户时间
        long userTime = process.getUserTime();
        // 正常运行时间
        long upTime = process.getUpTime();
        // 开始时间
        long startTime = process.getStartTime();
        // 读取字节
        long bytesRead = process.getBytesRead();
        // 写入字节
        long bytesWritten = process.getBytesWritten();
        // 打开文件数
        long openFiles = process.getOpenFiles();


        // 16.获取当前系统中的进程数
        int processCount = operatingSystem.getProcessCount();

        // 17.根据排序规则返回指定线程数
        OSProcess[] processes = operatingSystem.getProcesses(10, OperatingSystem.ProcessSort.CPU);

        // 18.获取进程ID list内所有进程
        List<OSProcess> processList = operatingSystem.getProcesses(new ArrayList<>());

        // 19.获取系统内总线程数
        int threadCount = operatingSystem.getThreadCount();

        // 20.获取系统启动时间
        long systemBootTime = operatingSystem.getSystemBootTime();

        // 21.获取系统版本信息
        OperatingSystemVersion systemVersion = operatingSystem.getVersion();

        // 22.获取cpu供货商
        String manufacturer = operatingSystem.getManufacturer();

        // 23.获取系统硬盘信息，FileSystem
        FileSystem fileSystem = operatingSystem.getFileSystem();
        // 获取最大文件描述符
        long maxFileDescriptors = fileSystem.getMaxFileDescriptors();
        // 获取打开的文件描述符
        long openFileDescriptors = fileSystem.getOpenFileDescriptors();
        // 获取盘符相关信息
        OSFileStore[] fileStores = fileSystem.getFileStores();


    }

}