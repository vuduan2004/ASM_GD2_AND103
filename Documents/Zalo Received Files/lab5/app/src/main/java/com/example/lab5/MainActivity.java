package com.example.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab5.adapter.DisAdapter;
import com.example.lab5.handle.Item_Handle;
import com.example.lab5.model.Distributor;
import com.example.lab5.model.Response;
import com.example.lab5.services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements Item_Handle {
    private RecyclerView rcvdis;
    private HttpRequest httpRequest;
    private DisAdapter adapter;
    EditText txtsearch;
    private ArrayList<Distributor> list;
    FloatingActionButton fltadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        rcvdis=findViewById(R.id.rcvDis);
        txtsearch=findViewById(R.id.txtsearch);
        fltadd=findViewById(R.id.fltadd);

        list= new ArrayList<>();
       //khởi tạo services
        httpRequest = new HttpRequest();
        // thực thi call API
        httpRequest.CallApi()
                .getListDistributor()//phương thi api cân thuc thi
                .enqueue(getListDistributorAPI);//xử lý bất đồng bộ
//tìm kiếm
        txtsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               if(i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                   //lấy từ khóa từ ô tìm kiếm
                   String key=txtsearch.getText().toString();
                   httpRequest.CallApi()
                           .searchDistributor(key) //phung thức API cần thực thi
                           .enqueue(getListDistributorAPI);//xử lý bất đồng bộ
                   return true;
               }
                return false;
            }
        });
        //nut thêm
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogthem();

            }
        });

    }

    //getdata
    private void getData(ArrayList<Distributor> ds) {
        adapter = new DisAdapter(this, ds,this);
        rcvdis.setLayoutManager(new LinearLayoutManager(this));
        rcvdis.setAdapter(adapter);

    }



    //Đây là khai báo một đối tượng Callback để xử lý phản hồi từ yêu cầu API.
// Đối tượng này sẽ được sử dụng để xử lý kết quả trả về từ yêu cầu API.
    Callback<Response<ArrayList<Distributor>>> getListDistributorAPI = new Callback<Response<ArrayList<Distributor>>>() {
        // phương thức được gọi khi có phản hồi từ yêu cầu API.
        // Nó nhận vào đối tượng Response chứa dữ liệu trả về từ server.
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            //khi call thành công
            if (response.isSuccessful()){
                if (response.body().getStatus() == 200){ //check status code
                    list= response.body().getData();// gán dữ liệu trả về từ phản hồi vào biến ds

                   getData(list);
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable t) {
            Log.d(">>>> Distributor", "onFailure: " + t.getMessage());
        }
    };

    //responapi
   // Đây là khai báo một đối tượng Callback để xử lý phản hồi từ yêu cầu API.
    // Đối tượng này sẽ được sử dụng để xử lý kết quả trả về từ yêu cầu API.
    Callback<Response<Distributor>> responseDistributorAPI=new Callback<Response<Distributor>>() {
       //Đây là phương thức được gọi khi có phản hồi từ yêu cầu API.
       // Nó nhận vào đối tượng Response chứa dữ liệu trả về từ server.
        @Override
        public void onResponse(Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                //
                    // gọi lại API để load lại dữ liệu sau khi thêm thành công.
                    httpRequest.CallApi().getListDistributor().enqueue(getListDistributorAPI);//
                }
            }
        }
//Phương thức này được gọi khi yêu cầu API gặp lỗi
        @Override
        public void onFailure(Call<Response<Distributor>> call, Throwable t) {
            Log.d(">>>> Distributor", "onFailure: " + t.getMessage());

        }
    };

    //opendialog thêm
    private void opendialogthem(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.item_add,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        //ánh xa
        EditText txtname = view.findViewById(R.id.txtname);
        Button btnthem = view.findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=txtname.getText().toString();
                Distributor dis=new Distributor();
                dis.setName(ten);
                httpRequest.CallApi().addDistributor(dis).enqueue(responseDistributorAPI);
//                    Toast.makeText(MainActivity.this, "Nội dung: " + content, Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // Đóng dialog sau khi thêm thành công
                    Toast.makeText(MainActivity.this, "insert succ", Toast.LENGTH_SHORT).show();
                }
        });

    }


    @Override
    public void Delete(String id) {
        httpRequest.CallApi().deleteDistributorById(id).enqueue(responseDistributorAPI);

    }

    @Override
    public void Update(String id, Distributor distributor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//khởi tạo alertdialog, AlertDialog.Builder được sử dụng để xây dựng một hộp thoại cảnh báo (alert dialog), giúp hiển thị thông báo cho người dùng trên màn hình.
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update, null);
        builder.setView(view);//thiêt lâp view cho hộp thoại
        Dialog dialog = builder.create();//tạo ra dialog
        dialog.show();

        EditText txtnameud = view.findViewById(R.id.txtnameud);

        Button btnsua = view.findViewById(R.id.btnsua);
        //gán dữ liệu lên ô text
        txtnameud.setText(distributor.getName());
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=txtnameud.getText().toString();
                distributor.setName(ten);
                httpRequest.CallApi().updateDistributorById(id,distributor).enqueue(responseDistributorAPI);
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "update succ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}