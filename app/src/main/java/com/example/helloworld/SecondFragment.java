package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<dataModel> dataHolder;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        recyclerView=view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();

        dataModel ob1 = new dataModel(R.drawable.ralph, "Ralph Breaks the Internet 2", "Animation Film");
        dataHolder.add(ob1);

        dataModel ob2 = new dataModel(R.drawable.justiceleague, "Justice League", "Super Hero");
        dataHolder.add(ob2);

        dataModel ob3 = new dataModel(R.drawable.bigdaddy, "Big Daddy", "A fuckin nice comedy film");
        dataHolder.add(ob3);

        dataModel ob4 = new dataModel(R.drawable.rango, "Rango", "Kadal Ijo yang Bisa Berbicara");
        dataHolder.add(ob4);

        dataModel ob5 = new dataModel(R.drawable.therevenant, "The Revenant 2015", "Pemburu Kulit Hewan");
        dataHolder.add(ob5);

        dataModel ob6 = new dataModel(R.drawable.dolittle, "Dr Dolittle", "Dokter Hewan yang Baik Hati");
        dataHolder.add(ob6);

        dataModel ob7 = new dataModel(R.drawable.goosebump, "Goosebump", "Goosebump description");
        dataHolder.add(ob7);

        dataModel ob8 = new dataModel(R.drawable.bloodshot, "Bloodshot", "Bloodshot description");
        dataHolder.add(ob8);

        recyclerView.setAdapter(new myAdapter(dataHolder));

        return view;
    }
}