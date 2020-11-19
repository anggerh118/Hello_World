package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataMahasiswa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataMahasiswa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView dataMahasiswa;
    private FirebaseFirestore firebaseFirestore;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirestoreRecyclerAdapter dataMahasiswaAdapter;

    public DataMahasiswa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataMahasiswa.
     */
    // TODO: Rename and change types and number of parameters
    public static DataMahasiswa newInstance(String param1, String param2) {
        DataMahasiswa fragment = new DataMahasiswa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_data_mahasiswa, container, false);
       dataMahasiswa = view.findViewById(R.id.dataMahasiswa);
       firebaseFirestore = firebaseFirestore.getInstance();

       Query query = firebaseFirestore.collection("DaftarMhs");
       FirestoreRecyclerOptions<Mahasiswa> options = new FirestoreRecyclerOptions.Builder<Mahasiswa>()
               .setQuery(query, Mahasiswa.class)
               .build();

       dataMahasiswaAdapter = new FirestoreRecyclerAdapter<Mahasiswa, MahasiswaViewHolder>(options){
           @NonNull
           @Override
           public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_2,parent,false);
               return new MahasiswaViewHolder(view);
           }
           @Override
           protected void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position, @NonNull Mahasiswa model) {
               holder.nama.setText(model.getNama());
               holder.nim.setText(model.getNim());
               holder.nohp.setText(model.getPhone());
           }
       };
       dataMahasiswa.setLayoutManager(new LinearLayoutManager(getContext()));
       dataMahasiswa.setAdapter(dataMahasiswaAdapter);
       return view;
    }

    private class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private TextView nama;
        private TextView nim;
        private TextView nohp;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            nim = itemView.findViewById(R.id.nim);
            nohp = itemView.findViewById(R.id.nomorhp);

        }
    }
    @Override
    public void onStop(){
        super.onStop();
        dataMahasiswaAdapter.stopListening();
    }
    @Override
    public void onStart(){
        super.onStart();
        dataMahasiswaAdapter.startListening();
    }
}