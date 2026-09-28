package app.organicmaps;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import app.organicmaps.sdk.location.TrackRecorder;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RideDialogFragment extends DialogFragment
{
  private static final String TAG = RideDialogFragment.class.getSimpleName();

  public static void show(@NonNull FragmentManager fragmentManager)
  {
    if (fragmentManager.findFragmentByTag(TAG) == null)
      new RideDialogFragment().show(fragmentManager, TAG);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    boolean isRecording = TrackRecorder.nativeIsTrackRecordingEnabled();
    MwmActivity activity = (MwmActivity) requireActivity();
    MaterialAlertDialogBuilder builder =
        new MaterialAlertDialogBuilder(activity, R.style.MwmTheme_AlertDialog)
            .setTitle(R.string.push_start_ride)
            .setMessage(isRecording ? R.string.push_start_ride_recording_active
                                    : R.string.push_start_ride_recording_inactive)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(isRecording ? R.string.push_start_stop_recording : R.string.push_start_record,
                               (dialog, which) -> {
                                 if (isRecording)
                                   activity.onTrackRecordingCancelled();
                                 else
                                   activity.onRideRecordButtonClicked();
                               });
    if (isRecording)
      builder.setNeutralButton(R.string.push_start_save_stop, (dialog, which) -> activity.onTrackRecordingSaved());
    return builder.create();
  }
}
