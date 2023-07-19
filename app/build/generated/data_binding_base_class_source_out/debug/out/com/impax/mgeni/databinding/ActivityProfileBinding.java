// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.impax.mgeni.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backB;

  @NonNull
  public final AppCompatButton closeBtn;

  @NonNull
  public final TextView email;

  @NonNull
  public final LinearLayout loginBtn;

  @NonNull
  public final TextView phoneNumber;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final TextView project;

  @NonNull
  public final TextView resetPass;

  @NonNull
  public final AppCompatButton saveBtn;

  @NonNull
  public final TextView textName;

  @NonNull
  public final View view;

  private ActivityProfileBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton backB,
      @NonNull AppCompatButton closeBtn, @NonNull TextView email, @NonNull LinearLayout loginBtn,
      @NonNull TextView phoneNumber, @NonNull CircleImageView profileImage,
      @NonNull TextView project, @NonNull TextView resetPass, @NonNull AppCompatButton saveBtn,
      @NonNull TextView textName, @NonNull View view) {
    this.rootView = rootView;
    this.backB = backB;
    this.closeBtn = closeBtn;
    this.email = email;
    this.loginBtn = loginBtn;
    this.phoneNumber = phoneNumber;
    this.profileImage = profileImage;
    this.project = project;
    this.resetPass = resetPass;
    this.saveBtn = saveBtn;
    this.textName = textName;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backB;
      ImageButton backB = ViewBindings.findChildViewById(rootView, id);
      if (backB == null) {
        break missingId;
      }

      id = R.id.closeBtn;
      AppCompatButton closeBtn = ViewBindings.findChildViewById(rootView, id);
      if (closeBtn == null) {
        break missingId;
      }

      id = R.id.email;
      TextView email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      LinearLayout loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      id = R.id.phoneNumber;
      TextView phoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumber == null) {
        break missingId;
      }

      id = R.id.profile_image;
      CircleImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.project;
      TextView project = ViewBindings.findChildViewById(rootView, id);
      if (project == null) {
        break missingId;
      }

      id = R.id.resetPass;
      TextView resetPass = ViewBindings.findChildViewById(rootView, id);
      if (resetPass == null) {
        break missingId;
      }

      id = R.id.saveBtn;
      AppCompatButton saveBtn = ViewBindings.findChildViewById(rootView, id);
      if (saveBtn == null) {
        break missingId;
      }

      id = R.id.textName;
      TextView textName = ViewBindings.findChildViewById(rootView, id);
      if (textName == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new ActivityProfileBinding((ConstraintLayout) rootView, backB, closeBtn, email,
          loginBtn, phoneNumber, profileImage, project, resetPass, saveBtn, textName, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}