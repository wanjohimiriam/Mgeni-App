// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.impax.mgeni.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPasswordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView companyLayout;

  @NonNull
  public final TextInputLayout confirmPassTextField;

  @NonNull
  public final TextView emailLabel;

  @NonNull
  public final TextView logoView;

  @NonNull
  public final TextInputLayout passwordTextField;

  @NonNull
  public final TextView phoneLabel;

  @NonNull
  public final CardView phoneLayout;

  @NonNull
  public final Button resetPassBtn;

  @NonNull
  public final TextInputEditText textConfirmPass;

  private ActivityPasswordBinding(@NonNull ConstraintLayout rootView,
      @NonNull CardView companyLayout, @NonNull TextInputLayout confirmPassTextField,
      @NonNull TextView emailLabel, @NonNull TextView logoView,
      @NonNull TextInputLayout passwordTextField, @NonNull TextView phoneLabel,
      @NonNull CardView phoneLayout, @NonNull Button resetPassBtn,
      @NonNull TextInputEditText textConfirmPass) {
    this.rootView = rootView;
    this.companyLayout = companyLayout;
    this.confirmPassTextField = confirmPassTextField;
    this.emailLabel = emailLabel;
    this.logoView = logoView;
    this.passwordTextField = passwordTextField;
    this.phoneLabel = phoneLabel;
    this.phoneLayout = phoneLayout;
    this.resetPassBtn = resetPassBtn;
    this.textConfirmPass = textConfirmPass;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.companyLayout;
      CardView companyLayout = ViewBindings.findChildViewById(rootView, id);
      if (companyLayout == null) {
        break missingId;
      }

      id = R.id.confirmPassTextField;
      TextInputLayout confirmPassTextField = ViewBindings.findChildViewById(rootView, id);
      if (confirmPassTextField == null) {
        break missingId;
      }

      id = R.id.emailLabel;
      TextView emailLabel = ViewBindings.findChildViewById(rootView, id);
      if (emailLabel == null) {
        break missingId;
      }

      id = R.id.logoView;
      TextView logoView = ViewBindings.findChildViewById(rootView, id);
      if (logoView == null) {
        break missingId;
      }

      id = R.id.passwordTextField;
      TextInputLayout passwordTextField = ViewBindings.findChildViewById(rootView, id);
      if (passwordTextField == null) {
        break missingId;
      }

      id = R.id.phoneLabel;
      TextView phoneLabel = ViewBindings.findChildViewById(rootView, id);
      if (phoneLabel == null) {
        break missingId;
      }

      id = R.id.phoneLayout;
      CardView phoneLayout = ViewBindings.findChildViewById(rootView, id);
      if (phoneLayout == null) {
        break missingId;
      }

      id = R.id.resetPassBtn;
      Button resetPassBtn = ViewBindings.findChildViewById(rootView, id);
      if (resetPassBtn == null) {
        break missingId;
      }

      id = R.id.textConfirmPass;
      TextInputEditText textConfirmPass = ViewBindings.findChildViewById(rootView, id);
      if (textConfirmPass == null) {
        break missingId;
      }

      return new ActivityPasswordBinding((ConstraintLayout) rootView, companyLayout,
          confirmPassTextField, emailLabel, logoView, passwordTextField, phoneLabel, phoneLayout,
          resetPassBtn, textConfirmPass);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
