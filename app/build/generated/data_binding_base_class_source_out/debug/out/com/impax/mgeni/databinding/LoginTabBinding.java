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

public final class LoginTabBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout PasswordTextField;

  @NonNull
  public final CardView companyLayout;

  @NonNull
  public final TextInputEditText emailAddress;

  @NonNull
  public final Button loginBtn;

  @NonNull
  public final CardView passLayout;

  @NonNull
  public final TextView resetPass;

  @NonNull
  public final TextInputEditText textPassword;

  @NonNull
  public final TextInputLayout usernameTextField;

  private LoginTabBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputLayout PasswordTextField, @NonNull CardView companyLayout,
      @NonNull TextInputEditText emailAddress, @NonNull Button loginBtn,
      @NonNull CardView passLayout, @NonNull TextView resetPass,
      @NonNull TextInputEditText textPassword, @NonNull TextInputLayout usernameTextField) {
    this.rootView = rootView;
    this.PasswordTextField = PasswordTextField;
    this.companyLayout = companyLayout;
    this.emailAddress = emailAddress;
    this.loginBtn = loginBtn;
    this.passLayout = passLayout;
    this.resetPass = resetPass;
    this.textPassword = textPassword;
    this.usernameTextField = usernameTextField;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LoginTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LoginTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.login_tab, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LoginTabBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.PasswordTextField;
      TextInputLayout PasswordTextField = ViewBindings.findChildViewById(rootView, id);
      if (PasswordTextField == null) {
        break missingId;
      }

      id = R.id.companyLayout;
      CardView companyLayout = ViewBindings.findChildViewById(rootView, id);
      if (companyLayout == null) {
        break missingId;
      }

      id = R.id.emailAddress;
      TextInputEditText emailAddress = ViewBindings.findChildViewById(rootView, id);
      if (emailAddress == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      Button loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      id = R.id.passLayout;
      CardView passLayout = ViewBindings.findChildViewById(rootView, id);
      if (passLayout == null) {
        break missingId;
      }

      id = R.id.resetPass;
      TextView resetPass = ViewBindings.findChildViewById(rootView, id);
      if (resetPass == null) {
        break missingId;
      }

      id = R.id.textPassword;
      TextInputEditText textPassword = ViewBindings.findChildViewById(rootView, id);
      if (textPassword == null) {
        break missingId;
      }

      id = R.id.usernameTextField;
      TextInputLayout usernameTextField = ViewBindings.findChildViewById(rootView, id);
      if (usernameTextField == null) {
        break missingId;
      }

      return new LoginTabBinding((ConstraintLayout) rootView, PasswordTextField, companyLayout,
          emailAddress, loginBtn, passLayout, resetPass, textPassword, usernameTextField);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
