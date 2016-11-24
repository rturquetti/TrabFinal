package com.example.rafael.trabfinal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ManagerCliente extends AppCompatActivity {
    private ListView listOrcamento;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        listOrcamento = (ListView) findViewById(R.id.listOrcamento);

        //informar à listview para abrir o menu quando clicar
        registerForContextMenu(listOrcamento);

        listOrcamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //acesso ao adapter que foi vinculado à listview
            // a lista é uma view é necessário passá-la para ser modificada
            //posição para identificação de qual linha foi clicado
            //informar o id da listview para ser acessada
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Cliente clienteClicado = (Cliente) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerCliente.this, Cadastro.class);
                irCadastro.putExtra("clienteClicado", clienteClicado);
                startActivity(irCadastro);
            }
        });

        listOrcamento.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                cliente = (Cliente) adapter.getItemAtPosition(posicao);
                return false;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem ligar = menu.add("Ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //será usado uma itent implicipata que irá chamar qual aplicação faz este serviço
                Intent irLigar = new Intent(Intent.ACTION_CALL);
                //classe URI irá interpretar a informação para identificar que é um nº de telefone
                Uri discarPara = Uri.parse("tel:" + cliente.getTelCli());
                irLigar.setData(discarPara);

                if (ActivityCompat.checkSelfPermission(ManagerCliente.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(irLigar);
                }
                return false;
            }
        });

        MenuItem sms = menu.add("Enviar SMS");
        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //será usado uma itent implicipata que irá chamar qual aplicação faz este serviço
                Intent irSms = new Intent(Intent.ACTION_VIEW);
                //classe URI irá interpretar a informação para identificar que é um nº de telefone
                Uri smsPara = Uri.parse("sms:" + cliente.getTelCli());
                irSms.setData(smsPara);

                if (ActivityCompat.checkSelfPermission(ManagerCliente.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(irSms);
                }
                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ClienteDAO dao = new ClienteDAO(ManagerCliente.this);

                dao.deletar(cliente);
                dao.close();
                atualizaLista();

                return false;
            }
        });
        MenuItem email = menu.add("Enviar e-mail");
        email.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //será usado uma itent implicipata que irá chamar qual aplicação faz este serviço
                Intent irEmail = new Intent(Intent.ACTION_SENDTO);
                //classe URI irá interpretar a informação para identificar que é um nº de telefone
                Uri emailPara = Uri.parse("email:" + cliente.getEmailCli().toString());
                irEmail.setData(emailPara);

                if (ActivityCompat.checkSelfPermission(ManagerCliente.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(irEmail);
                }
                return false;
            }
        });
        super.onCreateContextMenu(menu,v, menuInfo);
    }

    public void atualizaLista(){
        ClienteDAO dao = new ClienteDAO(this);
        List<Cliente> clientes = dao.getLista();
        dao.close();

        ListaClienteAdapter adapter = new ListaClienteAdapter(clientes,this);



        listOrcamento.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_orcamento,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemClicado = item.getItemId();

        switch (itemClicado){
            case R.id.novoCli:
                Intent irCadastro = new Intent(this, Cadastro.class);
                startActivity(irCadastro);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
