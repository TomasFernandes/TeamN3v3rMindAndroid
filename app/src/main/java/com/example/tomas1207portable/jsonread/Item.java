package com.example.tomas1207portable.jsonread;

import android.util.Log;

public class Item {
    private String Nome;
    private String Imagem;
    private int peso;
    private int preço;
    private String radidade;
    private String Descriçao;

    public Item(String nome, String imagem, int peso, int preço, String radidade, String descriçao) {
        this.Nome = nome;
        this.Imagem = imagem;
        this.peso = peso;
        this.preço = preço;
        this.radidade = radidade;
        this.Descriçao = descriçao;
    }
    public void rightClick(){
            Log.d("BDO","Right click on "+ this.Nome);
    }
    public int callPreço(int Uso){
        if(Uso >= 8){
            this.preço = this.preço*80;
            return this.preço;
        }
        return this.preço;
    }
}