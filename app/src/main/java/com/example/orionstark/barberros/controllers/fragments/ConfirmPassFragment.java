package com.example.orionstark.barberros.controllers.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.orionstark.barberros.R;
import com.example.orionstark.barberros.models.User;
import com.example.orionstark.barberros.services.BarberrosService;

import org.json.JSONException;

public class ConfirmPassFragment extends Fragment {
   View view;
   Button confirm_btn;
   EditText pass, username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_confirm_pass, container, false);
        viewInit();
        return view;
    }

    private void viewInit() {
        confirm_btn = view.findViewById(R.id.confirm_pass_btn);
        pass = view.findViewById(R.id.password_confirmPass);
        username = view.findViewById(R.id.username_confirmPass);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Checking");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                try {
                    BarberrosService.checkSecPass(
                            username.getText().toString(), pass.getText().toString(),
                            getContext(), new BarberrosService.ServiceCallback() {
                                @Override
                                public void onSucceed(String message) {
                                    User.usernames = username.getText().toString();
                                    progressDialog.cancel();
                                    getFragmentManager()
                                            .beginTransaction()
                                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                                            .replace(R.id.passconfig_outlet, new CreatePassFragment())
                                            .commit();
                                }
                                @Override
                                public void onError(String message) {
                                    progressDialog.cancel();
                                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
                                }
                            });
                } catch (JSONException e) {
                    progressDialog.cancel();
                    Snackbar.make(view, "Error parsing data", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}
