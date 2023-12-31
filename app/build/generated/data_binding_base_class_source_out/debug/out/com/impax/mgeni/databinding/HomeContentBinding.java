// Generated by view binder compiler. Do not edit!
package com.impax.mgeni.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.impax.mgeni.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomeContentBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton ProfileImage;

  @NonNull
  public final TextView QRScan;

  @NonNull
  public final ImageView QRscan;

  @NonNull
  public final CardView cardRecyc;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final CardView cardView2;

  @NonNull
  public final CardView cardView3;

  @NonNull
  public final TextView checkOut;

  @NonNull
  public final ImageView checkOutIcon;

  @NonNull
  public final ImageView checkinIcon;

  @NonNull
  public final LinearLayout layout;

  @NonNull
  public final RecyclerView listData;

  @NonNull
  public final TextView tenantName;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView time;

  @NonNull
  public final View view;

  private HomeContentBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton ProfileImage,
      @NonNull TextView QRScan, @NonNull ImageView QRscan, @NonNull CardView cardRecyc,
      @NonNull CardView cardView, @NonNull CardView cardView2, @NonNull CardView cardView3,
      @NonNull TextView checkOut, @NonNull ImageView checkOutIcon, @NonNull ImageView checkinIcon,
      @NonNull LinearLayout layout, @NonNull RecyclerView listData, @NonNull TextView tenantName,
      @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView time,
      @NonNull View view) {
    this.rootView = rootView;
    this.ProfileImage = ProfileImage;
    this.QRScan = QRScan;
    this.QRscan = QRscan;
    this.cardRecyc = cardRecyc;
    this.cardView = cardView;
    this.cardView2 = cardView2;
    this.cardView3 = cardView3;
    this.checkOut = checkOut;
    this.checkOutIcon = checkOutIcon;
    this.checkinIcon = checkinIcon;
    this.layout = layout;
    this.listData = listData;
    this.tenantName = tenantName;
    this.textView = textView;
    this.textView2 = textView2;
    this.time = time;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomeContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomeContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.home_content, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomeContentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Profile_Image;
      ImageButton ProfileImage = ViewBindings.findChildViewById(rootView, id);
      if (ProfileImage == null) {
        break missingId;
      }

      id = R.id.QRScan;
      TextView QRScan = ViewBindings.findChildViewById(rootView, id);
      if (QRScan == null) {
        break missingId;
      }

      id = R.id.QRscan;
      ImageView QRscan = ViewBindings.findChildViewById(rootView, id);
      if (QRscan == null) {
        break missingId;
      }

      id = R.id.cardRecyc;
      CardView cardRecyc = ViewBindings.findChildViewById(rootView, id);
      if (cardRecyc == null) {
        break missingId;
      }

      id = R.id.cardView;
      CardView cardView = ViewBindings.findChildViewById(rootView, id);
      if (cardView == null) {
        break missingId;
      }

      id = R.id.cardView2;
      CardView cardView2 = ViewBindings.findChildViewById(rootView, id);
      if (cardView2 == null) {
        break missingId;
      }

      id = R.id.cardView3;
      CardView cardView3 = ViewBindings.findChildViewById(rootView, id);
      if (cardView3 == null) {
        break missingId;
      }

      id = R.id.checkOut;
      TextView checkOut = ViewBindings.findChildViewById(rootView, id);
      if (checkOut == null) {
        break missingId;
      }

      id = R.id.checkOutIcon;
      ImageView checkOutIcon = ViewBindings.findChildViewById(rootView, id);
      if (checkOutIcon == null) {
        break missingId;
      }

      id = R.id.checkinIcon;
      ImageView checkinIcon = ViewBindings.findChildViewById(rootView, id);
      if (checkinIcon == null) {
        break missingId;
      }

      id = R.id.layout;
      LinearLayout layout = ViewBindings.findChildViewById(rootView, id);
      if (layout == null) {
        break missingId;
      }

      id = R.id.listData;
      RecyclerView listData = ViewBindings.findChildViewById(rootView, id);
      if (listData == null) {
        break missingId;
      }

      id = R.id.tenantName;
      TextView tenantName = ViewBindings.findChildViewById(rootView, id);
      if (tenantName == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.time;
      TextView time = ViewBindings.findChildViewById(rootView, id);
      if (time == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new HomeContentBinding((ConstraintLayout) rootView, ProfileImage, QRScan, QRscan,
          cardRecyc, cardView, cardView2, cardView3, checkOut, checkOutIcon, checkinIcon, layout,
          listData, tenantName, textView, textView2, time, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
