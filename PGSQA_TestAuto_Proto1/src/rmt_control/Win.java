package rmt_control;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.win32.W32APIOptions;

public class Win {
	static public class Kernel32 {
		static {
			Native.register(NativeLibrary.getInstance("kernel32", W32APIOptions.DEFAULT_OPTIONS));
		}

		static public native Pointer CreateEvent (Pointer lpEventAttributes, boolean bManualReset, boolean bInitialState,
			WString lpName);

		static public native int WaitForSingleObject (Pointer hHandle, int dwMilliseconds);

		static public native boolean CloseHandle (Pointer hObject);
	}
}
