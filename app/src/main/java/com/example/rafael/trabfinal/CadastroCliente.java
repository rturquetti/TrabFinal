package com.example.rafael.trabfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class CadastroCliente extends AppCompatActivity {
    private CadastroClienteAuxiliar auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent intent = getIntent();
        final Cliente clienteParaAlterar = (Cliente) intent.getSerializableExtra("clienteClicado");

        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        Button botaoCancelar = (Button) findViewById(R.id.buttonCancelar);

        auxiliar = new CadastroClienteAuxiliar(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinEstadosCli, android.R.layout.simple_spinner_item);
        auxiliar.retornaSpnner(this).setAdapter(adapter);

       if(clienteParaAlterar != null){
            botaoSalvar.setText("Alterar");
            auxiliar.exibeCliente(clienteParaAlterar);
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cliente = auxiliar.retornaCliente();
                ClienteDAO dao = new ClienteDAO(CadastroCliente.this);
                if (clienteParaAlterar == null) {
                    if (auxiliar.CampoVazio()){
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    }
                    else {
                        dao.salva(cliente);
                        dao.close();
                        finish();
                    }
                }else {
                    if (auxiliar.CampoVazio()){
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    }
                    else {
                        cliente.setId(clienteParaAlterar.getId());
                        dao.editar(cliente);
                        finish();
                    }
                }
            }
        });
        botaoCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
