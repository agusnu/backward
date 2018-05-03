package docotel.com.backward;

import android.content.Context;

import java.io.File;

/**
 * Created by agusn on 31/07/2017.
 */

public class DeviceHelper {
    public Boolean isDeviceRooted(Context context){
        boolean isRooted = checkSuperUser() || findBinary("su") || checkBuildInfo();
        return isRooted;
    }

    private boolean checkSuperUser() {
        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private boolean isAbleToExecCommand() {
        return canExecuteCommand("/system/xbin/which su")
                || canExecuteCommand("/system/bin/which su")
                || canExecuteCommand("which su");
    }

    private boolean canExecuteCommand(String command) {
        boolean executedSuccesfully;
        try {
            Runtime.getRuntime().exec(command);
            executedSuccesfully = true;
        } catch (Exception e) {
            executedSuccesfully = false;
        }

        return executedSuccesfully;
    }

    private boolean findBinary(String binaryName) {
        boolean found = false;
        if (!found) {
            String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                    "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
            for (String where : places) {
                if ( new File( where + binaryName ).exists() ) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    private boolean checkBuildInfo(){
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }else{
            return false;
        }
    }
}
