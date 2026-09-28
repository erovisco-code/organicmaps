package app.organicmaps;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ConnectDialogFragment extends DialogFragment
{
  private static final String TAG = ConnectDialogFragment.class.getSimpleName();

  public static void show(@NonNull FragmentManager fragmentManager)
  {
    if (fragmentManager.findFragmentByTag(TAG) == null)
      new ConnectDialogFragment().show(fragmentManager, TAG);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    Context context = requireContext();
    boolean supportsWifiDirect = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT);
    DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
    int presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION).length;
    CharSequence wifiStatus =
        getText(supportsWifiDirect ? R.string.push_start_supported : R.string.push_start_not_supported);
    String status = getString(R.string.push_start_connect_status, wifiStatus, presentationDisplays);

    return new MaterialAlertDialogBuilder(context, R.style.MwmTheme_AlertDialog)
        .setTitle(R.string.push_start_connect)
        .setMessage(status)
        .setPositiveButton(R.string.close, null)
        .create();
  }
}
