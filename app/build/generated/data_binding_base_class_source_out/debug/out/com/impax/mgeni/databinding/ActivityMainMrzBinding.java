// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.impax.mgeni.R;
import com.impax.mgeni.ocr.overlay.TextOverlay;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainMrzBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final LinearLayout cardLayout;

  @NonNull
  public final TextOverlay textOverLay;

  @NonNull
  public final PreviewView viewFinder;

  private ActivityMainMrzBinding(@NonNull CoordinatorLayout rootView,
      @NonNull LinearLayout cardLayout, @NonNull TextOverlay textOverLay,
      @NonNull PreviewView viewFinder) {
    this.rootView = rootView;
    this.cardLayout = cardLayout;
    this.textOverLay = textOverLay;
    this.viewFinder = viewFinder;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainMrzBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainMrzBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main_mrz, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainMrzBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardLayout;
      LinearLayout cardLayout = ViewBindings.findChildViewById(rootView, id);
      if (cardLayout == null) {
        break missingId;
      }

      id = R.id.textOverLay;
      TextOverlay textOverLay = ViewBindings.findChildViewById(rootView, id);
      if (textOverLay == null) {
        break missingId;
      }

      id = R.id.viewFinder;
      PreviewView viewFinder = ViewBindings.findChildViewById(rootView, id);
      if (viewFinder == null) {
        break missingId;
      }

      return new ActivityMainMrzBinding((CoordinatorLayout) rootView, cardLayout, textOverLay,
          viewFinder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}