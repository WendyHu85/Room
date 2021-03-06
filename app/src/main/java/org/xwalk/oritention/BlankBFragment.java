package org.xwalk.oritention;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.oritention.databinding.FragmentBlankABinding;
import org.xwalk.oritention.databinding.FragmentBlankBBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankBFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankBFragment newInstance(String param1, String param2) {
        BlankBFragment fragment = new BlankBFragment();
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
        MyNavigationVM myNavigationVM;
        myNavigationVM = ViewModelProviders.of(getActivity()).get(MyNavigationVM.class);

        FragmentBlankBBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank_b,container,false);
        binding.setData(myNavigationVM);
        binding.setLifecycleOwner(getActivity());

        binding.BackBT.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_blankBFragment2_to_blankAFragment2);

        });
        return binding.getRoot();
       // return inflater.inflate(R.layout.fragment_blank_b, container, false);
    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        Button button;
////        button = getView().findViewById(R.id.button5);
////        button.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                NavController controller= Navigation.findNavController(view);
////                controller.navigate(R.id.action_blankBFragment2_to_blankAFragment2);
////            }
////        });
//        getView().findViewById(R.id.button5).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_blankBFragment2_to_blankAFragment2));
//
//        String mstring = getArguments().getString("name");
//        String string2 = getArguments().getString("my_name");
//        Log.d(TAG, "onActivityCreated: "+mstring);
//        TextView textView = getView().findViewById(R.id.textView4);
//        textView.setText(string2);
//    }
}