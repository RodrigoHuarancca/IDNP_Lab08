package com.example.idnp_lab08;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class create extends Fragment {

    Button crear, listar;
    EditText usuario, contraseña;

    public static create newInstance(String param1, String param2) {
        create fragment = new create();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_user, container, false);

        crear = (Button) v.findViewById(R.id.registrar);
        listar = (Button) v.findViewById(R.id.listar);

        usuario = (EditText) v.findViewById(R.id.editUser);
        contraseña = (EditText) v.findViewById(R.id.editPass);

        DatabaseHelper db = new DatabaseHelper(this.MainActivity);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User aux = new User(0, String.valueOf(usuario), String.valueOf(contraseña));
                db.addUser(aux);

                //limpiando las casillas
                usuario.setText("");
                contraseña.setText("");
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, listar);
                fragmentTransaction.commit();
            }
        });

        db.close();
        return v;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}