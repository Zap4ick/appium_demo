package secretescapes.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AdbUtils {

    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public static void stopEmulator(String emulatorName) {
        log.info("Stopping emulator {}", emulatorName);
        String cmd = String.format("adb -s %s emu kill", emulatorName);
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            if (IS_WINDOWS) {
                process = runtime.exec(String.format("cmd.exe /c %s", cmd));
            } else {
                process = runtime.exec(new String[]{"/bin/bash", "-c", cmd});
            }
        } catch (IOException e) {
            log.info("Exception during executing command: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            log.info("Exception during waiting for process: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        process.destroy();
    }
}
