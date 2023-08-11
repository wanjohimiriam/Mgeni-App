// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.impax.mgeni.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityQrSideBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final LinearLayout cardLayout;

  @NonNull
  public final SurfaceView surfaceView;

  private ActivityQrSideBinding(@NonNull CoordinatorLayout rootView,
      @NonNull LinearLayout cardLayout, @NonNull SurfaceView surfaceView) {
    this.rootView = rootView;
    this.cardLayout = cardLayout;
    this.surfaceView = surfaceView;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityQrSideBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityQrSideBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_qr_side, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityQrSideBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardLayout;
      LinearLayout cardLayout = ViewBindings.findChildViewById(rootView, id);
      if (cardLayout == null) {
        break missingId;
      }

      id = R.id.surfaceView;
      SurfaceView surfaceView = ViewBindings.findChildViewById(rootView, id);
      if (surfaceView == null) {
        break missingId;
      }

      return new ActivityQrSideBinding((CoordinatorLayout) rootView, cardLayout, surfaceView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
