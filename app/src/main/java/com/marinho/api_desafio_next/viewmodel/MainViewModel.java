package com.marinho.api_desafio_next.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marinho.api_desafio_next.model.ApiService;
import com.marinho.api_desafio_next.model.CEP;
import com.marinho.api_desafio_next.model.CEPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private CEPService cepService;

    //CEP
    private MutableLiveData<String> mutable_cep = new MutableLiveData<>();
    public LiveData<String> liveData_cep = mutable_cep;

    //ESTADO
    private MutableLiveData<String> mutable_estado = new MutableLiveData<>();
    public LiveData<String> liveData_estado = mutable_estado;

    //CIDADE
    private MutableLiveData<String> mutable_cidade = new MutableLiveData<>();
    public LiveData<String> liveData_cidade = mutable_cidade;

    //BAIRRO
    private MutableLiveData<String> mutable_bairro = new MutableLiveData<>();
    public LiveData<String> liveData_bairro = mutable_bairro;

    //RUA
    private MutableLiveData<String> mutable_rua = new MutableLiveData<>();
    public LiveData<String> liveData_rua = mutable_rua;

    public void callbacksRetrofit(String cep) {

        cepService = ApiService.iniciar().create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP(cep);

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                CEP cep = response.body();
                if (response.isSuccessful()) {

                    mutable_cep.setValue(cep.getCep());
                    mutable_estado.setValue(cep.getUf());
                    mutable_cidade.setValue(cep.getLocalidade());
                    mutable_bairro.setValue(cep.getBairro());
                    mutable_rua.setValue(cep.getLogradouro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });

    }


}
