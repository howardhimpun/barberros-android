package com.example.orionstark.barberros.controllers.fragments;

import android.app.Activity;
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

public class CreatePassFragment extends Fragment {
    View view;
    Button submit_btn;
    EditText pass, confirm_pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_pass, container, false);
        initView();
        return view;
    }
    private void initView() {
        submit_btn = view.findViewById(R.id.submit_pass_btn);
        pass = view.findViewById(R.id.new_password_field);
        confirm_pass = view.findViewById(R.id.confirm_pass_field);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Checking");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                try {
                    BarberrosService.changePassword(
                            User.usernames, pass.getText().toString(),
                            getContext(), new BarberrosService.ServiceCallback() {
                                @Override
                                public void onSucceed(String message) {
                                    progressDialog.cancel();
                                    User.usernames = null;
                                    ((Activity) getContext()).finish();
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
