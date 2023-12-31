// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.impax.mgeni.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCheckoutBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextInputEditText IdText;

  @NonNull
  public final TextInputLayout IdTextField;

  @NonNull
  public final TextInputLayout NameTextField;

  @NonNull
  public final Button cancelBtn;

  @NonNull
  public final TextInputEditText checkInTimeText;

  @NonNull
  public final TextInputLayout checkInTimeTextField;

  @NonNull
  public final Button checkOutBtn;

  @NonNull
  public final TextView createAccountTV;

  @NonNull
  public final TextInputEditText hostDepartmentText;

  @NonNull
  public final TextInputLayout hostDepartmentTextField;

  @NonNull
  public final TextInputEditText hostNameText;

  @NonNull
  public final TextInputLayout hostNameTextField;

  @NonNull
  public final TextInputEditText nameText;

  @NonNull
  public final ScrollView scroll;

  private FragmentCheckoutBinding(@NonNull FrameLayout rootView, @NonNull TextInputEditText IdText,
      @NonNull TextInputLayout IdTextField, @NonNull TextInputLayout NameTextField,
      @NonNull Button cancelBtn, @NonNull TextInputEditText checkInTimeText,
      @NonNull TextInputLayout checkInTimeTextField, @NonNull Button checkOutBtn,
      @NonNull TextView createAccountTV, @NonNull TextInputEditText hostDepartmentText,
      @NonNull TextInputLayout hostDepartmentTextField, @NonNull TextInputEditText hostNameText,
      @NonNull TextInputLayout hostNameTextField, @NonNull TextInputEditText nameText,
      @NonNull ScrollView scroll) {
    this.rootView = rootView;
    this.IdText = IdText;
    this.IdTextField = IdTextField;
    this.NameTextField = NameTextField;
    this.cancelBtn = cancelBtn;
    this.checkInTimeText = checkInTimeText;
    this.checkInTimeTextField = checkInTimeTextField;
    this.checkOutBtn = checkOutBtn;
    this.createAccountTV = createAccountTV;
    this.hostDepartmentText = hostDepartmentText;
    this.hostDepartmentTextField = hostDepartmentTextField;
    this.hostNameText = hostNameText;
    this.hostNameTextField = hostNameTextField;
    this.nameText = nameText;
    this.scroll = scroll;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCheckoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCheckoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_checkout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCheckoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Id_text;
      TextInputEditText IdText = ViewBindings.findChildViewById(rootView, id);
      if (IdText == null) {
        break missingId;
      }

      id = R.id.IdTextField;
      TextInputLayout IdTextField = ViewBindings.findChildViewById(rootView, id);
      if (IdTextField == null) {
        break missingId;
      }

      id = R.id.NameTextField;
      TextInputLayout NameTextField = ViewBindings.findChildViewById(rootView, id);
      if (NameTextField == null) {
        break missingId;
      }

      id = R.id.cancelBtn;
      Button cancelBtn = ViewBindings.findChildViewById(rootView, id);
      if (cancelBtn == null) {
        break missingId;
      }

      id = R.id.checkInTime_text;
      TextInputEditText checkInTimeText = ViewBindings.findChildViewById(rootView, id);
      if (checkInTimeText == null) {
        break missingId;
      }

      id = R.id.checkInTimeTextField;
      TextInputLayout checkInTimeTextField = ViewBindings.findChildViewById(rootView, id);
      if (checkInTimeTextField == null) {
        break missingId;
      }

      id = R.id.checkOutBtn;
      Button checkOutBtn = ViewBindings.findChildViewById(rootView, id);
      if (checkOutBtn == null) {
        break missingId;
      }

      id = R.id.create_accountTV;
      TextView createAccountTV = ViewBindings.findChildViewById(rootView, id);
      if (createAccountTV == null) {
        break missingId;
      }

      id = R.id.hostDepartment_text;
      TextInputEditText hostDepartmentText = ViewBindings.findChildViewById(rootView, id);
      if (hostDepartmentText == null) {
        break missingId;
      }

      id = R.id.hostDepartmentTextField;
      TextInputLayout hostDepartmentTextField = ViewBindings.findChildViewById(rootView, id);
      if (hostDepartmentTextField == null) {
        break missingId;
      }

      id = R.id.hostName_text;
      TextInputEditText hostNameText = ViewBindings.findChildViewById(rootView, id);
      if (hostNameText == null) {
        break missingId;
      }

      id = R.id.hostNameTextField;
      TextInputLayout hostNameTextField = ViewBindings.findChildViewById(rootView, id);
      if (hostNameTextField == null) {
        break missingId;
      }

      id = R.id.name_text;
      TextInputEditText nameText = ViewBindings.findChildViewById(rootView, id);
      if (nameText == null) {
        break missingId;
      }

      id = R.id.scroll;
      ScrollView scroll = ViewBindings.findChildViewById(rootView, id);
      if (scroll == null) {
        break missingId;
      }

      return new FragmentCheckoutBinding((FrameLayout) rootView, IdText, IdTextField, NameTextField,
          cancelBtn, checkInTimeText, checkInTimeTextField, checkOutBtn, createAccountTV,
          hostDepartmentText, hostDepartmentTextField, hostNameText, hostNameTextField, nameText,
          scroll);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
