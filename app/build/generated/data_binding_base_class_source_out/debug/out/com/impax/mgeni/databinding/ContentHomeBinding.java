// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.impax.mgeni.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout buttonLayout;

  @NonNull
  public final Button checkInBtn;

  @NonNull
  public final Button checkOutBtn;

  @NonNull
  public final TextView date;

  @NonNull
  public final DigitalClock dateTextView;

  @NonNull
  public final TextView entryPoint;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final TextView salutation;

  private ContentHomeBinding(@NonNull ConstraintLayout rootView, @NonNull LinearLayout buttonLayout,
      @NonNull Button checkInBtn, @NonNull Button checkOutBtn, @NonNull TextView date,
      @NonNull DigitalClock dateTextView, @NonNull TextView entryPoint,
      @NonNull LinearLayout linearLayout2, @NonNull TextView salutation) {
    this.rootView = rootView;
    this.buttonLayout = buttonLayout;
    this.checkInBtn = checkInBtn;
    this.checkOutBtn = checkOutBtn;
    this.date = date;
    this.dateTextView = dateTextView;
    this.entryPoint = entryPoint;
    this.linearLayout2 = linearLayout2;
    this.salutation = salutation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonLayout;
      LinearLayout buttonLayout = ViewBindings.findChildViewById(rootView, id);
      if (buttonLayout == null) {
        break missingId;
      }

      id = R.id.checkInBtn;
      Button checkInBtn = ViewBindings.findChildViewById(rootView, id);
      if (checkInBtn == null) {
        break missingId;
      }

      id = R.id.checkOutBtn;
      Button checkOutBtn = ViewBindings.findChildViewById(rootView, id);
      if (checkOutBtn == null) {
        break missingId;
      }

      id = R.id.date;
      TextView date = ViewBindings.findChildViewById(rootView, id);
      if (date == null) {
        break missingId;
      }

      id = R.id.date_TextView;
      DigitalClock dateTextView = ViewBindings.findChildViewById(rootView, id);
      if (dateTextView == null) {
        break missingId;
      }

      id = R.id.entryPoint;
      TextView entryPoint = ViewBindings.findChildViewById(rootView, id);
      if (entryPoint == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.salutation;
      TextView salutation = ViewBindings.findChildViewById(rootView, id);
      if (salutation == null) {
        break missingId;
      }

      return new ContentHomeBinding((ConstraintLayout) rootView, buttonLayout, checkInBtn,
          checkOutBtn, date, dateTextView, entryPoint, linearLayout2, salutation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
