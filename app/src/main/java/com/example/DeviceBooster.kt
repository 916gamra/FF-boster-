package com.example

import java.io.DataOutputStream
import java.io.IOException

object DeviceBooster {

    fun executeShellCommand(command: String): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("sh")
            val os = DataOutputStream(process.outputStream)
            os.writeBytes("$command\n")
            os.writeBytes("exit\n")
            os.flush()
            process.waitFor()
            process.exitValue() == 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Samsung Performance Mode
    fun enablePerformanceMode(enable: Boolean) {
        val value = if (enable) 1 else 0
        executeShellCommand("settings put global sem_perfomance_mode $value")
    }

    // GPU Rendering
    fun enableForceGpuRendering(enable: Boolean) {
        val value = if (enable) 1 else 0
        executeShellCommand("settings put global force_gpu_rendering $value")
    }

    // Animation Scales
    fun setAnimationScales(scale: Float) {
        executeShellCommand("settings put global window_animation_scale $scale")
        executeShellCommand("settings put global transition_animation_scale $scale")
        executeShellCommand("settings put global animator_duration_scale $scale")
    }

    // Touch Sensitivity
    fun enableTouchSensitivity(enable: Boolean) {
        val value = if (enable) 1 else 0
        executeShellCommand("settings put system touch_sensitivity $value")
    }

    // Wi-Fi Power Save
    fun disableWifiPowerSave(disable: Boolean) {
        val value = if (disable) 0 else 1
        executeShellCommand("settings put global wifi_power_save $value")
    }

    fun optimizeRam() {
        executeShellCommand("am kill-all")
    }

    // Cloudflare DNS
    fun enableCloudflareDns(enable: Boolean) {
        if (enable) {
            executeShellCommand("settings put global private_dns_mode hostname")
            executeShellCommand("settings put global private_dns_specifier 1dot1dot1dot1.cloudflare-dns.com")
        } else {
            executeShellCommand("settings put global private_dns_mode opportunistic")
            executeShellCommand("settings put global private_dns_specifier \"\"")
        }
    }

    // Smart Switch limit
    fun disableSmartSwitch(disable: Boolean) {
        val value = if (disable) 0 else 1
        executeShellCommand("settings put global smart_switch_enabled $value")
    }

    // Multi process
    fun enableMultiProcess(enable: Boolean) {
        val value = if (enable) 1 else 0
        executeShellCommand("settings put global multi_process $value")
    }

    // Cached processes
    fun setMaxCachedProcesses(max: Int) {
        executeShellCommand("settings put global activity_manager_constants max_cached_processes=$max")
    }
}
