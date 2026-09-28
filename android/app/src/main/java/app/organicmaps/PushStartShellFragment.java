package app.organicmaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.organicmaps.bookmarks.BookmarkCategoriesActivity;
import app.organicmaps.sdk.routing.RoutingController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PushStartShellFragment extends Fragment
{
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.push_start_shell, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    BottomNavigationView navigation = view.findViewById(R.id.push_start_navigation);
    navigation.setSelectedItemId(R.id.push_start_tab_map);
    navigation.setOnItemSelectedListener(item -> {
      final int itemId = item.getItemId();
      if (itemId == R.id.push_start_tab_map)
      {
      {
        return true;
      }
      else if (itemId == R.id.push_start_tab_ride)
      {
        RideDialogFragment.show(getParentFragmentManager());
        return true;
      }
      else if (itemId == R.id.push_start_tab_routes)
      {
        BookmarkCategoriesActivity.start(requireActivity());
        return true;
      }
      else if (itemId == R.id.push_start_tab_navigation)
      {
        RoutingController controller = RoutingController.get();
        if (controller.isPlanning() || controller.isNavigating())
          ((MwmActivity) requireActivity()).updateMenu();
        else
          controller.prepare(null, null);
        return true;
      }
      else if (itemId == R.id.push_start_tab_connect)
      {
        ConnectDialogFragment.show(getParentFragmentManager());
        return true;
        }
        return false;
    });
  }
}