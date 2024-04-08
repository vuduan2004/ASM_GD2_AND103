package com.example.ass_and_rest_api.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ass_and_rest_api.R;
import com.example.ass_and_rest_api.screen.ChangePasswordActivity;
import com.example.ass_and_rest_api.screen.LoginActivity;


public class AccountFragment extends Fragment {
    TextView txtChangePass, txtLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        txtChangePass = view.findViewById(R.id.txt_change_pass_frm_account);
        txtLogin = view.findViewById(R.id.txt_login_frm_account);
        txtChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}