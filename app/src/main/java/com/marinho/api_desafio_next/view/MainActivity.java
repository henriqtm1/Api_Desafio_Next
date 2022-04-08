package com.marinho.api_desafio_next.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.marinho.api_desafio_next.viewmodel.MainViewModel;
import com.marinho.api_desafio_next.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view_model = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setMyViewmodel(view_model);

        criandoObservers();

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.edtTxtCEP.getText().toString().length() >=8) {

                    view_model.callbacksRetrofit(binding.edtTxtCEP.getText().toString());
                } else {
                    binding.edtTxtCEP.setError("Insira um CEP válido");
                }

            }
        });
    }

    public void criandoObservers() {

        view_model.liveData_cep.observe(this, cep -> {
            binding.edtTxtCEP.setText(cep);
        });

        view_model.liveData_estado.observe(this, estado -> {
            binding.edtTxtUF.setText(estado);
        });

        view_model.liveData_cidade.observe(this, cidade -> {
            binding.edtTxtCIDADE.setText(cidade);
        });

        view_model.liveData_bairro.observe(this, bairro -> {
            binding.edtTxtBAIRRO.setText(bairro);
        });

        view_model.liveData_rua.observe(this, rua -> {
            binding.edtTxtRUA.setText(rua);
        });

    }

}