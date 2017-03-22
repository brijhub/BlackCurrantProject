package com.example.brijesh.blackcurrantproject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    private String url="https://iamin-events.appspot.com/_ah/api/appUserApi/v1/collectionresponse_eventcategories/";
    TextView txt;
    private List<ListPozo> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private FirstLevelAdapter firstLevelAdapter;
    LinearLayoutManager linearLayoutManager;
    public String id;
    final static String DATA_RECEIVE = "data_receive";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.secondLevelList);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle args = getArguments();
        if (args != null) {
            // showReceivedData.setText(args.getString(DATA_RECEIVE));
            id = args.getString(DATA_RECEIVE);
            System.out.print("jsoninsidesecond..." + id);
            jsonObjectRequest(id);
        }
        return view;
    }


    private void jsonObjectRequest(String id) {

        System.out.print("jsoninside...");
        JsonObjectRequest jsonObjReq=new JsonObjectRequest(Request.Method.GET, url+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray items=jsonObject.getJSONArray("items");
                    System.out.println("json..."+items);
                    for(int i=0;i<items.length();i++)
                    {
                        JSONObject c=items.getJSONObject(i);
                        String id=c.getString("id");
                        String name=c.getString("name");
                        String hashtag=c.getString("isdefault");
                        String image=c.getString("backdropUrl");
                        ListPozo lp=new ListPozo();
                        lp.setImage(image);
                        lp.setTitle(name);
                        lp.setId(id);
                        lp.setDescription(hashtag);
                        list.add(lp);
                        System.out.println("jsondata..."+id+ name+ image + hashtag);
                    }

                    firstLevelAdapter=new FirstLevelAdapter(getContext(),list);
                    recyclerView.setAdapter(firstLevelAdapter);
                    firstLevelAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
