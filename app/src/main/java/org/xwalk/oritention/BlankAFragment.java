package org.xwalk.oritention;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.xwalk.oritention.databinding.FragmentBlankABinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentBlankABinding binding;
    private Long elapsedTime;
    public BlankAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankAFragment newInstance(String param1, String param2) {
        BlankAFragment fragment = new BlankAFragment();
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

     //   FragmentMas
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank_a,container,false);
        binding.setData(myNavigationVM);
        binding.setLifecycleOwner(getActivity());
        binding.button4.setOnClickListener(v->{
            NavController controller= Navigation.findNavController(v);
            controller.navigate(R.id.action_blankAFragment2_to_blankBFragment2);
        });
        binding.seekBar.setProgress(myNavigationVM.getNumber().getValue());
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                myNavigationVM.getNumber().setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       // binding.textView5.setBase(System.currentTimeMillis());//UNIX 时间 1970 1-1
//        binding.textView5.setBase(SystemClock.elapsedRealtime());//
//        binding.textView5.start();

        getLifecycle().addObserver(binding.textView5);
        return binding.getRoot();
     //   return inflater.inflate(R.layout.fragment_blank_a, container, false);
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        elapsedTime =  SystemClock.elapsedRealtime()-  binding.textView5.getBase();
//        binding.textView5.stop();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        binding.textView5.setBase(SystemClock.elapsedRealtime()-elapsedTime);
//        binding.textView5.start();
//    }
    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Button button;
//        button = getView().findViewById(R.id.button4);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                EditText editText = getView().findViewById(R.id.editTextTextPersonName);
//                String string = editText.getText().toString();
//                if (TextUtils.isEmpty(string)){
//                    Toast.makeText(getActivity(),"请输入名字",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Bundle bundle = new Bundle();
//                bundle.putString("my_name",string);
//                NavController controller= Navigation.findNavController(view);
//                controller.navigate(R.id.action_blankAFragment2_to_blankBFragment2,bundle);
//
//            }
//        });
//    }
}