package com.example.rafael.trabfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Cadastro extends AppCompatActivity {
    private CadastroAuxiliar auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent intent = getIntent();
        final Cliente clienteParaAlterar = (Cliente) intent.getSerializableExtra("clienteClicado");

        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        Button botaoCancelar = (Button) findViewById(R.id.buttonCancelar);

        auxiliar = new CadastroAuxiliar(this);

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
                ClienteDAO dao = new ClienteDAO(Cadastro.this);

                if (clienteParaAlterar == null) {
                    dao.salva(cliente);
                }else {
                    cliente.setId(clienteParaAlterar.getId());
                    dao.editar(cliente);
                }

                dao.close();
                finish();
            }
        });

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
