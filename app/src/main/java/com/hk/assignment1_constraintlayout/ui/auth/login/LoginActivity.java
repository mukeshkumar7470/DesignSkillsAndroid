package com.hk.assignment1_constraintlayout.ui.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.hk.assignment1_constraintlayout.R;
import com.hk.assignment1_constraintlayout.models.LoginUser;
import com.hk.assignment1_constraintlayout.databinding.ActivityLoginBinding;
import com.hk.assignment1_constraintlayout.ui.auth.SignUpActivity;
import com.hk.assignment1_constraintlayout.viewModels.LoginViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                    binding.email.setError("Enter an E-Mail Address");
                    binding.email.requestFocus();
                }
                else if (!loginUser.isEmailValid()) {
                    binding.email.setError("Enter a Valid E-mail Address");
                    binding.email.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                    binding.password.setError("Enter a Password");
                    binding.password.requestFocus();
                }
                else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.password.setError("Enter at least 6 Digit password");
                    binding.password.requestFocus();
                }
                else {
                    callLoginApi();
                }

            }
        });


        binding.signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void callLoginApi() {
        Toast.makeText(LoginActivity.this, "Login Clicked...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}